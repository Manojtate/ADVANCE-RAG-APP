package com.spring.ai.AdanceRagFirstProject.first_project.services;

import org.springframework.ai.document.Document;
import java.util.List;

public interface DataTransformer {

    List<Document> transform(List<Document> documents);

}
