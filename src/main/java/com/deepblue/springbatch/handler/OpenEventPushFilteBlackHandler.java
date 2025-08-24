package com.deepblue.springbatch.handler;

import java.util.List;

public class OpenEventPushFilteBlackHandler<ObjectB> extends FilteBlackHandler<List<ObjectB>> {


    @Override
    public List<ObjectB> filteBlack(List<ObjectB> param) {
        return List.of();
    }
}
