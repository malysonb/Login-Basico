package com.malysonb.loginBasico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malysonb.loginBasico.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByLogin(String login);

}
