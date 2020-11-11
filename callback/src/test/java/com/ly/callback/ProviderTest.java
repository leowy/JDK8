package com.ly.callback;

import com.ly.callback.sync.Consumer;
import com.ly.callback.sync.Provider;
import org.junit.Test;

public class ProviderTest {

    @Test
    public void testCallback() {
        // 同步回调
        Consumer consumer = new Consumer();
        Provider provider = new Provider();
        provider.setConsumer(consumer);
        provider.ask();
    }

}