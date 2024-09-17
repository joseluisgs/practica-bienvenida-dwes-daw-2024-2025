Este ejercicio consiste en desarrollar una aplicación de consola llamada torneo_tenis.jar, cuyo objetivo es procesar y gestionar los datos de jugadores de tenis. El programa debe recibir un fichero de entrada en formato CSV con información de los jugadores, validar estos datos e insertarlos en una base de datos SQLite o H2. Además, debe generar un archivo de salida en formato CSV, JSON o XML con los resultados.

El sistema permite realizar consultas sobre los datos, como listar jugadores por ranking, obtener estadísticas como la media de altura y peso, o agrupar jugadores por país. También implementa una caché de 5 elementos para mejorar el rendimiento y sigue el patrón Railway Oriented Programming (ROP) para la gestión de errores. Todo debe estar programado en Kotlin o Java, usando JDK 17 o superior.


1. S - Single Responsibility Principle (SRP)
Principio de Responsabilidad Única:
Cada clase debe tener una única responsabilidad o razón para cambiar. Esto significa que una clase debe encargarse de una única tarea o funcionalidad dentro del sistema.

2. O - Open/Closed Principle (OCP)
Principio Abierto/Cerrado:
El software debe estar abierto para extensión, pero cerrado para modificación. Esto significa que debes poder añadir nuevas funcionalidades sin modificar el código existente.

3. L - Liskov Substitution Principle (LSP)
Principio de Sustitución de Liskov:
Los objetos de una subclase deben poder reemplazar a los de su superclase sin alterar el comportamiento correcto del programa. En otras palabras, las clases derivadas deben ser completamente intercambiables con sus clases base.

4. I - Interface Segregation Principle (ISP)
Principio de Segregación de Interfaces:
Los clientes no deben depender de interfaces que no utilizan. Es mejor tener interfaces específicas y pequeñas que grandes y generales. Esto evita que las clases implementen métodos que no necesitan.

5. D - Dependency Inversion Principle (DIP)
Principio de Inversión de Dependencias:
Los módulos de alto nivel no deben depender de módulos de bajo nivel, sino de abstracciones. Tanto los módulos de alto como de bajo nivel deben depender de interfaces o abstracciones, no de implementaciones concretas.
