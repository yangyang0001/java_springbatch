package com.deepblue.springbatch.chapter_12;

import org.springframework.batch.core.SkipListener;

public class MineSkipListener implements SkipListener {

    @Override
    public void onSkipInRead(Throwable t) {
        SkipListener.super.onSkipInRead(t);
    }

    @Override
    public void onSkipInWrite(Object item, Throwable t) {
        SkipListener.super.onSkipInWrite(item, t);
    }

    @Override
    public void onSkipInProcess(Object item, Throwable t) {
        SkipListener.super.onSkipInProcess(item, t);
    }
}
