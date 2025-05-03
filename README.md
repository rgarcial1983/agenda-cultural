Descripción de Cada Paquete:
config: Contiene las clases de configuración de Spring, como la configuración de la seguridad, la base de datos, o cualquier otro componente.

controller: Almacena las clases que manejan las solicitudes HTTP entrantes y devuelven respuestas. Aquí se definen los endpoints de la API REST.

dto: Contiene los objetos de transferencia de datos, que son utilizados para transferir datos entre el controlador y el servicio, especialmente útil al devolver o recibir datos en formato JSON.

exception: Describe las excepciones personalizadas que podrían lanzarse durante la ejecución del programa y los manejadores de excepción globales.

model: Incluye las clases que representan las entidades del dominio, es decir, los modelos que se mapean a tablas de la base de datos.

repository: Contiene interfaces que extienden de JpaRepository o CrudRepository, facilitando el acceso a datos y las operaciones CRUD sobre las entidades.

service: Aquí se implementa la lógica de negocio. Las clases en este paquete pueden ser inyectadas en los controladores para ejecutar la lógica necesaria al recibir solicitudes.
