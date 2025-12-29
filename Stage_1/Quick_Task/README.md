Quick Task
---
- 📁 Carpeta: `Stage_1/Quick_Task/QuickTask1/src/main/java/MiFicha.java`
- **Objetivo:** Poner en práctica conceptos clave de automatización y testing de manera rápida y enfocada.
---
- 📂 **Framework utilizado:** Rest Assured 
- Para usar Rest Assured necesitas:
1. Instalar **Java 11**.
2. **IDE (Entorno de desarrollo)**: Como IntelliJ IDEA, Eclipse o Visual Studio Code (con soporte para Java).
3. Descargar **Maven**: Son herramientas que ayudan a instalar Rest Assured y otras dependencias automáticamente.
4. Instalar el plugin de **Cucumber For Java**.
---
- 📂 **Tecnologías utilizadas:** 
- **Java**: Lenguaje de programación donde se ejecutan las pruebas.
- **JUnit5 o TestNG**: Frameworks para ejecutar y organizar pruebas.
- **JSON**: Formato común en el que las APIs envían y reciben datos.
- **HTTP**: Protocolo de comunicación entre clientes y servidores.
---
## Ejercicio 1. Java ☕

**Instrucciones y ejecución de las pruebas:**

- Crea un archivo llamado `MiFicha.java`.
- Dentro, guarda tu información en variables:
    - Tu nombre (String)
    - Tu edad (int)
    - Si estás estudiando automatización en APIs (boolean)
    - Tu lista de hobbies (String[] o mejor ArrayList)
- Muestra la información en pantalla usando `System.out.println()`.
- Usa `.getClass().getSimpleName()` para imprimir el tipo de cada variable (tip: en Java no existe `typeof` como en JS).
- Pregunta al usuario (usando `Scanner`) cuál es su hobby favorito y agrégalo a tu lista de hobbies.
- Muestra cuántos hobbies hay en total con `.size()`.
- Cambia el valor de la edad sumándole 1 (como si hubieras cumplido años) y vuelve a mostrarlo en pantalla.
- **Cómo ejecutar las pruebas:**
  1. Desde tu IDE:
     Haz clic derecho sobre la clase MiFicha → Run / Ejecutar.

  2. Desde la terminal con Maven:

    ```bash
    mvn test
    mvn surefire-report:report
    ```
  - Maven ejecutará todas las pruebas en src/test/java.
  - Los resultados se mostrarán en la consola y se guardarán en target/surefire-reports.

## Ejercicio 2. Postman 📨

### 1. Instalación de Postman

Tienes dos formas de usar Postman:

### 🔹 Opción 1: Aplicación de escritorio
1. Ve a la página oficial: [postman](https://www.postman.com/downloads/)
2. Descarga la versión para tu sistema operativo (Windows, Mac o Linux).
3. Instala la aplicación y crea una cuenta gratuita (necesaria para guardar colecciones en la nube).

### 🔹 Opción 2: Cliente Online (sin instalar nada)
1. Ingresa a [a la versión web](https://www.postman.com/)
2. Haz clic en **Sign In** o **Sign Up** para registrarte.
3. Podrás usar Postman directamente en tu navegador con las mismas funcionalidades principales.

**Instrucciones para descargar la colección en Postman:**

- Descarga el fichero de colección `Api Testing - QAXPERT.postman_collection - QuickTask2.json` desde GitHub.
  - Pasos:
  1. Entra al repositorio de GitHub.
  2. Navega hasta el archivo de la colección (el .json de Postman).
  3. Haz clic en el nombre del archivo para abrirlo.
  4. Arriba a la derecha, pulsa el botón “Download raw file” o el icono de Download (a veces está bajo el botón “Raw” → clic derecho → Guardar enlace como...).
  5. Guárdalo en tu PC, por ejemplo en Descargas.
- Abre Postman en tu máquina o en el cliente web
- Arriba a la izquierda en el menú hamburguesa haz click en **File** -> **Import** 
- Selecciona **Upload Files** y carga el archivo `Api Testing - QAXPERT.postman_collection - QuickTask2.json`
- Verifica que la colección descargada aparece en tu panel izquierdo
- Ejecuta al menos una petición de la colección para confirmar que la importación fue exitosa

