package com.loloof64.functional.monad;

public abstract class Maybe<T> {
    public abstract T fromJust();
    public abstract boolean isJust();
    public abstract boolean isNothing();
}
