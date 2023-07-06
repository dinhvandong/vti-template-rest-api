package com.vti.templaterestfulapi.payload.response;

import com.vti.templaterestfulapi.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse2 {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Long expiryDuration;
    private Set<Role> roles = new HashSet<>();

    private Long userID;
    private String email;

    public JwtResponse2(String accessToken, String refreshToken,
                        Long expiryDuration, Long userID, Set<Role> roles, String email) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiryDuration = expiryDuration;
        tokenType = "Bearer ";
        this.userID = userID;
        this.roles = roles;
        this.email = email;
    }

    public JwtResponse2(String accessToken, String refreshToken,
                        String tokenType, Long expiryDuration,
                        Set<Role> roles, Long userID) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiryDuration = expiryDuration;
        this.roles = roles;
        this.userID = userID;
    }
}
