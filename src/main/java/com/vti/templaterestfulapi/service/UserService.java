package com.vti.templaterestfulapi.service;
import com.vti.templaterestfulapi.dto.LogOutAllDeviceRequest;
import com.vti.templaterestfulapi.event.OnUserLogoutAllSuccessEvent;
import com.vti.templaterestfulapi.exception.UserLogoutException;
import com.vti.templaterestfulapi.models.User;
import com.vti.templaterestfulapi.models.UserDevice;
import com.vti.templaterestfulapi.payload.response.UserResponse;
import com.vti.templaterestfulapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class UserService {
    @Autowired
    UserRepository userRepository;
//
//    @Autowired
//    DepartmentRepository departmentRepository;
    public List<User> getUserPagging(long parentID, int pageNum, int pageSize)
    {
        Pageable pageOne = PageRequest.of(pageNum,pageSize);
        Page<User> studentPagging = userRepository.findAllByParentID(parentID,pageOne);
        return studentPagging.stream().toList();
    }

    public void removeUser(long userID){
        Optional<User> optionalUser = userRepository.findById(userID);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setActive(false);
            userRepository.save(user);
        }
    }

    public List<UserResponse> getAllUser()
    {
         List<User> userList =  findAllByIsActive();
                 //userRepository.findAll();
         int index = 0;
         try{
             for(User user: userList){
                 if(user.getParentID()==0){
                     try{
                         userList.remove(index);
                     }catch (Exception ex){
                     }
                 }
                 index ++;
             }
         }catch (Exception exception){
         }
        List<UserResponse> userResponseList = new ArrayList<>();
        for(User user1: userList)
        {
            UserResponse userResponse = new UserResponse();

            userResponse.setNote(user1.getNote());
            userResponse.setId(user1.getId());
            userResponse.setParentID(user1.getParentID());
            userResponse.setActive(user1.isActive());
            userResponse.setUserName(user1.getUserName());
            userResponse.setCreatedTime(user1.getCreatedTime());
            userResponse.setFullName(user1.getFullName());
            userResponse.setRoles(user1.getRoles());
            userResponse.setDepartmentID(user1.getDepartmentID());

//            Optional<Department> departmentOptional = departmentRepository.findById(user1.getDepartmentID());
//            userResponse.setDepartmentName(departmentOptional.get().getDepartmentName());


            userResponseList.add(userResponse);
        }

        return  userResponseList;
    }
    public User findUserByUserName(String userName){
        List<User> userList = userRepository.findAll();
        for(User user: userList){
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return  null;
    }
    public List<User> findAllByIsActive()
    {
        List<User> allUser = userRepository.findAll();
        List<User> returnList = new ArrayList<>();
        for(User user: allUser)
        {
            if(user.isActive())
            {
                returnList.add(user);
            }
        }
        return  returnList;
    }
    public User findUserById(long id){
        return  userRepository.findById(id).get();
    }
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserDeviceService userDeviceService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    public void logout(String deviceId , long userId,
                      LogOutAllDeviceRequest logOutRequest){
        UserDevice userDevice = userDeviceService.findByUserId(userId)
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new UserLogoutException(deviceId, "Invalid device Id supplied. No matching device found for the given user "));
        refreshTokenService.deleteById(userDevice.getRefreshToken().getId());
        User currentUser = findUserById(userId);
        OnUserLogoutAllSuccessEvent logoutSuccessEvent = new OnUserLogoutAllSuccessEvent
                (currentUser.getUserName(),
                 logOutRequest);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
    }
}
