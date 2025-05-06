package com.test.junit;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
    @Test
    public void testGetMaxIndex() {
        int res1 = Utils.getMaxIndex(null);
        // 测试断言
        Assert.assertEquals(
                // 参数一：测试失败是的提示
                "测试失败",
                // 参数二：测试期望值
                -1,
                // 参数三：当前测试结果
                res1
        );

        int res2 = Utils.getMaxIndex("");
        Assert.assertEquals("测试失败", 0, res2);

        int res3 = Utils.getMaxIndex("abc");
        Assert.assertEquals("测试失败", 3, res3);
    }
}
