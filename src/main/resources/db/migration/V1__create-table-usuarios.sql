create table usuarios(

    id_usuario bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    clave varchar(100) not null,

    primary key (id_usuario) 

);