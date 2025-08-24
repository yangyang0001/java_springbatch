package com.deepblue.springbatch.handler;

import java.util.List;

public abstract class FilteBlackHandler<T> {

    public T filteBlack(T param) {
        return doFilter(param);
    }

    public abstract T doFilter(T param);

}
