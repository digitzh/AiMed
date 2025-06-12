package com.digitzh.railmind;

import com.digitzh.railmind.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {
    // 1. 方法1：直接定义确定的模型
    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testChat(){
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String answer = assistant.chat("你是谁");
        System.out.println(answer);
    }

    // 2. 方法2：在Assistant.java中注册信息
    @Autowired
    private Assistant assistant;
    @Test
    public void testAssistant(){
        String answer = assistant.chat("我是谁");
        System.out.println(answer);
    }
}
