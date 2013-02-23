package org.emamotor.fpforjavadevs.datastructures2;

import org.emamotor.fpforjavadevs.functions.Function1;
import org.emamotor.fpforjavadevs.functions.Function1Void;
import org.emamotor.fpforjavadevs.functions.Function2;

public class ListModule {

    public static interface List<T> {
        
        T head();
        List<T> tail();
        boolean isEmpty();
        
        public List<T> filter(Function1<T, Boolean> f);
        public <T2> List<T2> map(Function1<T, T2> f);
        public <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f);
        public <T2> T2 foldRight(T2 seed, Function2<T, T2, T2> f);
        public void foreach(Function1Void<T> f);
        
    }
    
    public static final class NonEmptyList<T> implements List<T> {

        private final T _head;
        private final List<T> _tail;
        
        protected NonEmptyList(T head, List<T> tail) {
            _head = head;
            _tail = tail;
        }
        
        @Override
        public T head() {
            return _head;
        }

        @Override
        public List<T> tail() {
            return _tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
        
        @Override
        public List<T> filter(Function1<T, Boolean> f) {
            
            if(f.apply(head())) {
                return list(head(), tail().filter(f));
            } else {
                return tail().filter(f);
            }
            
        }

        @Override
        public <T2> List<T2> map(Function1<T, T2> f) {
            return list(f.apply(head()), tail().map(f));
        }

        @Override
        public <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f) {
            return tail().foldLeft(f.apply(seed, head()), f);
        }

        @Override
        public <T2> T2 foldRight(T2 seed, Function2<T, T2, T2> f) {
            return f.apply(head(), tail().foldRight(seed, f));
        }

        @Override
        public void foreach(Function1Void<T> f) {
            f.apply(head());
            tail().foreach(f);
        }
        
        @Override
        public boolean equals(Object other) {
            if (other == null || getClass() != other.getClass())
                return false;
            
            List<?> that = (List<?>) other;
            return head().equals(that.head()) && tail().equals(that.tail());
        }
        
        @Override
        public int hashCode() {
            return 37 * (head().hashCode() + tail().hashCode());
        }
        
        @Override
        public String toString() {
            return "(" + head() + ", " + tail() + ")";
        }

    }
    
    public static class EmptyListHasNoHead extends RuntimeException {}
    
    public static class EmptyListHasNoTail extends RuntimeException {}
    
    public static final List<? extends Object> EMPTY = new List<Object>() {

        @Override
        public Object head() {
            throw new EmptyListHasNoHead();
        }

        @Override
        public List<Object> tail() {
            throw new EmptyListHasNoTail();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
        
        @Override
        public List<Object> filter(Function1<Object, Boolean> f) {
            return this;
        }

        @Override
        public <T2> List<T2> map(Function1<Object, T2> f) {
            return emptyList();
        }

        @Override
        public <T2> T2 foldLeft(T2 seed, Function2<T2, Object, T2> f) {
            return seed;
        }

        @Override
        public <T2> T2 foldRight(T2 seed, Function2<Object, T2, T2> f) {
            return seed;
        }

        @Override
        public void foreach(Function1Void<Object> f) {
            // do nothing
        }
        
        @Override
        public String toString() {
            return "()";
        }

    };
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> emptyList() {
        return (List<T>) EMPTY;
    }
    
    public static <T> List<T> list(T head, List<T> tail) {
        return new NonEmptyList<T>(head, tail);
    }
    
}
