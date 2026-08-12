package com.semana14;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                " SEMANA 14 - MODELO DE ACTORES"
        );

        System.out.println(
                "=========================================="
        );

        ActorSystem system =
                ActorSystem.create("ActorSystem");

        ActorRef supervisor =
                system.actorOf(
                        SupervisorActor.props(),
                        "supervisor"
                );

        System.out.println(
                "Sistema de actores iniciado."
        );

        // Primera tarea normal
        System.out.println(
                "\nEnviando tarea normal..."
        );

        supervisor.tell(
                new TaskMessage(
                        "procesar pedido 001",
                        false
                ),
                ActorRef.noSender()
        );

        // Esperar un poco para visualizar el procesamiento
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Segunda tarea con fallo intencional
        System.out.println(
                "\nEnviando tarea que provocará un fallo..."
        );

        supervisor.tell(
                new TaskMessage(
                        "procesar pedido 002",
                        true
                ),
                ActorRef.noSender()
        );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Tercera tarea después del fallo
        System.out.println(
                "\nEnviando nueva tarea después del fallo..."
        );

        supervisor.tell(
                new TaskMessage(
                        "procesar pedido 003",
                        false
                ),
                ActorRef.noSender()
        );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
                "\nPrueba finalizada."
        );

        system.terminate();
    }
}
