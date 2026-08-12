package com.semana14;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Punto de entrada para probar localmente el sistema de actores.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println(" SEMANA 14 - MODELO DE ACTORES");
        System.out.println("==========================================");

        ActorSystem system = ActorSystem.create("ActorSystem");

        ActorRef supervisor = system.actorOf(
                SupervisorActor.props(),
                "supervisor"
        );

        System.out.println("Sistema de actores iniciado.");

        System.out.println("\nEnviando tarea normal...");
        supervisor.tell(
                new TaskMessage("procesar pedido 001", false),
                ActorRef.noSender()
        );

        sleep(2000);

        System.out.println("\nEnviando tarea que provocará un fallo...");
        supervisor.tell(
                new TaskMessage("procesar pedido 002", true),
                ActorRef.noSender()
        );

        sleep(3000);

        System.out.println("\nEnviando nueva tarea después del fallo...");
        supervisor.tell(
                new TaskMessage("procesar pedido 003", false),
                ActorRef.noSender()
        );

        sleep(3000);

        System.out.println("\nPrueba finalizada.");
        system.terminate();
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
