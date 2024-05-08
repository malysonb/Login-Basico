package com.malysonb.loginBasico.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.malysonb.loginBasico.config.JwtTokenUtil;
import com.malysonb.loginBasico.dto.BearerToken;
import com.malysonb.loginBasico.dto.RegistrarDTO;
import com.malysonb.loginBasico.model.Usuario;
import com.malysonb.loginBasico.repository.RoleRepository;
import com.malysonb.loginBasico.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginService implements UserDetailsService{

    @Autowired
    UsuarioRepository uRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    private JwtTokenUtil jUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = uRepo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não foi encontrado!"));
        return user;
    }

    public ResponseEntity<?> registrar(RegistrarDTO dto){
        if(uRepo.findByLogin(dto.getLogin()).isPresent()){
            return new ResponseEntity<>("Já existe um usuário com este login!", HttpStatus.SEE_OTHER);
        }
        else{
            Usuario user = new Usuario();
            user.setEmail(dto.getEmail());
            user.setLogin(dto.getLogin());
            user.setSenha(new BCryptPasswordEncoder().encode(dto.getSenha()));
            user.setNivel(roleRepo.findById("USER").get());
            user.setAtivado(true);
            uRepo.save(user);
            String token = jUtil.generateToken(user);
            return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
        }
    }
    
    

}
