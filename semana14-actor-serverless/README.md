# Semana 14 - Modelo de Actores y Arquitecturas Serverless

Proyecto académico en Java con Akka y un handler compatible con AWS Lambda.

## Requisitos

- Java 17
- Maven 3.8+
- Internet para descargar dependencias Maven

## Estructura

- `TaskMessage.java`: mensaje de trabajo.
- `WorkerActor.java`: actor trabajador.
- `SupervisorActor.java`: supervisor y estrategia de recuperación.
- `Main.java`: demostración local.
- `LambdaHandler.java`: handler compatible con AWS Lambda.
- `pom.xml`: dependencias y configuración Maven.

## Ejecutar localmente

Desde la carpeta raíz:

```bash
mvn clean package
java -jar target/actor-serverless-1.0-SNAPSHOT.jar
```

La demostración:
1. Crea un supervisor y tres workers.
2. Procesa una tarea normal.
3. Envía una tarea que provoca un fallo intencional en `worker1`.
4. El supervisor detecta la excepción y reinicia el worker.
5. Se envía una nueva tarea y el sistema continúa.

## Resultado esperado

La consola debe mostrar mensajes similares a:

```text
Supervisor iniciado con 3 workers.
Sistema de actores iniciado.
Enviando tarea normal...
Worker worker1 resultado: Procesado correctamente: PROCESAR PEDIDO 001
Enviando tarea que provocará un fallo...
Worker worker1 está simulando un fallo...
Supervisor detectó un fallo: Fallo intencional del Worker
Reiniciando el Worker...
Enviando nueva tarea después del fallo...
Worker worker1 resultado: Procesado correctamente: PROCESAR PEDIDO 003
Prueba finalizada.
```

## Serverless

`LambdaHandler` implementa `RequestHandler` de AWS Lambda y puede recibir un objeto JSON con una propiedad `task`.

Ejemplo:

```json
{
  "task": "procesar pedido 100"
}
```

El despliegue real en AWS Lambda requiere configurar una función Lambda, subir el JAR generado y definir como handler:

```text
com.semana14.LambdaHandler::handleRequest
```

Este repositorio no afirma que el despliegue AWS haya sido realizado hasta ejecutar ese proceso en una cuenta de AWS.
