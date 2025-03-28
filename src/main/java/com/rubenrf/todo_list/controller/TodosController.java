package com.rubenrf.todo_list.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rubenrf.todo_list.dto.tareas.DatosActualizarTarea;
import com.rubenrf.todo_list.dto.tareas.DatosCrearTarea;
import com.rubenrf.todo_list.dto.tareas.DatosListadoTareas;
import com.rubenrf.todo_list.dto.tareas.DatosRespuestaTarea;
import com.rubenrf.todo_list.infra.security.TokenService;
import com.rubenrf.todo_list.models.Tarea;
import com.rubenrf.todo_list.models.Usuario;
import com.rubenrf.todo_list.service.TareasService;
import com.rubenrf.todo_list.service.UsuarioService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodosController {

    private final TokenService tokenService;

    @Autowired
    private TareasService tareasService;

    @Autowired
    private UsuarioService usuarioService;

    TodosController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    @RateLimiter(name = "todos-create")
    public ResponseEntity<?> registrarTarea(@RequestBody @Valid DatosCrearTarea datosCrearTarea,
            UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {

        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(obtenerAutor(request));
        Tarea tareaRegistrada = tareasService.crearTarea(datosCrearTarea, usuario);

        URI uri = uriComponentsBuilder.path("/todos/{id}").buildAndExpand(tareaRegistrada.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DatosRespuestaTarea(tareaRegistrada.getId(), tareaRegistrada.getTitulo(),
                        tareaRegistrada.getDescripcion()));
    }

    @PutMapping("/{id}")
    @RateLimiter(name = "todos-update")
    public ResponseEntity<?> actualizarTarea(@PathVariable Long id,
            @RequestBody DatosActualizarTarea DatosActualizarTarea, HttpServletRequest request) {

        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(obtenerAutor(request));
        Tarea tarea = tareasService.obtenerTarea(id);

        if (!verificarAutor(tarea, usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        tarea.actualizarTarea(DatosActualizarTarea);

        tareasService.actualizarTarea(tarea);

        return ResponseEntity.ok(new DatosRespuestaTarea(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @RateLimiter(name = "todos-delete")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long id, HttpServletRequest request) {

        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(obtenerAutor(request));
        Tarea tarea = tareasService.obtenerTarea(id);

        if (!verificarAutor(tarea, usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        tareasService.eliminarTarea(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @RateLimiter(name = "todos-list")
    public ResponseEntity<Page<DatosListadoTareas>> listarTareas(@PageableDefault Pageable paginacion) {
        return ResponseEntity.ok(tareasService.obtenerTareas(paginacion));
    }

    public String obtenerAutor(HttpServletRequest request) {
        String emailUsuario = null;
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            emailUsuario = tokenService.getSubject(token);
        }
        return emailUsuario;
    }

    private Boolean verificarAutor(Tarea tarea, Usuario usuario) {
        return tarea.getAutor().getId() == usuario.getId();

    }

}
