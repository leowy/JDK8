package netty;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/11/3 14:21
 * @Description:
 */
public class IPFilter2 implements IpFilterRule {

    String ip = "192.168.2.152";

    @Override
    public boolean matches(InetSocketAddress inetSocketAddress) {
        String src = inetSocketAddress.getHostString();
        System.out.println(src);
        return Objects.equals(ip, src);
    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.REJECT;
    }
}
