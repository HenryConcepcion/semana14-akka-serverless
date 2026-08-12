package com.semana14;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler compatible con AWS Lambda.
 *
 * Entrada esperada:
 * {"task":"procesar pedido 100"}
 *
 * Nota: este handler demuestra la interfaz serverless.
 * El despliegue real en AWS Lambda es un paso separado.
 */
public class LambdaHandler
        implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(
            Map<String, Object> input,
            Context context) {

        Map<String, Object> response = new HashMap<>();

        try {
            String task = String.valueOf(
                    input.getOrDefault("task", "tarea predeterminada")
            );

            System.out.println("Lambda recibió la tarea: " + task);

            response.put("statusCode", 200);
            response.put(
                    "message",
                    "Tarea recibida por Lambda: " + task
            );
        } catch (Exception e) {
            response.put("statusCode", 500);
            response.put("error", e.getMessage());
        }

        return response;
    }
}
