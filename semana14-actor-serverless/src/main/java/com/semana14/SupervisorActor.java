package com.semana14;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Supervisor que administra y supervisa tres workers.
 */
public class SupervisorActor extends AbstractLoggingActor {

    private ActorRef worker1;
    private ActorRef worker2;
    private ActorRef worker3;

    public static Props props() {
        return Props.create(SupervisorActor.class);
    }

    @Override
    public void preStart() {
        worker1 = getContext().actorOf(WorkerActor.props(), "worker1");
        worker2 = getContext().actorOf(WorkerActor.props(), "worker2");
        worker3 = getContext().actorOf(WorkerActor.props(), "worker3");

        System.out.println("Supervisor iniciado con 3 workers.");
    }

    @Override
    public OneForOneStrategy supervisorStrategy() {
        return new OneForOneStrategy(
            3,
            Duration.create(1, TimeUnit.MINUTES),
            DeciderBuilder
                .match(RuntimeException.class, exception -> {
                    System.out.println("Supervisor detectó un fallo: "
                            + exception.getMessage());
                    System.out.println("Reiniciando el Worker...");
                    return akka.actor.SupervisorStrategy.restart();
                })
                .matchAny(exception ->
                    akka.actor.SupervisorStrategy.resume())
                .build()
        );
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(TaskMessage.class, message -> {
                System.out.println("Supervisor recibió una nueva tarea.");

                worker1.tell(message, self());
                worker2.tell(
                    new TaskMessage(message.getText() + " - tarea 2", false),
                    self()
                );
                worker3.tell(
                    new TaskMessage(message.getText() + " - tarea 3", false),
                    self()
                );
            })
            .build();
    }
}
