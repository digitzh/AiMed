package com.digitzh.aimed.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {
    // 1. 如果工具较复杂，可以补充name；更详细地可以补充value
    // 工具较简单时无需补充，以免引起大模型幻觉
//     @Tool(name="加法", value="计算两个参数的和")
    // 2. 使用@P注解，标注参数的说明，以及是否为必须项（默认true）
    @Tool
    double sum(
            @ToolMemoryId int memoryId,
            @P(value = "加数1", required = true)double a,
            @P(value = "加数2", required = true)double b)
    {
        System.out.println(memoryId + " 调用加法运算");
        return a + b;
    }
    @Tool
    double squareRoot(@ToolMemoryId int memoryId, double x) {
        System.out.println(memoryId + " 调用平方根运算");
        return Math.sqrt(x);
    }
}