package org.emamotor.fpforjavadevs.option;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OptionTest {

    private List<Option<String>> names = null;

    @Before
    public void setUp() throws Exception {
        names = new ArrayList<Option<String>>();
        names.add(new Some<String>("Dean"));
        names.add(new None<String>());
        names.add(new Some<String>("Wampler"));
    }

    @Test
    public void getOrElseUsesValueForSomeAndAlternativeForName() throws Exception {
        String[] expected = { "Dean", "Unknown!", "Wampler" };

        System.out.println("*** Using getOrElse:");
        for (int i = 0, size = names.size(); i < size; i++) {
            Option<String> name = names.get(i);
            String value = name.getOrElse("Unknown!");
            System.out.println(name + ": " + value);
            assertThat(value, is(expected[i]));
        }
    }

    @Test
    public void hasNextWithGetUsesOnlyValuesForSomes() throws Exception {
        String[] expected = { "Dean", null, "Wampler" };

        System.out.println("*** Using hasValue:");
        for (int i = 0, size = names.size(); i < size; i++) {
            Option<String> name = names.get(i);
            if (name.hasValue()) {
                String value = name.get();
                System.out.println(name + ": " + value);
                assertThat(value, is(expected[i]));
            }
        }
    }
    
    static Option<String> wrap(String s) {
        if (s == null) {
            return new None<String>();
        } else {
            return new Some<String>(s);
        }
    }
    
    @Test
    public void exampleMethodReturningOption() throws Exception {
        System.out.println("*** Method that Returns an Option");
        Option<String> opt1 = wrap("hello!");
        System.out.println("hello! -> " + opt1);
        assertEquals(Some.class, opt1.getClass());
        assertThat(opt1.get(), is("hello!"));
        
        Option<String>opt2 = wrap(null);
        System.out.println("null -> " + opt2);
        assertEquals(None.class, opt2.getClass());
        assertThat(opt2.getOrElse("str"), is("str"));
    }
}
