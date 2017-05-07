package com.loloof64.functional.monad;

public class Just<T> extends Maybe {

    private T data;

    public Just(T data){
        this.data = data;
    }

    @Override
    public T fromJust() {
        return data;
    }

    @Override
    public boolean isJust() {
        return true;
    }

    @Override
    public boolean isNothing() {
        return false;
    }
}
