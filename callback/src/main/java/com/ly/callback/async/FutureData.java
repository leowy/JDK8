package com.ly.callback.async;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/28 16:25
 * @Description:
 */
public class FutureData implements Data{
    private RealData realData;
    private boolean status = false;

    @Override
    public synchronized String getResult() {
        while (!status) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  realData.getResult();
        }
        return null;
    }

    public synchronized void setRealData(RealData realData) {
        if (status) {
            return;
        }
        this.realData = realData;
        status = true;
        notifyAll();
    }
}
