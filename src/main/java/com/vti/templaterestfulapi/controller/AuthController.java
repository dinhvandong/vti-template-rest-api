package com.vti.templaterestfulapi.controller;


import com.vti.templaterestfulapi.dto.DeviceInfo;
import com.vti.templaterestfulapi.dto.LogOutAllDeviceRequest;
import com.vti.templaterestfulapi.dto.LoginForm;
import com.vti.templaterestfulapi.models.*;
import com.vti.templaterestfulapi.payload.request.LoginRequest;
import com.vti.templaterestfulapi.payload.response.JwtResponse;
import com.vti.templaterestfulapi.payload.response.JwtResponse2;
import com.vti.templaterestfulapi.repositories.RoleRepository;
import com.vti.templaterestfulapi.repositories.UserRepository;
import com.vti.templaterestfulapi.security.jwt.JwtProvider;
import com.vti.templaterestfulapi.security.jwt.JwtUtils;
import com.vti.templaterestfulapi.service.RefreshTokenService;
import com.vti.templaterestfulapi.service.UserDetailsImpl;
import com.vti.templaterestfulapi.service.UserDeviceService;
import com.vti.templaterestfulapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*","http://localhost:8081",
        "http://localhost:3000","http://localhost:8080",
        "http://localhost:3001"},maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserDeviceService userDeviceService;
    @Autowired
    private UserService userService;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String refreshToken, @RequestHeader("Authorization") String token) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(refreshToken);
        String jwt = token.split(" ")[1];
        System.out.println("Test Login");
        if (optionalRefreshToken.isPresent()) {
            refreshTokenService.deleteById(optionalRefreshToken.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Logout success", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(400, "Logout fail", ""));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm) {
        Optional<User> optionalUser = userRepository.findByUserName(loginForm.getUsername());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "Fail", "User or password is incorrect!!"));
        }
        System.out.println("Test signin");

        User user = optionalUser.get();
        if (user.isActive()) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginForm.getUsername(),
                            loginForm.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtProvider.generateJwtToken(authentication);
            userDeviceService.findByUserId(user.getId())
                    .map(UserDevice::getRefreshToken)
                    .map(RefreshToken::getId)
                    .ifPresent(refreshTokenService::deleteById);

            Optional<UserDevice> optionalUserDevice = userDeviceService.findByUserId(user.getId());
            if(optionalUserDevice.isPresent()){
                userDeviceService.deleteOldDevice(user.getId());
            }

            UserDevice userDevice = userDeviceService.createUserDevice(loginForm.getDeviceInfo());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken();
            userDevice.setUser(user);
            userDevice.setRefreshToken(refreshToken);
            refreshToken.setUserDevice(userDevice);
            refreshToken = refreshTokenService.save(refreshToken);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Ok",
                            new JwtResponse2(jwtToken,
                                    refreshToken.getToken(),
                                    jwtProvider.getExpiryDuration(),
                                    user.getId(), null, loginForm.getUsername())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(400, "Fail", "User has been deactivated/locked !!"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginForm loginForm) {
        Optional<User> optionalUser =
                userRepository.findByUserName(loginForm.getUsername());
        //   .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseObject(201, "Fail", "User or password is incorrect!!"));
        }
        User user = optionalUser.get();
        Optional<UserDevice> oldDeivceOptional = userDeviceService.findByUserId(user.getId());
        if (oldDeivceOptional.isPresent()) {
            UserDevice oldDeivce = oldDeivceOptional.get();
            LogOutAllDeviceRequest logOutRequest = new LogOutAllDeviceRequest();
            logOutRequest.setDeviceInfo(new DeviceInfo(oldDeivce.getDeviceId(), oldDeivce.getDeviceType()));
            userService.logout(oldDeivce.getDeviceId(), oldDeivce.getUser().getId(), logOutRequest);
        }
//        for (Role role : user.getRoles()) {
//            if (role.getName() == ERole.ROLE_MODERATOR ||
//                    role.getName() == ERole.ROLE_ADMIN ||
//                    role.getName() == ERole.ROLE_MODERATOR) {
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "Fail", "Account have no permission!"));
//
//            }
//        }
        if (user.isActive()) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginForm.getUsername(),
                            loginForm.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtProvider.generateJwtToken(authentication);
            userDeviceService.findByUserId(user.getId())
                    .map(UserDevice::getRefreshToken)
                    .map(RefreshToken::getId)
                    .ifPresent(refreshTokenService::deleteById);
            // userService.findUserById(user.getId()).;
            UserDevice userDevice = userDeviceService.createUserDevice(loginForm.getDeviceInfo());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken();
            userDevice.setUser(user);
            userDevice.setRefreshToken(refreshToken);
            refreshToken.setUserDevice(userDevice);
            refreshToken = refreshTokenService.save(refreshToken);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Ok",
                    new JwtResponse2(jwtToken,
                            refreshToken.getToken(), jwtProvider.getExpiryDuration(), user.getId(), user.getRoles(), loginForm.getUsername())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(400, "Fail", "User has been deactivated/locked !!"));
        //return ResponseEntity.badRequest().body(new ApiResponse(false, "User has been deactivated/locked !!"));
    }
}