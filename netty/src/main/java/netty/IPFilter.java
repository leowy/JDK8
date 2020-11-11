package netty;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/11/3 14:21
 * @Description:
 */
public class IPFilter implements IpFilterRule {

    //    String ip = "127.0.0.1";
    private List<String> addresses;

    public IPFilter(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean matches(InetSocketAddress inetSocketAddress) {

        final boolean b = addresses.stream().noneMatch(address -> {
            System.out.println("address:" + address);
            System.out.println("fact ip:" + inetSocketAddress.getHostString());

            boolean result = inetSocketAddress.getHostString().equals(address);
            System.out.println(result);
            return result;

        });
        System.out.println("result :" + b);
        return b;
//        String src = inetSocketAddress.getHostString();
//        System.out.println(src);
//        return Objects.equals(ip, src);
    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.REJECT;
    }
}
