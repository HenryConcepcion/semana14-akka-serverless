package com.semana14;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;
import java.util.HashMap;

public class LambdaHandler
        implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(
            Map<String, Object> input,
            Context context) {

        Map<String, Object> response =
                new HashMap<>();

        try {

            String task =
                    String.valueOf(
                            input.getOrDefault(
                                    "task",
                                    "tarea predeterminada"
                            )
                    );

            System.out.println(
                    "Lambda recibió la tarea: " + task
            );

            String result =
                    "Tarea recibida y enviada al sistema de actores: "
                    + task;

            response.put(
                    "statusCode",
                    200
            );

            response.put(
                    "message",
                    result
            );

        } catch (Exception e) {

            response.put(
                    "statusCode",
                    500
            );

            response.put(
                    "error",
                    e.getMessage()
            );
        }

        return response;
    }
}
