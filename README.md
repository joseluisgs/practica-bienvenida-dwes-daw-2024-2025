# Práctica de bienvenida a DWES y DAW 2024-2025

- [Práctica de bienvenida a DWES y DAW 2024-2025](#práctica-de-bienvenida-a-dwes-y-daw-2024-2025)
  - [Acerca de](#acerca-de)
  - [Enunciado](#enunciado)
  - [Restricciones](#restricciones)
  - [Entrega](#entrega)
  - [Recursos](#recursos)


## Acerca de

Esta práctica tiene como objetivo ser una evaluación inicial sobre contenidos de 1º DAW.

Como bien sabes, para programar al lado de servidor debemos tener unos conceptos mínimos siempre en mente.

Te puedes apoyar en los apuntes de 1º DAW de Programación, Entornos de Desarrollo, Bases de Datos y Lenguajes de Marcas y Sistemas de Información. Pero yo no he sido el profesor de todos esos módulos, por lo que deberás revisarlos y responsabilizarte de lo aprendido en 1º DAW ya sea en este instituto o en otro.

## Enunciado
como futuros desarrolladores de backend, te invito a que hagas una pequeña práctica de bienvenida a DWES y DAW.
Para ello debemos hacer un aplicación con la siguiente funcionalidad y tenga las siguientes restricciones:
- Es una aplicación en consola, que se llamara torneo_tenis.jar
  
- Esta aplicación acepta como máximo dos parámetros: fichero_entrada.csv y fichero_salida.xxx
  - fichero_entrada.csv contendrá los datos de los jugadores de tenis que participaran en la competición y siempre es obligatorio. Su extensión válida es .csv. El path debe ser válido en sistemas de archivos del sistema operativo actual.
  - fichero_salida.xxx solo puede tener de extensión .csv, .json o .xml. El path debe ser válido en sistemas de archivos del sistema operativo actual. Si no escribes un path, el fichero se guardará en el directorio actual, con json como formato por defecto y con el nombre torneo_tenis.json.
  
- El programa debe contener una base de datos en ficheros del tipo SQLite o H2 para almacenar la información, para ello tendremos una tabla llamada tenistas con la siguiente información:
  - id (autonumérico)
  - nombre: nombre del tenista completo
  - pais: nombre del país
  - altura: altura del tenista en cm
  - peso: peso del tenista en kg
  - puntos: puntos totales del tenista
  - mano: mano del tenista, DIESTRO o ZURDO
  - fecha_nacimiento: fecha de nacimiento del tenista, en formato AAAA-MM-DD (ISO 8601)
  - created_at: fecha de creación del tenista, en formato AAAA-MM-DDTHH:MM:SS.SSSSSSS (ISO 8601)
  - updated_at: fecha de actualización del tenista, en formato AAAA-MM-DDTHH:MM:SS.SSSSSSS (ISO 8601)

- El programa debe leer el fichero de entrada y insertar los datos en la base de datos. El fichero de entrada debe ser abierto en modo lectura solamente analizando que todos los datos son válidos y están en formato correcto antes de ser insertados.

- Además, tendremos una caché FIFO de la base de datos con tamaño de 5 elementos.

- El programa debe generar un fichero de salida con la información de la base de datos.

- Antes de ofrecer la salida debe mostrar en la consola las siguientes consultas usando api de colecciones:
  - tenistas ordenados con ranking, es decir, por puntos de mayor a menor
  - media de altura de los tenistas
  - media de peso de los tenistas
  - tenista más alto
  - tenistas de España
  - tenistas agrupados por país
  - número de tenistas agrupados por país y ordenados por puntos descendente
  - numero de tenistas agrupados por mano dominante y puntuación media de ellos
  - puntuación total de los tenistas agrupados por país
  - país con más puntuación total
  - tenista con mejor ranking de España.
        
## Restricciones
- Se debe programar siguiendo el patrón ROP (Railway Oriented Programming) con un sistema de errores tipados al dominio, o usar excepciones dentro del dominio.
- En todo momento debe haber un validador de datos que verifique que los datos antes de ser introducidos sean correctos.
- Se deben implementar el repositorio de almacenamiento con las operaciones de selección completa, selección por id, selección por país, selección por ranking, inserción, actualización y borrado. Las salidas de listado deberán estar ordenadas por ranking ascendente. El ranking se calcula en base a puntos, tendrá mayor ranking cuando tengan más puntos.
- Se debe testear todos los elementos realizados y asegurar un 90% de cobertura de los elementos funcionales.
- Se debe usar un método manual o automatizado de inyección de dependencias.
- La base de datos debe vaciarse a iniciarse la aplicación.
- Los parámetros de configuración y uso de la base de datos deben estar en un fichero de properties.
- El despliegue de la aplicación debe ser en un ejecutable .jar con el nombre torneo_tenis.jar.
- Se debe comentar tu código y obtener esta documentación en html (puedes usar herramientas como Dokka o JavaDoc).
- Se debe trabajar con Git y Github siguiendo GitFlow.
- El lenguaje de programación debe ser Kotlin o Java y usar JDK 17 o superior.
- Se debe implementar las operaciones de colecciones usando la API de colecciones funcional de Kotlin o Java.
- La consola en su ejecución debe tener colores.

## Entrega
- Proyecto mediante Pull Request a este directorio en el directorio entregas. El subdirectorio que debes hacer es apellido_apellido_nombre.
- En ficho directorio debe estar el proyecto y un fichero README.md.
- En el fichero README.md debe tener:
  - Descripción del proyecto.
  - Arquitectura usada.
  - Enumerar los 5 principios SOLID y poner 2 ejemplos con capturas de tu código donde los usases.
  - Justificación de las librerías usadas.

## Recursos
- Apuntes de Programación 1º DAW: https://github.com/joseluisgs/Programacion-00-2023-2024
- Apuntes de Entornos de Desarrollo 1º DAW: https://github.com/joseluisgs/EntornosDesarrollo-00-2023-2024
- Documentación de los lenguajes y librerías que quieras usar.
- Puedes usar ChatGPT, CoPilot, Tabnine, Codeium o similar para ayudarte, consultar u hacerte tus apuntes, pero recuerda que en los exámenes no tendrá sin internet, así que: aprende y comprende el porqué de las cosas.
