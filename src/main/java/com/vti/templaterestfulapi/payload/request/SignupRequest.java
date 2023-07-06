package com.vti.templaterestfulapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
  @NotBlank
  @Size(max = 50)
  private String username;
  @NotBlank
  @Size(max = 50)
  private String fullName;
//  @NotBlank
//  @Size(max = 50)
//  @Email
//  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;


  private int departmentID;


  private  String note;

  public int getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(int departmentID) {
    this.departmentID = departmentID;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getUsername() {
    return username;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setUsername(String username) {
    this.username = username;
  }
//
//  public String getEmail() {
//    return email;
//  }
//
//  public void setEmail(String email) {
//    this.email = email;
//  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
