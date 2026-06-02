# 📚 Biblioteca API

REST API para la gestión de una biblioteca, desarrollada con Spring Boot como proyecto de aprendizaje de Java y desarrollo backend.

---

## 🚀 Tecnologías

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **SQL Server**
- **Maven**

---

## 📁 Estructura del proyecto

```
src/
└── main/
    └── java/
        └── com.example.biblioteca/
            ├── controller/        # Controladores REST
            │   ├── AutorController
            │   ├── CategoriaController
            │   └── LibroController
            ├── dto/               # Objetos de transferencia de datos
            │   ├── AutorInputDTO / AutorOutputDTO
            │   ├── CategoriaInputDTO / CategoriaOutputDTO
            │   └── LibroInputDTO / LibroOutputDTO
            ├── exception/         # Manejo global de errores
            │   ├── GlobalExceptionHandler
            │   ├── ConflictoException
            │   ├── PeticionInvalidaException
            │   ├── RecursoNoEncontradoException
            │   └── ErrorResponse
            └── model/             # Entidades JPA
```

---

## ✨ Funcionalidades

- ✅ CRUD completo de **Libros**
- ✅ CRUD completo de **Autores**
- ✅ CRUD completo de **Categorías**
- ✅ Manejo global de excepciones con respuestas de error estructuradas
- ✅ Patrón DTO para separar la capa de datos de la API
- ✅ Relación **ManyToOne** entre Libro y Autor
- ✅ Relación **ManyToMany** entre Libro y Categoría (tabla intermedia `Libro_Categoria`)

---

## 🗄️ Diagrama de base de datos

```
Autor (id, nombre, nacionalidad)
  │
  │ 1:N
  ▼
Libro (id, titulo, anioPublicacion, autor_id)
  │
  │ N:M
  ▼
Libro_Categoria (libro_id, categoria_id)
  │
  │ N:1
  ▼
Categoria (id, nombre)
```

---

## 🔗 Endpoints principales

### Libros
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/libros` | Listar todos los libros |
| GET | `/libros/{id}` | Obtener libro por ID |
| POST | `/libros` | Crear nuevo libro |
| PUT | `/libros/{id}` | Actualizar libro |
| DELETE | `/libros/{id}` | Eliminar libro |

### Autores
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/autores` | Listar todos los autores |
| GET | `/autores/{id}` | Obtener autor por ID |
| POST | `/autores` | Crear nuevo autor |
| PUT | `/autores/{id}` | Actualizar autor |
| DELETE | `/autores/{id}` | Eliminar autor |

### Categorías
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/categorias` | Listar todas las categorías |
| GET | `/categorias/{id}` | Obtener categoría por ID |
| POST | `/categorias` | Crear nueva categoría |
| PUT | `/categorias/{id}` | Actualizar categoría |
| DELETE | `/categorias/{id}` | Eliminar categoría |

---

## ⚙️ Cómo correrlo localmente

### Requisitos previos
- Java 17 o superior
- SQL Server corriendo (o SQL Server Express)
- Maven

### Pasos

1. Clona el repositorio:
```bash
git clone https://github.com/kevinramos10/biblioteca-api.git
cd biblioteca-api
```

2. Configura la base de datos en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=biblioteca_api;encrypt=true;trustServerCertificate=true
spring.datasource.username=TU_USUARIO_DB
spring.datasource.password=TU_PASSWORD
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
```

3. Crea la base de datos en SQL Server y ejecuta el script:
```sql
CREATE DATABASE biblioteca_api;
USE biblioteca_api;

-- Tabla Autor
CREATE TABLE Autor (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL,
    nacionalidad NVARCHAR(50) NOT NULL
);

-- Tabla Categoria
CREATE TABLE Categoria (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Libro
CREATE TABLE Libro (
    id INT IDENTITY(1,1) PRIMARY KEY,
    titulo NVARCHAR(100) NOT NULL,
    anioPublicacion INT NOT NULL,
    autor_id INT NOT NULL,
    CONSTRAINT FK_Libro_Autor FOREIGN KEY (autor_id) REFERENCES Autor(id)
);

-- Tabla intermedia Libro-Categoria (ManyToMany)
CREATE TABLE Libro_Categoria (
    libro_id INT NOT NULL,
    categoria_id INT NOT NULL,
    PRIMARY KEY (libro_id, categoria_id),
    CONSTRAINT FK_LC_Libro FOREIGN KEY (libro_id) REFERENCES Libro(id),
    CONSTRAINT FK_LC_Categoria FOREIGN KEY (categoria_id) REFERENCES Categoria(id)
);

-- Datos de ejemplo
INSERT INTO Autor (nombre, nacionalidad) VALUES ('Gabriel García Márquez', 'Colombiano');
INSERT INTO Categoria (nombre) VALUES ('Realismo mágico'), ('Novela');
INSERT INTO Libro (titulo, anioPublicacion, autor_id) VALUES ('Cien años de soledad', 1967, 1);
INSERT INTO Libro_Categoria (libro_id, categoria_id) VALUES (1, 1), (1, 2);
```

4. Corre la aplicación:
```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

---

## 🧠 Lo que aprendí haciendo este proyecto

- Creación de una REST API con Spring Boot
- Uso de Spring Data JPA para la capa de persistencia
- Patrón DTO para separar la lógica de negocio de la API
- Manejo centralizado de errores con `@ControllerAdvice`
- Relaciones entre entidades con JPA/Hibernate (`@ManyToOne`, `@ManyToMany`)
- Configuración de SQL Server con Spring Boot

---

## 👨‍💻 Autor

Desarrollado como proyecto de práctica para aprender Java y Spring Boot.
