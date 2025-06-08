# 快速上手

运行`src/main/java/com/digitzh/ai/MainApp.java`：
```
2025-06-05T11:02:28.666+08:00  INFO 20984 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2025-06-05T11:02:28.671+08:00  INFO 20984 --- [           main] com.digitzh.aimed.MainApp                   : Started MainApp in 4.953 seconds (process running for 5.314)
```

接下来有2种使用方法：

### 方法1 通过knife4j发送请求

打开[knife4j页面](http://localhost:8080/doc.html#/-v3-api-docs/AiMed/chat)，点击左侧AiMed - POST 会话，可在页面中看到消息结构体：
```json
{
  "memoryId": 1,
  "message": "你好"
}
```
修改其中的message，点击发送即可。memoryId用于隔离不同会话。

### 方法2 通过UI页面发送消息

在aimed-ui目录下运行UI（前后端分离，请参考前端仓库）：
```shell
npm run dev
```
之后即可在(http://localhost:5173/)使用。