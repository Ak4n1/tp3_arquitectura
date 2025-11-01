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
- `GET /estudiantes/libreta/{numeroLibreta}` - Obtener estudiante por número de libreta 12345
- `GET /estudiantes/genero/{genero}` - Obtener estudiantes por género (Masculino, Femenino u Otro)
- `GET /estudiantes/carrera/{carreraId}/ciudad/{ciudadDeResidencia}` - Obtener estudiantes de una carrera filtrados por ciudad de residencia (si la ciudad tiene espacios usar %20 en la URL)

### Carreras
- `GET /carreras` - Obtener todas las carreras
- `POST /carreras/crear` - Crear carrera
- `GET /carreras/{id}` - Obtener carrera por ID
- `PUT /carreras/{id}` - Actualizar carrera
- `DELETE /carreras/{id}` - Eliminar carrera
- `POST /carreras/matricular` - Matricular estudiante en carrera
- `GET /carreras/por-cantidad-inscriptos` - Carreras con estudiantes inscriptos ordenadas por cantidad (descendente)
- `GET /carreras/reporte` - Reporte de carreras con inscriptos y egresados por año, ordenadas alfabéticamente 

---

## Lo que hice (matias):
-Creado el endpoint para ordenar estudiantes, integrado como dto para ignorar el error de serializacion porque la list estudiante contiene la relacion LAZY
-Mejora en el endpoint para que sea mas entendible, si estamos en estudiantes/ es obvio que despues vamos a ordenar estudiantantes, se evita el endpoint repetitivo
-Logica del endpoint pasada de controller (mala practica) al ServiceEstudiante
-Implementado el buscar por libreta universitaria, devuelve un solo estudiante porque cada libreta es unica
-Implementado el buscar por genero, devuelve todos los estudiantes de ese genero (Masculino, Femenino u Otro)
-Implementado buscar estudiantes de una carrera filtrados por ciudad de residencia, usando path variables para ambos parametros
-Implementado buscar carreras por cantidad de inscriptos ordenadas de manera descendente
-se creo un nuevo responseDto para carreras (ResponseCarreraDTO) para evitar problemas de lazy loading
-se implemento un metodo que inserta los elementos de la lista y lo convierte en una lista de dto asi no rompe (ServiceCarreraImp line 217)
-se creo ReporteCarreraDTO para el reporte de carreras con inscriptos y egresados por año no pude eliminar los espacios asique hay que poner %20 en caso de que exista una ciudad con espacios entre medio
-implementado el reporte de carreras que muestra inscriptos y egresados agrupados por año, ordena carreras alfabeticamente y los años



