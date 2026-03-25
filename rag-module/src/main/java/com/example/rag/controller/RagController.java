package com.example.rag.controller;

import com.example.rag.service.DocumentStoreService;
import com.example.rag.service.RagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final DocumentStoreService documentStoreService;
    private final RagService ragService;

    public RagController(DocumentStoreService documentStoreService, RagService ragService) {
        this.documentStoreService = documentStoreService;
        this.ragService = ragService;
    }

    @PostMapping("/store")
    public String storeDocument(@RequestParam String content, @RequestParam String id) {
        documentStoreService.storeDocument(content, id);
        return "Document stored successfully";
    }

    @GetMapping("/query")
    public String query(@RequestParam String question) {
        return ragService.ragQuery(question);
    }

}