package com.loloof64.functional.monad;

public class Nothing<T> extends Maybe<T> {
    @Override
    public T fromJust() {
        throw new RuntimeException("Cannot call fromJust() from Nothing !");
    }

    @Override
    public boolean isJust() {
        return false;
    }

    @Override
    public boolean isNothing() {
        return true;
    }
}
