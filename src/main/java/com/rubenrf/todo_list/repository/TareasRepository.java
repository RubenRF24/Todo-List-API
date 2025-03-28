package com.rubenrf.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubenrf.todo_list.models.Tarea;

public interface TareasRepository extends JpaRepository<Tarea, Long> {

}
