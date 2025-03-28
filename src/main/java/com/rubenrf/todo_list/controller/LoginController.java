package com.rubenrf.todo_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubenrf.todo_list.dto.jwt.DatosJWTToken;
import com.rubenrf.todo_list.dto.usuario.DatosAutenticacionUsuario;
import com.rubenrf.todo_list.infra.security.TokenService;
import com.rubenrf.todo_list.models.Usuario;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @RateLimiter(name = "login")
    public ResponseEntity<?> login(@RequestBody @Valid DatosAutenticacionUsuario DatosAutenticacionUsuario) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(DatosAutenticacionUsuario.email(),
                DatosAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok().body(new DatosJWTToken(JWTToken));
    }
}
