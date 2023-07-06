package com.vti.templaterestfulapi.service;


import com.vti.templaterestfulapi.models.Role;
import com.vti.templaterestfulapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role create(Role role){
        return roleRepository.save(role);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

}
