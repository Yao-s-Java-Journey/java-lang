package com.collection.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 斗地主，卡牌类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String value;
    private String color;
    private int size; // 大小，用于排序比较

    @Override
    public String toString() {
        return color + value;
    }
}
