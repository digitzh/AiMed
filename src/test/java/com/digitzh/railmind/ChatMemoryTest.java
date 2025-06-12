package com.digitzh.railmind;

import com.digitzh.railmind.assistant.Assistant;
import com.digitzh.railmind.assistant.MemoryChatAssistant;
import com.digitzh.railmind.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
    // 2 手动实现有记忆聊天
    @Autowired
    private QwenChatModel qwenChatModel;
    @Autowired
    private Assistant assistant;

    @Test
    public void testChatMemory2() {
        //第一轮对话
        UserMessage userMessage1 = UserMessage.userMessage("你是谁");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage1.text());
        //第二轮对话
        UserMessage userMessage2 = UserMessage.userMessage("刚才我问了什么");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1,
                aiMessage1, userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage2.text());
    }

    // 3 通过chatMemory()方法实现有记忆聊天
    @Test
    public void testChatMemory3(){
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        Assistant assistant1 = AiServices
                .builder(Assistant.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        String answer1 = assistant1.chat("你是谁");
        System.out.println(answer1);
        String answer2 = assistant1.chat("刚才我问了什么");
        System.out.println(answer2);
    }

    // 4 在AIService中配置ChatMemory
    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testChatMemory4(){
        String answer1 = memoryChatAssistant.chat("你是谁");
        System.out.println(answer1);
        String answer2 = memoryChatAssistant.chat("刚才我问了什么");
        System.out.println(answer2);
    }

    // 5 带聊天ID（区分不同聊天）的ChatAssistant
    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testChatMemory5(){
        String answer1a = separateChatAssistant.chat(1, "你是谁");
        System.out.println(answer1a);

        String answer1b = separateChatAssistant.chat(1, "刚才我问了什么");
        System.out.println(answer1b);
        String answer2b = separateChatAssistant.chat(2, "刚才我问了什么");
        System.out.println(answer2b);
    }
}
