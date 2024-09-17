# Ejercicio-Tenistas

Ejericio Tenistas hecho por Jaime León Mulero.

En este ejercicio he creado una aplicación la cual lee un fichero csv el cual indicamos al ejecutar el programa (fichero valido en carpeta data dentro del proyecto) y este lo valida y lee para añadir toda la información a la base de datos SQLite. Una vez la información en la base de datos, la aplicación ejecuta una serie de consultas utilizando la api de colecciones de Kotlin y finalmente almacena los datos de la base de datos en un fichero que indicamos como segundo argumento al ejecutar la aplicación. En caso de que este fichero no sea válido o no lo hayamos indicado, generará un archivo .json por defecto en la carpeta data del proyecto.

S – Single Responsibility Principle (SRP)
O – Open/Closed Principle (OCP)
L – Liskov Substitution Principle (LSP)
I – Interface Segregation Principle (ISP)
D – Dependency Inversion Principle (DIP)

implementation("org.xerial:sqlite-jdbc:3.45.2.0") --> Dependencia de SQLite (Base de datos)
implementation("app.cash.sqldelight:sqlite-driver:2.0.2") --> Dependencia de SqlDeLightManager (Manejo de base de datos)
implementation("org.mybatis:mybatis:3.5.13") --> Dependencia de scripts de bbdd (usada para poder ejecutar los scripts de la bbdd)
implementation("org.lighthousegames:logging:1.3.0") --> Dependencia de loggers
implementation("ch.qos.logback:logback-classic:1.5.0") --> Dependencia de loggers
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0") --> Dependencia para poder hacer las clases serializables y poder escribir archivos json con estas
implementation(platform("io.insert-koin:koin-bom:3.5.6")) --> Dependencia de inyección de dependencias
implementation("io.insert-koin:koin-core") --> Dependencia de inyección de dependencias
implementation("io.insert-koin:koin-test") --> Dependencia de inyección de dependencias
implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0") --> Dependencia para devolver result con opción Ok y opción de error
implementation("io.github.pdvrieze.xmlutil:core-jvm:0.86.3") --> Dependencia para poder hacer las clases serializables y poder escribir archivos xml con estas
implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.86.3") --> Dependencia para poder hacer las clases serializables y poder escribir archivos xml con estas
implementation("com.github.ajalt.mordant:mordant:2.0.0-beta9") --> Dependencia para añadir colores en la terminal