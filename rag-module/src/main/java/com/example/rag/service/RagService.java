package com.example.rag.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RagService {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    public RagService(VectorStore vectorStore, ChatClient chatClient) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
    }

    public String ragQuery(String query) {
        // 1. 从向量存储中检索相关文档
        var similarDocuments = vectorStore.similaritySearch(query);
        
        // 2. 构建上下文
        String context = similarDocuments.stream()
                .map(doc -> doc.getContent())
                .collect(Collectors.joining("\n"));
        
        // 3. 构建prompt
        String promptTemplate = "Answer the following question based on the provided context:\n" +
                "Context:\n" +
                "{context}\n" +
                "Question:\n" +
                "{query}\n" +
                "Answer:";
        
        String promptText = promptTemplate
                .replace("{context}", context)
                .replace("{query}", query);
        
        // 4. 调用LLM获取回答
        ChatResponse response = chatClient.call(new Prompt(promptText));
        
        return response.getResult().getOutput().getContent();
    }

}