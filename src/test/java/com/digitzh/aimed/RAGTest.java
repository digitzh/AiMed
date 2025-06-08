package com.digitzh.aimed;

import dev.langchain4j.community.model.dashscope.QwenTokenCountEstimator;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenCountEstimator;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

@SpringBootTest
public class RAGTest {
    @Test
    public void testReadDocument() {
        // 1.1 （无参）使用FileSystemDocumentLoader读取知识库文档并使用默认文档解析器解析
//        Document document1 = FileSystemDocumentLoader.loadDocument("src/main/resources/doc/RAG简介.txt");
//        System.out.println(document.text());

        // 1.2 加载并显式使用TextDocumentParser解析
//        Document document2 = FileSystemDocumentLoader.loadDocument("E:/knowledge/file.txt", new
//                TextDocumentParser());

        // 1.3 从一个目录中加载所有文档
//        List<Document> documents1 = FileSystemDocumentLoader.loadDocuments("E:/knowledge", new
//                TextDocumentParser());

        // 1.4 从一个目录中加载所有的.txt文档
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.md");
        List<Document> documents2 = FileSystemDocumentLoader.loadDocuments("src/main/resources/doc",
                pathMatcher, new TextDocumentParser());
        // 遍历
        for (Document document : documents2){
            System.out.println("=====================================");
            System.out.println(document.metadata());
            System.out.println(document.text());
        }

        // 1.5 从一个目录及其子目录中加载所有文档
//        List<Document> documents3 =
//                FileSystemDocumentLoader.loadDocumentsRecursively("E:/knowledge", new
//                        TextDocumentParser());
    }

    // 2.1 解析PDF
    @Test
    public void testParsePDF() {
        Document document = FileSystemDocumentLoader.loadDocument(
                "src/main/resources/doc/大模型RAG应用.pdf",
                new ApachePdfBoxDocumentParser()
        );
        System.out.println(document.metadata());
        System.out.println(document.text());
    }

    /**
     * 加载文档并存入向量数据库
     */
    @Test
    public void testReadDocumentAndStore() {
        //使用FileSystemDocumentLoader读取指定目录下的知识库文档
        //并使用默认的文档解析器对文档进行解析(TextDocumentParser)
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/doc/P, NP, NPC和NP-Hard问题.md");
        //为了简单起见，我们暂时使用基于内存的向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        //ingest
        //1、分割文档：默认使用递归分割器，将文档分割为多个文本片段，每个片段包含不超过 300个token，并且有 30个token的重叠部分保证连贯性
        //DocumentByParagraphSplitter(DocumentByLineSplitter(DocumentBySentenceSplitter(DocumentByWordSplitter)))
        //2、文本向量化：使用一个LangChain4j内置的轻量化向量模型对每个文本片段进行向量化
        //3、将原始文本和向量存储到向量数据库中(InMemoryEmbeddingStore)
        EmbeddingStoreIngestor.ingest(document, embeddingStore);
        //查看向量数据库内容
        System.out.println(embeddingStore);
    }

    @Test
    public void testDocumentSplitter() {
        //使用FileSystemDocumentLoader读取指定目录下的知识库文档
//并使用默认的文档解析器对文档进行解析(TextDocumentParser)
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/doc/P, NP, NPC和NP-Hard问题.md");
        //为了简单起见，我们暂时使用基于内存的向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        //自定义文档分割器
        //按段落分割文档：每个片段包含不超过 300个token，并且有 30个token的重叠部分保证连贯性
        //注意：当段落长度总和小于设定的最大长度时，就不会有重叠的必要。
        DocumentByParagraphSplitter documentSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                //token分词器：按token计算
                new HuggingFaceTokenCountEstimator()); // 使用Estimator替代Tokenizer
        //按字符计算
        //DocumentByParagraphSplitter documentSplitter = new DocumentByParagraphSplitter(300, 30);
        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentSplitter)
                .build()
                .ingest(document);
    }

    @Test
    public void testTokenCount() {
        String text = "这是一个示例文本，用于测试 token 长度的计算。";
        UserMessage userMessage = UserMessage.userMessage(text);
        //计算 token 长度
        QwenTokenCountEstimator estimator = new QwenTokenCountEstimator(System.getenv("BAILIAN_API_KEY"), "qwen-max");
//        HuggingFaceTokenCountEstimator estimator = new HuggingFaceTokenCountEstimator();
        int count = estimator.estimateTokenCountInMessage(userMessage);
        System.out.println("token长度：" + count);
    }
}
