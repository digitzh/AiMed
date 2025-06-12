package com.digitzh.railmind.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

// 支持记忆（上下文）的Assistant
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory"
)
public interface MemoryChatAssistant {
    // 支持用户提示词（每次会话均发送）
    @UserMessage("你是一位铁路助手，提供专业、精准的铁路运营调度和维护保障服务。{{message}}")
    String chat(@V("message") String message);
}
