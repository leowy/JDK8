package com.ly.callback.async;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/28 16:28
 * @Description:
 */
public class Client {
    FutureData futureData = new FutureData();

    public Data sendMsg(int i) {
        new Thread(()->{
            RealData realData = new RealData();
            String data = realData.getResult();
            futureData.setRealData(realData);
        },"client").start();

        return futureData;
    }
}
