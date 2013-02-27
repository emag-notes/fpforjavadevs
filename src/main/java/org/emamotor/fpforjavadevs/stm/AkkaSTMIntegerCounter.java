package org.emamotor.fpforjavadevs.stm;

import akka.stm.Atomic;
import akka.stm.Ref;

public class AkkaSTMIntegerCounter {

    private final Ref<Integer> ref = new Ref<Integer>(0);
    
    public int counter() {
        
        return new Atomic<Integer>() {

            @Override
            public Integer atomically() {
                int inc = ref.get() + 1;
                ref.set(inc);
                return inc;
            }
            
        }.execute();
        
    }
    
    public static void main(String[] args) {
        AkkaSTMIntegerCounter counterRef = new AkkaSTMIntegerCounter();
        System.out.println(counterRef.counter());
        System.out.println(counterRef.counter());
    }
}
