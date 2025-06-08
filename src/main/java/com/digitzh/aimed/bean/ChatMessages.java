package com.digitzh.aimed.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_messages")
public class ChatMessages {
    // 1. 文档id，唯一标识，映射到 MongoDB 文档的 _id 字段
    @Id // 采用ObjectId类型可使MongoDB自动生成messageId
    private ObjectId messageId;
//    private Long messageId;

    // 2. 聊天记忆id
    private String memoryId;

    private String content; //存储当前聊天记录列表的json字符串
}
