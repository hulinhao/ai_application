package com.example.rag.config;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.RedisVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoreConfig {

    @Bean
    public VectorStore vectorStore(RedisVectorStore.RedisVectorStoreConfig redisVectorStoreConfig, EmbeddingClient embeddingClient) {
        return new RedisVectorStore(redisVectorStoreConfig, embeddingClient);
    }

}