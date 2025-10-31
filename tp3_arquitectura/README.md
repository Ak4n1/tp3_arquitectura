# TP3 Arquitectura

## Configuración de Base de Datos

Ejecutar el script SQL para crear la base de datos:
```sql
scripts/create_database.sql
```

Configurar en `application.properties`:
```properties
spring.datasource.url=jdbc:mariadb://localhost:3360/tp3_arquitectura
spring.datasource.username=tp3_user
spring.datasource.password=tp3_password
```

## Endpoints

### Estudiantes
- `POST /estudiantes/crear` - Crear estudiante
- `GET /estudiantes/ordenar-Todos/{criterio}` - Obtener estudiantes ordenados (ASC/DESC)

### Carreras
- `GET /carreras` - Obtener todas las carreras
- `POST /carreras/crear` - Crear carrera
- `GET /carreras/{id}` - Obtener carrera por ID
- `PUT /carreras/{id}` - Actualizar carrera
- `DELETE /carreras/{id}` - Eliminar carrera
- `POST /carreras/matricular` - Matricular estudiante en carrera

---

## Lo que hice (matias):
-creado el endpoint para ordenar estudiantes, integrado como dto para ignorar el error de serializacion porque la list estudiante contiene la relacion LAZY
-mejora en el endpoint para que sea mas entendible, si estamos en estudiantes/ es obvio que despues vamos a ordenar estudiantantes, se evita el endpoint repetitivo
-Logica del endpoint pasada de controller (mala practica) al ServiceEstudiante

