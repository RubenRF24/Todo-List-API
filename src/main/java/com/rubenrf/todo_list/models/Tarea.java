package com.rubenrf.todo_list.models;

import com.rubenrf.todo_list.dto.tareas.DatosActualizarTarea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tareas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    public void actualizarTarea(DatosActualizarTarea datosActualizarTarea) {

        if (datosActualizarTarea.title() != null) {
            this.titulo = datosActualizarTarea.title();
        }
        if (datosActualizarTarea.description() != null) {
            this.descripcion = datosActualizarTarea.description();
        }
    }

}
