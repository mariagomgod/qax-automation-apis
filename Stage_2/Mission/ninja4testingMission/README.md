# Mission #2: Proyecto de Automatización de APIs con Token – Go Rest API

En esta misión vamos a utilizar la **[Go Rest Api](https://gorest.co.in/)**, una API pública que nos permite gestionar token, usuarios, commentarios y publicaciones.

Para comenzar a interactuar con la API, es necesario crear una cuenta y generar un token de acceso:

1. Crear cuenta: Ingresa a [Go Rest Signup](https://gorest.co.in/consumer/login)
   y registra un usuario.

2. Obtener token: Una vez registrado, genera tu token personal en [Go Rest Access Tokens](https://gorest.co.in/my-account/access-tokens)

Este token será obligatorio para autenticar tus peticiones a la API, permitiéndote crear usuarios, consultar datos y realizar acciones protegidas.

## Historia de Usuario – Flujos de Prueba API Go Rest

**Como** tester de APIs,
**Quiero** automatizar y validar la creación de usuarios, publicaciones y comentarios,
**Para** que pueda asegurarme de que la API funciona correctamente y cumple los contratos esperados.

## Casos de prueba principales:

1. Crear usuario – Validar que se pueda crear un usuario con datos válidos.

2. Crear publicación – Validar que un usuario pueda crear publicaciones asociadas a su cuenta.

3. Crear comentario – Validar que un comentario se pueda agregar a una publicación existente.

## Instrucciones

1. Crear proyecto desde cero

   Crear una carpeta para el proyecto.

2. Configurar el `pom.xml` para instalar dependencias

3. Diseñar tests individuales para cada endpoint

        Crear un test para crear usuarios y validar que se creó correctamente.

        Crear un test para crear publicaciones y validar que la publicación existe.

        Crear un test para crear comentarios y validar que se agregaron al post correcto.

4. Crear un test de flujo end-to-end

   En un solo caso de prueba:

        Crear un usuario.

        Crear una publicación asociada a ese usuario.

        Crear un comentario asociado a esa publicación.

        Validar en cada paso que los recursos se crearon correctamente usando GET/ID.

5. Ejecutar los tests y generar reportes

   Ejecutar todos los tests y verificar que los pasos pasen correctamente.

   Generar el reporte y log para revisar los resultados.

6. Subir el proyecto completo

   Reporte de ejecución


