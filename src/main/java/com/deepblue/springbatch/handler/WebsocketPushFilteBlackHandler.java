package com.deepblue.springbatch.handler;

import java.util.List;

public class WebsocketPushFilteBlackHandler<ObjectA> extends FilteBlackHandler <List<ObjectA>> {

    @Override
    public List<ObjectA> doFilter(List<ObjectA> param) {
        return List.of();
    }
}
