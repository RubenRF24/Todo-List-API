package com.rubenrf.todo_list.dto.tareas;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearTarea(@NotBlank String title, @NotBlank String description) {
}
