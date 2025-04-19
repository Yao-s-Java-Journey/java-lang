package com.collection.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 房间
 */
public class Room {
    // 1. 准备一副牌
    private ArrayList<Card> allCards = new ArrayList<>();

    // 2. 初始化 54 张牌
    {
        // 3. 准备点数、花色
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] colors = {"♠", "♥", "♦", "♣"};
        // 4. 组装一副牌
        int size = 0;
        for(String value : values) {
            size++;
            for(String color : colors) {
                allCards.add(new Card(value, color, size));
            }
        }
        // 5. 单独添加大小王
        allCards.add(new Card("大王", "\uD83E\uDD21", ++size));
        allCards.add(new Card("小王", "\uD83D\uDC7B", ++size));
        System.out.println("新牌：" + allCards);
    }

    public void start() {
        // 1. 洗牌
        Collections.shuffle(allCards);
        System.out.println("洗牌：" + allCards);

        // 2. 发牌（发51张，最后三张给地主）
        List<Card> user1 = new ArrayList<>();
        List<Card> user2 = new ArrayList<>();
        List<Card> user3 = new ArrayList<>();
        for (int i = 0; i < allCards.size() - 3; i++) {
            Card currentCard = allCards.get(i);
            if (i % 3 == 0) {
                user1.add(currentCard);
            } else if (i % 3 == 1) {
                user2.add(currentCard);
            } else {
                user3.add(currentCard);
            }
        }
        // 再发最后三张底牌
        List<Card> lastCards = allCards.subList(allCards.size() - 3, allCards.size());
        user1.addAll(lastCards);

        // 3. 排序
        sortCards(user1);
        sortCards(user2);
        sortCards(user3);

        // 4. 看牌
        System.out.println("user1:" + user1);
        System.out.println("user2:" + user2);
        System.out.println("user3:" + user3);
    }

    public void sortCards(List<Card> cards) {
          cards.sort((o1, o2) -> o1.getSize() - o2.getSize());
    }
}
