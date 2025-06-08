package com.digitzh.aimed.config;

import com.digitzh.aimed.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeparateChatAssistantConfig {
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;
    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                // 1. 原方法：新建对象，内存中的聊天记忆
                //.chatMemoryStore(new InMemoryChatMemoryStore())
                // 2. 新方法：注入
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }
}
