create table tareas(

    id_tarea bigint not null auto_increment,
    titulo varchar(200) not null,
    descripcion text not null,
    autor bigint not null,

    primary key (id_tarea) 

);

ALTER TABLE tareas ADD FOREIGN KEY (autor) REFERENCES usuarios(id_usuario);