package com.digitzh.aimed.controller;

import com.digitzh.aimed.assistant.AiMedAgent;
import com.digitzh.aimed.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "AiMed")
@RestController
@RequestMapping("/aimed")
public class AiMedController {
    @Autowired
    private AiMedAgent aiMedAgent;

    @Operation(summary = "对话")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return aiMedAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
