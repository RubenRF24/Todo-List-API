package com.rubenrf.todo_list.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosCrearUsuario(@NotBlank String name, @Email String email, @NotBlank String password) {

}
