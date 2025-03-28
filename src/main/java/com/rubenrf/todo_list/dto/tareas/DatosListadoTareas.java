package com.rubenrf.todo_list.dto.tareas;

import com.rubenrf.todo_list.models.Tarea;

public record DatosListadoTareas(Long id, String title, String description) {

    public DatosListadoTareas(Tarea tarea) {
        this(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion());
    }

}
