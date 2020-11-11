package com.ly.callback.async;

import org.junit.Test;

public class DataTest {

    @Test
    public void testAsyncCallback() {
        Client client = new Client();
        Data data = client.sendMsg(1);
        System.out.println(data.getResult());
    }

}