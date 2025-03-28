package com.rubenrf.todo_list.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rubenrf.todo_list.dto.usuario.DatosCrearUsuario;
import com.rubenrf.todo_list.models.Usuario;
import com.rubenrf.todo_list.repository.UsuarioRepository;
import com.rubenrf.todo_list.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(DatosCrearUsuario datosCrearUsuario) {
        Usuario usuario = new Usuario(datosCrearUsuario.name(), datosCrearUsuario.email(),
                datosCrearUsuario.password());

        Usuario usuarioRegistrado = usuarioRepository.save(usuario);

        return usuarioRegistrado;
    }

    @Override
    public void actualizarUsuario() {
    }

    @Override
    public void obtenerUsuarioPorNombre() {
    }

    @Override
    public void eliminarUsuario() {
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);
        return usuario;
    }

    @Override
    public Usuario obtenerUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return usuario;
    }

}
