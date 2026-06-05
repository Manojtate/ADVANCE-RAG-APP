package com.spring.ai.AdanceRagFirstProject.first_project.services;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTransformerImp implements DataTransformer {

    @Override
    public List<Document> transform(List<Document> documents) {

        TokenTextSplitter splitter = TokenTextSplitter.builder().build();

        return splitter.transform(documents);
    }
}