# inDrive Application

Aplicación de transporte compartido desarrollada con Spring Boot con interfaz moderna y amigable.

## Configuración de la Base de Datos

La aplicación está configurada para usar **MySQL** como base de datos.

### Configuración de MySQL
Antes de ejecutar la aplicación, asegúrate de tener MySQL instalado y configurado:

1. **Instalar MySQL** (si no está instalado):
   - Descarga MySQL desde: https://dev.mysql.com/downloads/mysql/
   - Instálalo con las opciones predeterminadas
   - Configura el usuario `root` con tu contraseña

2. **Crear la base de datos** (opcional, la aplicación la creará automáticamente):
   ```sql
   CREATE DATABASE indrive_db;
   ```

3. **Configurar la conexión** en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/indrive_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña
   ```

## Cómo Ejecutar la Aplicación

### Opción 1: Usando Maven
```bash
mvn spring-boot:run
```

### Opción 2: Usando el archivo batch (Windows)
```bash
run.bat
```

### Opción 3: Compilar y ejecutar el JAR
```bash
mvn clean package
java -jar target/indriveapp-1.0.0.jar
```

## Acceso a la Aplicación

Una vez iniciada la aplicación:
- URL principal: http://localhost:8080
- Login: http://localhost:8080/auth/login
- Registro: http://localhost:8080/auth/registro

## Características de la Interfaz

### Diseño Moderno
- Interfaz con gradientes modernos y animaciones suaves
- Diseño responsivo para móviles y tablets
- Iconos de Font Awesome para mejor experiencia visual
- Tipografía Poppins para mejor legibilidad

### Interactividad
- Animaciones de entrada y transiciones suaves
- Efectos hover en botones y tarjetas
- Validación de formularios en tiempo real
- Indicador de fortaleza de contraseña
- Notificaciones animadas

### Experiencia de Usuario
- Navegación intuitiva
- Feedback visual en todas las acciones
- Formularios con iconos descriptivos
- Diseño limpio y organizado

## Funcionalidades

### Pasajeros
- Dashboard con estadísticas
- Seleccionar destino
- Proponer precio
- Ver conductores disponibles
- Seguimiento del viaje
- Pago
- Calificación

### Conductores
- Dashboard con estadísticas en tiempo real
- Ver solicitudes de viajes
- Enviar ofertas
- Viaje actual con seguimiento
- Historial de viajes
- Ganancias y reportes

### Administradores
- Dashboard con métricas clave
- Gestión de usuarios
- Gestión de viajes
- Estadísticas y reportes
- Configuración del sistema

## Estructura del Proyecto

- `src/main/java/com/indriveapp/`
  - `controller/` - Controladores Spring MVC
  - `service/` - Servicios de negocio
  - `repository/` - Repositorios JPA
  - `model/` - Entidades JPA
  - `config/` - Configuración de Spring

- `src/main/resources/`
  - `templates/` - Plantillas Thymeleaf modernas
  - `static/` - Recursos estáticos (CSS, JS, imágenes)
  - `application.properties` - Configuración de la aplicación

## Tecnologías Utilizadas

- Spring Boot 3.2.0
- Spring Data JPA
- Thymeleaf
- MySQL Database
- Maven
- Font Awesome (iconos)
- Google Fonts (Poppins)

## Requisitos del Sistema

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- Navegador web moderno (Chrome, Firefox, Safari, Edge)

## Solución de Problemas

### Error de conexión a MySQL
- Verifica que MySQL esté ejecutándose
- Confirma que el usuario y contraseña son correctos
- Asegúrate de que el puerto 3306 esté disponible

### Error de compilación
- Ejecuta `mvn clean` antes de compilar
- Verifica que Java 17 esté instalado
- Actualiza las dependencias con `mvn clean install`

## Notas Importantes

- La aplicación crea automáticamente las tablas en MySQL al iniciar
- Las contraseñas se almacenan en texto plano (para producción, usar encriptación)
- La interfaz está optimizada para dispositivos móviles
- Todas las páginas tienen animaciones y efectos interactivos
