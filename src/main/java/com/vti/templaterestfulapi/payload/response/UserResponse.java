package com.vti.templaterestfulapi.payload.response;

import com.vti.templaterestfulapi.models.Role;
import com.vti.templaterestfulapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String userName;

    private String fullName;
    private String passWord;


    private  String departmentName;

    private int departmentID;
    private  String note;

    private Date createdTime;
    private boolean isActive;
    private long parentID;


    public UserResponse(User user1)
    {
        this.setNote(user1.getNote());
        this.setId(user1.getId());
        this.setParentID(user1.getParentID());
        this.setActive(user1.isActive());
        this.setUserName(user1.getUserName());
        this.setCreatedTime(user1.getCreatedTime());
        this.setFullName(user1.getFullName());
        this.setRoles(user1.getRoles());
    }
    private Set<Role> roles = new HashSet<>();
}
