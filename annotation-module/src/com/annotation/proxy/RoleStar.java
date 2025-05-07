package com.annotation.proxy;

import lombok.AllArgsConstructor;

/**
 * 主演类
 */
@AllArgsConstructor
public class RoleStar implements Star {
    private String name;

    public String sing(String song) {
        System.out.println(name + "唱了一首" + song);
        return name + "说：哎哟，不错哦";
    }

    public void dance() {
        System.out.println(name + "跳了一支天鹅湖。");
    }
}
