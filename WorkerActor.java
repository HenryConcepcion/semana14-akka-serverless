package com.semana14;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class WorkerActor extends AbstractLoggingActor {

    public static Props props() {
        return Props.create(WorkerActor.class);
    }

    @Override
    public Receive createReceive() {

        return receiveBuilder()

                .match(TaskMessage.class, message -> {

                    System.out.println(
                            "Worker " + self().path().name()
                            + " recibió: " + message.getText()
                    );

                    // Simulación de un fallo
                    if (message.shouldSimulateFailure()) {

                        System.out.println(
                                "Worker " + self().path().name()
                                + " está simulando un fallo..."
                        );

                        throw new RuntimeException(
                                "Fallo intencional del Worker"
                        );
                    }

                    String resultado =
                            "Procesado correctamente: "
                            + message.getText().toUpperCase();

                    System.out.println(
                            "Worker " + self().path().name()
                            + " resultado: " + resultado
                    );
                })

                .build();
    }
}
