
# Sistema de Reporte de Incidentes



### Descripción

Este proyecto implementa un Sistema de Reporte de Incidentes para una empresa de soporte operativo que brinda servicios sobre diversas aplicaciones y sistemas operativos. El sistema permite gestionar incidentes desde su reporte hasta su resolución, involucrando a áreas como RRHH, Comercial y Mesa de Ayuda.

#### [<<<Enunciado del Trabajo Practico Final>>>](https://github.com/SantiagoPerezKay/Manejo_Incidentes_TP_UTN/blob/main/JAVA%20-%20Consigna%20Trabajo%20Pr%C3%A1ctico%20Integrador_.pdf)

### Tecnologías Utilizadas

El desarrollo se realizó en Java, haciendo uso de las siguientes tecnologías:

- **Lombok:** Utilizado para simplificar la creación de Setters y Getters, reduciendo la tarea repetitiva.
- **Hibernate:** Se empleó para mapear las clases con anotaciones JPA y permitir la persistencia de datos en una Base de Datos Relacional.
- **MySQL:** Base de datos utilizada para almacenar la información del sistema.




#### resumen: desde un menu por consola, se pueden realizar el clasico CRUD y algunas otras funcionalidades para resolver los requerimientos de una empresa de help Desk.

El sistema esta divido en 3 capaz (paquetes).
- Model (aunque considero que deberia llamarse entity ya que todas las clases contenidas son entidades).
- view (main)
- Controller (tambien considero que deberia haberlo llamado DAO y las clases nombredeclaseDAOIMPL ya que todas implementan una interfaz CRUD para la abstraccion)

