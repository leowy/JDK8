package com.ly.callback.sync;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/28 15:34
 * @Description:
 */
public class Consumer {

    public void tell(Callback callback) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.desc("I'm consumer");
    }
}
