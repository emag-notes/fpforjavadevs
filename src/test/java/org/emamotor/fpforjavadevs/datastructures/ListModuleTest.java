package org.emamotor.fpforjavadevs.datastructures;

import static org.emamotor.fpforjavadevs.datastructures.ListModule.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.emamotor.fpforjavadevs.datastructures.ListModule.EmptyListHasNoHead;
import org.emamotor.fpforjavadevs.datastructures.ListModule.List;
import org.junit.Test;

public class ListModuleTest {

    List<String> EMPTYLS = emptyList(); 
    List<Long>   EMPTYLL = emptyList(); 

    @Test
    public void emptyListsOfCollectionsAreSupported() throws Exception {
        // SetUp
        // Exercise
        List<List<Long>> emptyLLL = emptyList();
        List<? extends Map<String, Long>> emptyLMSL = emptyList();
        // Verify
        assertEquals(emptyLLL, EMPTYLL);
        assertEquals(emptyLMSL, EMPTYLL);
    }
    
    @Test(expected = EmptyListHasNoHead.class)
    public void callingHeadOnAnEmptyListRaises() throws Exception {
        EMPTYLS.head();
    }
    
    @Test(expected = EmptyListHasNoTail.class)
    public void callingTailOnAnEmptyListRaises() throws Exception {
        EMPTYLS.tail();
    }
    
    @Test
    public void callingTailOnAOneElementListReturnsAnEmptyList() throws Exception {
        // SetUp
        // Exercise
        List<String> tail = list("one", EMPTYLS).tail();
        // Verify
        assertThat(tail, is(EMPTYLS));
    }
    
    @Test
    public void callingTailOnAListWithMultiplElementsReturnsANonEmptyList() throws Exception {
        // SetUp
        // Exercise
        List<String> tail = list("one", list("two", list("three", EMPTYLS))).tail();
        // Verify
        assertThat(tail, is(list("two", list("three", EMPTYLS))));
    }
    
    @Test
    public void callingHeadOnANonEmptyListReturnsTheHead() throws Exception {
        // SetUp
        // Exercise
        String head = list("one", EMPTYLS).head();
        // Verify
        assertThat(head, is("one"));
    }
    
    @Test
    public void AllEmptyListsAreEqual() throws Exception {
        assertEquals(EMPTYLS, EMPTYLL);
    }
    
    @Test
    public void AnEmptyListAndNoEmptyListAreNeverEqual() throws Exception {
        // SetUp
        // Exercise
        List<String> list1 = list("one", EMPTYLS);
        // Verify
        assertThat(list1, is(not(EMPTYLS)));
    }
    
    @Test
    public void TwoNonEmptyListsAreEqualIfTheirHeadsAnTailsAreEqual() throws Exception {
        // SetUp
        // Exercise
        List<String> list1 = list("one", list("two", list("three", EMPTYLS)));
        List<String> list2 = list("one", list("two", list("three", EMPTYLS)));
        List<Long>   list3 = list(1L, list(2L, list(3l, EMPTYLL)));
        // Verify
        assertThat(list1, is(list2));
        assertFalse(list1.equals(list3));
    }
    
    @Test
    public void TwoNonEmptyListsAreNotEqualIfTheirSizesAreDifferent() throws Exception {
        // SetUp
        // Exercise
        List<String> list1 = list("one", EMPTYLS);
        List<String> list2 = list("one", list("two", EMPTYLS));
        // Verify
        assertThat(list1, is(not(list2)));
    }
    
    @Test
    public void ListsAreRecursiveStructures() throws Exception {
        // SetUp
        // Exercise
        List<String> list1 = list("one", list("two", list("three", EMPTYLS)));
        // Verify
        assertThat(list1.toString(), is("(one, (two, (three, ())))"));
    }
    
}
