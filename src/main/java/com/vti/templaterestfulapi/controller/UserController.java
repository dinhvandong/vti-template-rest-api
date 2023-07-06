//package com.vti.templaterestfulapi.controller;
//
//import com.btect.fpt.cmsbackend.models.*;
//import com.btect.fpt.cmsbackend.payload.request.SignupRequest;
//import com.btect.fpt.cmsbackend.payload.response.UserResponse;
//import com.btect.fpt.cmsbackend.repositories.DepartmentRepository;
//import com.btect.fpt.cmsbackend.repositories.RoleRepository;
//import com.btect.fpt.cmsbackend.repositories.UserRepository;
//import com.btect.fpt.cmsbackend.security.jwt.JwtProvider;
//import com.btect.fpt.cmsbackend.service.RefreshTokenService;
//import com.btect.fpt.cmsbackend.service.UserDeviceService;
//import com.btect.fpt.cmsbackend.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@CrossOrigin(origins = {"*","http://localhost:8081",
//        "http://localhost:3000","http://localhost:8080",
//        "http://localhost:3001"},maxAge = 3600)
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    JwtProvider jwtUtils;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    DepartmentRepository departmentRepository;
//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//    PasswordEncoder encoder;
////    @Autowired
////    JwtUtils jwtUtils;
//
//    @Autowired
//    JwtProvider jwtProvider;
//    @Autowired
//    UserService userService;
////    @Autowired
////    PingRepository pingRepository;
//
//    @Autowired
//    private RefreshTokenService refreshTokenService;
//
//    @Autowired
//    private UserDeviceService userDeviceService;
//
//    @Autowired
//    private ApplicationEventPublisher applicationEventPublisher;
//    @PostMapping("/createAccount")
//    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest,
//                                          @RequestHeader("Authorization") String token) {
//        Optional<User> optionalUser = null;
//        String jwt = token.split(" ")[1];
//        if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
//            String username = jwtProvider.getUserNameFromJwtToken(jwt);
//            optionalUser = userRepository.findByUserName(username);
//        }
//        System.out.println("AAA1");
//        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
//            System.out.println("AAA2");
//
////            return ResponseEntity
////                    .badRequest()
////                    .body(new MessageResponse("Error: Username is already taken!"));
////            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(400,"fail", "User already exist "));
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(400,"User already exist ", ""));
//
//        }
//        System.out.println("AAA3");
//
//        // Create new user's account
//        User user = new User();
//        user.setDepartmentID(signUpRequest.getDepartmentID());
//        Optional<Department> departmentOptional = departmentRepository.findById(signUpRequest.getDepartmentID());
//        user.setDepartmentName(departmentOptional.get().getDepartmentName());
//        user.setEmail(signUpRequest.getUsername());
//        user.setFullName(signUpRequest.getFullName());
//        user.setUserName(signUpRequest.getUsername());
//        user.setPassWord(encoder.encode(signUpRequest.getPassword()));
//        System.out.println("AAA4");
//
//        user.setParentID(optionalUser.get().getId());
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_TEACHER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//                        break;
//                    case "ah":
//                        Role subRole = roleRepository.findByName(ERole.ROLE_AH)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(subRole);
//                        break;
//                    case "teacher":
//                        Role subAdminRole = roleRepository.findByName(ERole.ROLE_TEACHER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(subAdminRole);
//                        break;
//                }
//            });
//        }
//        System.out.println("AAA5");
//
//        user.setCreatedTime(new Date());
//        user.setModifiedDate(new Date());
//        user.setRoles(roles);
//        user.setActive(true);
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"User registered successfully!", userRepository.save(user)));
//    }
//
//
//    @PostMapping("/changePassWord")
//    public ResponseEntity<?> changePassword(@RequestParam String oldPassword,
//                                            @RequestParam String newPassword,
//                                            @RequestHeader("Authorization") String token)
//    {
//        Optional<User> optionalUser = null;
//        String jwt = token.split(" ")[1];
//        if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
//            String username = jwtProvider.getUserNameFromJwtToken(jwt);
//            optionalUser = userRepository.findByUserName(username);
//        }
//        if(optionalUser.isPresent())
//        {
//            User user = optionalUser.get();
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(), oldPassword));
//
//            if(authentication.isAuthenticated()){
//                user.setPassWord(encoder.encode(newPassword));
//                user.setModifiedDate(new Date());
//                User returnUser =   userRepository.save(user);
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Change password successful", returnUser));
//
//            }else {
//
//                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(201,"Old password is incorrect", ""));
//
//            }
//        }else
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(201,"User update not found by ID", ""));
//        }
//    }
//
//
//
//    @PostMapping("/changePassWordByParent")
//    public ResponseEntity<?> changePasswordByParent(@RequestParam Long userId,
//                                                    @RequestParam String newPassword,  @RequestHeader("Authorization") String token)
//    {
//        Optional<User> optionalUserSuper = null;
//        String jwt = token.split(" ")[1];
//        if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
//            String username = jwtProvider.getUserNameFromJwtToken(jwt);
//            optionalUserSuper = userRepository.findByUserName(username);
//        }
//        Optional<User> optionalUser = null;
//        optionalUser = userRepository.findById(userId);
//
//        if(optionalUser.isPresent())
//        {
//            User user = optionalUser.get();
//            if(user.getParentID() == optionalUserSuper.get().getId() || optionalUserSuper.get().getParentID()==0)
//            {
//                // Hoac la Admin voi parentID ==0 hoac la parent
//                user.setPassWord(encoder.encode(newPassword));
//                user.setModifiedDate(new Date());
//
//                User returnUser =   userRepository.save(user);
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Change password successful", returnUser));
//
//            }else
//            {
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(404,"User update not found", ""));
//            }
//
//        }else
//        {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(404,"User update not found", ""));
//        }
//    }
//    @PostMapping("/getListUser")
//    public ResponseEntity<?> getUserByToken(@RequestHeader("Authorization") String token)
//    {
//        try{
//            Optional<User> optionalUser = null;
//            String jwt = token.split(" ")[1];
//            if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
//                String username = jwtProvider.getUserNameFromJwtToken(jwt);
//                optionalUser = userRepository.findByUserName(username);
//            }
//            List<UserResponse> returnList = userService.getAllUser();
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"success",returnList  ));
//        }catch (Exception ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(201,"Not found", ""));
//        }
//    }
//
//
//    @PostMapping("/getDepartmentByEmail")
//    public ResponseEntity<?> getDepartmentByEmail(@RequestParam String email)
//    {
//        Optional<User> userOptional = userRepository.findByUserName(email);
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            int departmentID = user.getDepartmentID();
//            Optional<Department> departmentOptional = departmentRepository.findById(departmentID);
//            if(departmentOptional.isPresent()){
//                Department department = departmentOptional.get();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"success",department  ));
//            }else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(201,"Department Not found", ""));
//            }
//        }else {
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(201,"User Not found", ""));
//
//        }
//
//    }
//    @PostMapping("/getAllDepartment")
//    public ResponseEntity<?> getAllDepartment()
//    {
//       List<Department> departmentList = departmentRepository.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"success",departmentList  ));
//    }
//    @PostMapping("/removeUser")
//    public ResponseEntity<?> removeUser(@RequestParam Long userID)
//    {
//        try{
//           userService.removeUser(userID);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"success","remove"  ));
//        }catch (Exception ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(201,"Not found", ""));
//        }
//    }
//}
