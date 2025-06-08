package com.digitzh.aimed.assistant;

import dev.langchain4j.service.*;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;
@AiService(
        wiringMode = EXPLICIT,
        // 1. 批式输出
//        chatModel = "qwenChatModel",
        // 2. 流式输出
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderAiMed",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverAiMedPincone"
)
public interface AiMedAgent {
    @SystemMessage(fromResource = "prompt-templates/aimed-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}