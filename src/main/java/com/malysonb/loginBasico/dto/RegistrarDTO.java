package com.malysonb.loginBasico.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * DTO básico de registro de usuário.
 * @author Malyson Souza
 * @since 2024
 * @version 1.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrarDTO implements Serializable{
    
    private String nome;
    private String login;
    private String senha;
    private String email;

}
