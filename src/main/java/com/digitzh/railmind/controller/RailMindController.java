package com.digitzh.railmind.controller;

import com.digitzh.railmind.assistant.RailMindAgent;
import com.digitzh.railmind.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "RailMind")
@RestController
@RequestMapping("/railmind")
public class RailMindController {
    @Autowired
    private RailMindAgent railMindAgent;

    @Operation(summary = "对话")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return railMindAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
