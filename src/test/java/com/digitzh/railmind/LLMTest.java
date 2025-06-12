package com.digitzh.railmind;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.net.URI;

@SpringBootTest
public class LLMTest {
    @Test
    public void testGPTDemo(){
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.chat("你是谁？");
        System.out.println(answer);
    }

    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Test
    public void testSpringBoot(){
        String answer = openAiChatModel.chat("你是谁？");
        System.out.println(answer);
    }

    @Autowired
    private OllamaChatModel ollamaChatModel;
    @Test
    public void testOllama(){
        String answer = ollamaChatModel.chat("你是谁？");
        System.out.println(answer);
    }

    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testQwen(){
        String answer = qwenChatModel.chat("你好");
        System.out.println(answer);
    }

    @Test
    public void testBailianWanx(){
        WanxImageModel wanxImageModel = WanxImageModel
                .builder()
                .modelName("wanx2.1-t2i-turbo")
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .build();
        Response<Image> response = wanxImageModel.generate("A sleek, futuristic metro train (silver/blue \n" +
                " livery with dynamic stripes) arriving at a spacious elevated station, with magnetic levitation technology visible beneath the train.");
        URI url = response.content().url();
        System.out.println(url);
    }
}
