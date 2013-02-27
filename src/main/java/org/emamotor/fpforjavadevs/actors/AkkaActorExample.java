package org.emamotor.fpforjavadevs.actors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import akka.actor.ActorRef;
import static akka.actor.Actors.*;
import akka.actor.UntypedActor;

public class AkkaActorExample {

    // Server Code
    static class MemoryActor extends UntypedActor {
        final Map<String, Date> seen = new HashMap<String, Date>();

        @Override
        public void onReceive(Object messageObject) {
            // assume only String for simplifying
            String message = messageObject.toString();
            if (message.equals("DUMP")) {
                getContext().replySafe(seen.toString());
            } else {
                Date date = new Date();
                seen.put(message.toString(), date);
                getContext().replySafe("'" + message + "' recorded at " + date);
            }
        }
    }
    
    public static void main(String[] args) {
        ActorRef remActor = actorOf(MemoryActor.class).start();
        for (String arg : args) {
            // Client Code
            Object response = remActor.sendRequestReply(arg);
            System.out.println("Reply received: " + response);
        }
        Object response = remActor.sendRequestReply("DUMP");
        System.out.println("Dump of remembered strings: " + response);
        System.exit(0);
    }
}
