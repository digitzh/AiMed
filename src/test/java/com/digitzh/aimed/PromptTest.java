package com.digitzh.aimed;

import com.digitzh.aimed.assistant.MemoryChatAssistant;
import com.digitzh.aimed.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {
    // 1. 系统级提示
    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testSystemMessage() {
        String answer = separateChatAssistant.chat(3, "刚才我问了什么问题？");
        System.out.println(answer);
    }

    // 2. 用户级提示
    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testUserMessage() {
        String answer = memoryChatAssistant.chat("我是张三");
        System.out.println(answer);

        String answer2 = memoryChatAssistant.chat("我有一只猫");
        System.out.println(answer2);

        String answer3 = memoryChatAssistant.chat("我是谁？");
        System.out.println(answer3);
    }

    @Test
    public void testV() {
        String answer1 = separateChatAssistant.chat2(1, "我是张三");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat2(1, "我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserInfo() {
        // 假设用户的姓名和年龄已经从数据库中取出
        String answer = separateChatAssistant.chat3(1, "我是谁？我多大了？今天是几号？", "张三", 18);
        System.out.println(answer);
    }
}