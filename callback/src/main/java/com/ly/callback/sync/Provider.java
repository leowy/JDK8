package com.ly.callback.sync;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/28 15:33
 * @Description:
 */
public class Provider implements Callback {

    private Consumer consumer;

    @Override
    public void desc(String content) {
        System.out.println("this is callback:" + content);
    }

    public void ask() {
        consumer.tell(this);
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

}
