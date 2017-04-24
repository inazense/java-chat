# java-chat
Chat de ejemplo para demostración en Servicios y procesos

### Configuración del servidor
En el archivo configuracionSocket.properties debemos indicar la dirección IP donde estará alojado nuestro servidor del chat, y el puerto por el que nos conectaremos al mismo.

### Como ejecutarlo
El servidor chat se lanza desde servidor/AppServidor. Una única ejecución que quedará corriendo sin interfaz gráfica
Cada cliente lo lanzaremos desde cliente/AppCliente. Una ejecución por cada usuario

### Como usarlo
Al lanzar un nuevo cliente nos solicita un nombre de usuario. Si no se escribe ninguno generará uno por defecto: ProgramadorXXX, siendo XXX un número aleatorio por cada cliente.

##### Cualquier sugerencia o nueva implementación se puede hacer con un pull-request o abriendo un ticket de issue
