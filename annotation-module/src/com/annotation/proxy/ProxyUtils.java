package com.annotation.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 经纪公司，创建代理人
 */
public class ProxyUtils {
    /**
     * 创建经纪人
     *
     * @param roleStar 明星
     * @return 满足明星方法的经纪人
     */
    public static Star createBroker(RoleStar roleStar) {
        Star broker = (Star) Proxy.newProxyInstance(
                // 参数一：ClassLoader 指定类加载器，加载生成的代理类
                ProxyUtils.class.getClassLoader(),
                // 参数二：指定生成的代理长什么样子（有哪些方法，是一个数组）
                new Class[]{Star.class},
                // 参数三：指定生成的代理对象要什么事情（代理调用方法，方法会执行 invoke()）
                new InvocationHandler() {
                    @Override
                    public Object invoke(
                            Object proxy, // 经纪人对象
                            Method method, // 经纪人调用的方法
                            Object[] args // 经纪人调用方法时传递的参数
                    ) throws Throwable {
                        // 代理对象要做的事情，会写在这里
                        if (method.getName().equals("sing")) {
                            System.out.println("经纪人正在准备演唱会");
                        } else if (method.getName().equals("dance")) {
                            System.out.println("经纪人正在准备大剧院");
                        } else {
                            System.out.println("无需经纪人");
                        }

                        // 最终会调用 roleStar 上对应的方法，并接受返回值
                        return method.invoke(roleStar, args);
                    }
                }
        );
        return broker;
    }
}
