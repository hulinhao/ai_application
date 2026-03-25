package com.example.rag.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DocumentStoreService {

    private final VectorStore vectorStore;

    public DocumentStoreService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void storeDocument(String content, String id) {
        Map<String, Object> metadata = Map.of("id", id);
        Document document = new Document(id, metadata);
        vectorStore.add(List.of(document));
    }

    public void storeDocuments(List<Document> documents) {
        vectorStore.add(documents);
    }

}