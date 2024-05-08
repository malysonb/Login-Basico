package com.malysonb.loginBasico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malysonb.loginBasico.model.Role;

public interface RoleRepository  extends JpaRepository<Role, String>{
    
}
