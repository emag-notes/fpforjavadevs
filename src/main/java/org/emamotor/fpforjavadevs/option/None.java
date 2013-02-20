package org.emamotor.fpforjavadevs.option;

public final class None<T> extends Option<T> {

    public static class NoneHasNoValue extends RuntimeException {}
    
    public None() {}
    
    @Override
    public boolean hasValue() {
        return false;
    }

    @Override
    public T get() {
        throw new NoneHasNoValue();
    }
    
    @Override
    public String toString() {
        return "None";
    }
    
    @Override
    public boolean equals(Object other) {
        return (other == null || other.getClass() != None.class) ? false : true;
    }

}
