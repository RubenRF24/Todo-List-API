package com.rubenrf.todo_list.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rubenrf.todo_list.dto.tareas.DatosCrearTarea;
import com.rubenrf.todo_list.dto.tareas.DatosListadoTareas;
import com.rubenrf.todo_list.models.Tarea;
import com.rubenrf.todo_list.models.Usuario;

public interface TareasService {

    public Tarea crearTarea(DatosCrearTarea datosCrearTarea, Usuario usuario);

    public void actualizarTarea(Tarea tarea);

    public Tarea obtenerTarea(Long id);

    public Page<DatosListadoTareas> obtenerTareas(Pageable paginacion);

    public void eliminarTarea(Long id);

}
