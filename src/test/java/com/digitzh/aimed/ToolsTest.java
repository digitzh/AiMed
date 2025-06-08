package com.digitzh.aimed;

import com.digitzh.aimed.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToolsTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testCalculatorTools() {
        String answer = separateChatAssistant.chat(99, "95863+34584等于多少？123456的平方根是多少？");
                //答案：1457102469 | 689706.4865
                System.out.println(answer);
    }
}
