package com.rubenrf.todo_list.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rubenrf.todo_list.dto.jwt.DatosJWTToken;
import com.rubenrf.todo_list.dto.usuario.DatosCrearUsuario;
import com.rubenrf.todo_list.infra.security.TokenService;
import com.rubenrf.todo_list.models.Usuario;
import com.rubenrf.todo_list.service.UsuarioService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @RateLimiter(name = "register")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosCrearUsuario datosCrearUsuario,
            UriComponentsBuilder uriComponentsBuilder) {

        log.info("Entrando al metodo registrar");
        Usuario usuarioRegistrado = usuarioService.crearUsuario(datosCrearUsuario);

        var JWTToken = tokenService.generarToken(usuarioRegistrado);
        URI uri = uriComponentsBuilder.path("/todos/{id}").buildAndExpand(usuarioRegistrado.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosJWTToken(JWTToken));
    }

}
