package com.ly.callback.async;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/28 16:25
 * @Description:
 */
public class RealData implements Data {

    private int param;
    @Override
    public String getResult() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            param += i;
        }
        return param+"";
    }


    public void setParam(int param) {
        this.param = param;
    }
}
