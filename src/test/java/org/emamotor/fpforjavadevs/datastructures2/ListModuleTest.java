package org.emamotor.fpforjavadevs.datastructures2;

import static org.emamotor.fpforjavadevs.datastructures2.ListModule.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.emamotor.fpforjavadevs.datastructures2.ListModule.List;
import org.emamotor.fpforjavadevs.functions.Function1;
import org.emamotor.fpforjavadevs.functions.Function1Void;
import org.emamotor.fpforjavadevs.functions.Function2;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ListModuleTest {

    public static class ForeachExample {
        
        @Test
        public void foreachExample() throws Exception {
            // SetUp
            String[] args = {"A", "B", "C"};
            // Exercise
            // Verify
            argsToList(args).foreach(new Function1Void<String>(){

                @Override
                public void apply(String arg) {
                    System.out.println("You entered: " + arg);
                }
                
            });
        }
        
        private static List<String> argsToList(String[] args) {
            List<String> result = emptyList();
            for(String arg : args) {
                result = list(arg, result);
            }
            return result;
        }
        
    }
    
    public static class CombinatorExample {
        
        private static final List<Integer> EMPTYLI = emptyList();
        
        @Test
        public void higherOrderFunctionCombinatorExample() throws Exception {
            // SetUp
            List<Integer> listI = list(1, list(2, list(3, list(4, list(5, list(6, EMPTYLI))))));
            
            // Exercise
            Integer sum = listI.filter(new Function1<Integer, Boolean>() {

                @Override
                public Boolean apply(Integer i) {
                    return i % 2 == 0;
                }

            }).map(new Function1<Integer, Integer>() {

                @Override
                public Integer apply(Integer i) {
                    return i * 2;
                }

            }).foldLeft(0, new Function2<Integer, Integer, Integer>() {

                @Override
                public Integer apply(Integer seed, Integer item) {
                    return seed + item;
                }

            });
            
            // Verify
            assertThat(sum, is(24));
        }
        
    }
    
}
