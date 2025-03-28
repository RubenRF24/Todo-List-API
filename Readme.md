# API de Lista de Tareas

En este proyecto se requiere desarrollar una API RESTful para permitir a los usuarios gestionar su lista de tareas. Los proyectos backend anteriores se han centrado únicamente en operaciones CRUD, pero este proyecto requerirá que implementes autenticación de usuarios también.

Este se basó en [Todo List API](https://roadmap.sh/projects/todo-list-api) realizado por [Roadmap.sh](https://roadmap.sh/).

## Objetivos

Las habilidades que aprenderás en este proyecto incluyen:
- Autenticación de usuarios
- Diseño de esquemas y bases de datos
- Diseño de API RESTful
- Operaciones CRUD
- Manejo de errores
- Seguridad

## Requisitos

Se requiere desarrollar una API RESTful con los siguientes endpoints:

- Registro de usuario para crear un nuevo usuario
- Endpoint de login para autenticar al usuario y generar un token
- Operaciones CRUD para gestionar la lista de tareas
- Implementar autenticación de usuarios para permitir solo el acceso a usuarios autorizados
- Implementar manejo de errores y medidas de seguridad
- Usar una base de datos para almacenar los datos de usuarios y tareas (puedes usar cualquier base de datos de tu elección)
- Implementar validación adecuada de datos
- Implementar paginación y filtrado para la lista de tareas

A continuación se detallan los endpoints y los detalles de las solicitudes y respuestas:

### Registro de Usuario

Registrar un nuevo usuario usando la siguiente solicitud:

```
POST /register
{
  "name": "John Doe",
  "email": "john@doe.com",
  "password": "password"
}
```

Esto validará los detalles proporcionados, asegurará que el email sea único y almacenará los datos del usuario en la base de datos. Asegúrate de hashear la contraseña antes de almacenarla. Responde con un token que pueda usarse para autenticación si el registro es exitoso.

```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
}
```

El token puede ser un JWT o una cadena aleatoria que pueda usarse para autenticación. Dejamos a tu criterio los detalles de implementación.

### Login de Usuario

Autenticar al usuario usando la siguiente solicitud:

```
POST /login
{
  "email": "john@doe.com",
  "password": "password"
}
```

Esto validará el email y contraseña proporcionados, y responderá con un token si la autenticación es exitosa.

```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
}
```

### Crear un Item de Tarea

Crear un nuevo item de tarea usando la siguiente solicitud:

```
POST /todos
{
  "title": "Comprar víveres",
  "description": "Comprar leche, huevos y pan"
}
```

El usuario debe enviar el token recibido en el endpoint de login en el encabezado para autenticar la solicitud. Puedes usar el encabezado Authorization con el token como valor. Si el token falta o es inválido, responde con un error y código de estado 401.

```
{
  "message": "No autorizado"
}
```

Al crear exitosamente el item de tarea, responde con los detalles del item creado.

```
{
  "id": 1,
  "title": "Comprar víveres",
  "description": "Comprar leche, huevos y pan"
}
```

### Actualizar un Item de Tarea

Actualizar un item de tarea existente usando la siguiente solicitud:

```
PUT /todos/1
{
  "title": "Comprar víveres",
  "description": "Comprar leche, huevos, pan y queso"
}
```

Al igual que el endpoint de creación, el usuario debe enviar el token recibido. Además, asegúrate de validar que el usuario tenga permiso para actualizar el item (que sea el creador del item). Responde con un error y código 403 si el usuario no está autorizado.

```
{
  "message": "Prohibido"
}
```

Al actualizar exitosamente el item, responde con los detalles actualizados.

```
{
  "id": 1,
  "title": "Comprar víveres",
  "description": "Comprar leche, huevos, pan y queso"
}
```

### Eliminar un Item de Tarea

Eliminar un item de tarea existente usando la siguiente solicitud:

```
DELETE /todos/1
```

El usuario debe estar autenticado y autorizado para eliminar el item. Al eliminar exitosamente, responde con código de estado 204.

### Obtener Items de Tarea

Obtener la lista de items de tarea usando la siguiente solicitud:

```
GET /todos?page=1&limit=10
```

El usuario debe estar autenticado para acceder a las tareas y la respuesta debe estar paginada. Responde con la lista de items junto con los detalles de paginación.

```
{
  "data": [
    {
      "id": 1,
      "title": "Comprar víveres",
      "description": "Comprar leche, huevos y pan"
    },
    {
      "id": 2,
      "title": "Pagar cuentas",
      "description": "Pagar facturas de electricidad y agua"
    }
  ],
  "page": 1,
  "limit": 10,
  "total": 2
}
```

## Extras

- Implementar filtrado y ordenamiento para la lista de tareas
- Implementar pruebas unitarias para la API
- Implementar límite de tasa y throttling para la API
- Implementar mecanismo de refresh token para la autenticación

Este proyecto te ayudará a entender cómo diseñar e implementar una API RESTful con autenticación de usuarios. También aprenderás a trabajar con bases de datos, manejar errores e implementar medidas de seguridad.
