package com.rubenrf.todo_list.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rubenrf.todo_list.dto.tareas.DatosCrearTarea;
import com.rubenrf.todo_list.dto.tareas.DatosListadoTareas;
import com.rubenrf.todo_list.models.Tarea;
import com.rubenrf.todo_list.models.Usuario;
import com.rubenrf.todo_list.repository.TareasRepository;
import com.rubenrf.todo_list.service.TareasService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasRepository tareasRepository;

    @Override
    public Tarea crearTarea(DatosCrearTarea datosCrearTarea, Usuario usuario) {

        Tarea tarea = Tarea.builder()
                .titulo(datosCrearTarea.title())
                .descripcion(datosCrearTarea.description())
                .autor(usuario)
                .build();

        Tarea tareaRegistrada = tareasRepository.save(tarea);

        return tareaRegistrada;

    }

    @Override
    public void actualizarTarea(Tarea tarea) {
        tareasRepository.save(tarea);
    }

    @Override
    public Tarea obtenerTarea(Long id) {

        Tarea tarea = tareasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea #" + id + " no encontrada"));

        return tarea;
    }

    @Override
    public Page<DatosListadoTareas> obtenerTareas(Pageable paginacion) {
        return tareasRepository.findAll(paginacion).map(DatosListadoTareas::new);
    }

    @Override
    public void eliminarTarea(Long id) {
        tareasRepository.deleteById(id);
    }

}
