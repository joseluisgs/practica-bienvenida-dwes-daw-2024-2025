Hacemos una aplicación que se llamara torneos_tenis.jar, aceptando como maximo dos parametros donde 
fichero_entrada.csv contendra los datos de los jugadores de tenis que participaran en la competicion y 
fichero_salida.xxx para extensiones que solo pueden ser .csv .json o xml. 
Teniendo en cuenta que nuestro programa debe de tener una base de datos pudiendo ser tanto SQlite o H2.

La aplicacion permite a traves de las consultas poder consultar las estadisticas de cada tenista filtrando por;
altura, peso, mano dominante o poder agrupar por pais.

    1. S-Single Responsibility Principle (SRP) : Según este principio “una clase debería tener una, y solo una, razón para cambiar”
    2. O-Principio de Abierto/Cerrado: “Deberías ser capaz de extender el comportamiento de una clase, sin modificarla”. En otras palabras: las clases que usas deberían estar abiertas para poder extenderse y cerradas para modificarse.
    3. L- Principio de Sustitución de Liskov: “las clases derivadas deben poder sustituirse por sus clases base”.
    4. I- Principio de Segregación de la Interfaz: “Haz interfaces que sean específicas para un tipo de cliente”, es decir, para una finalidad concreta.
    5. D- Principio de Inversión de Dependencias: “Depende de abstracciones, no de clases concretas”.