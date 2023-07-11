package com.vti.templaterestfulapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String passWord;
    private  String note;
    @Column(nullable = false)
    private String email;

    private String googleID;
    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date modifiedDate;

    private boolean isActive;

    private long parentID;

    private int departmentID;

    private String departmentName;

    public long getParentID() {
        return parentID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public User() {
        this.isActive = true;

    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public User(String userName, String fullName, String passWord, String note,  Date createdTime, boolean isActive, long parentID) {
        this.userName = userName;
        this.fullName = fullName;
        this.passWord = passWord;
        this.createdTime = createdTime;
        this.isActive = isActive;
        this.note = note;
        this.parentID = parentID;
    }

    public User(String userName, String fullName, String passWord, String note, long parentID, String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.passWord = passWord;
        this.note = note;
        this.isActive = true;
        this.parentID = parentID;
        this.email = email;
//        String email
    }

    public User(Long id, String userName, String fullName, String passWord, String note, Date createdTime, boolean isActive, long parentID) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.passWord = passWord;
       // this.email = email;
        this.note = note;
        this.createdTime = createdTime;
        this.isActive = isActive;
        this.parentID = parentID;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isModarator()
    {
        return true;
    }

    public boolean isAdmin(){
        return true;
    }

    public boolean isTeacher(){
        return true;
    }
    public boolean isAH()
    {
        return true;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
