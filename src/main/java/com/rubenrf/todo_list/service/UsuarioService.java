package com.rubenrf.todo_list.service;

import com.rubenrf.todo_list.dto.usuario.DatosCrearUsuario;
import com.rubenrf.todo_list.models.Usuario;

import jakarta.persistence.EntityNotFoundException;

public interface UsuarioService {

    public Usuario crearUsuario(DatosCrearUsuario datosCrearUsuario);

    public void actualizarUsuario();

    public Usuario obtenerUsuario(Long id) throws EntityNotFoundException;

    public void obtenerUsuarioPorNombre();

    public Usuario obtenerUsuarioPorEmail(String email);

    public void eliminarUsuario();

}
