package com.spring.ai.AdanceRagFirstProject.first_project.services;

import com.spring.ai.AdanceRagFirstProject.first_project.advisors.TokenPrintAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharServiceImp implements  ChatService {

    private Logger logger = LoggerFactory.getLogger(TokenPrintAdvisor.class);

    private final ChatClient chatClient;

    @Autowired
    private VectorStore vectorStore;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    @Value("classpath:/prompts/system-message.st")
    private Resource syetmMessage;

    public CharServiceImp(ChatClient chatClient , VectorStore vectorStore) {

        this.chatClient = chatClient;
        this.vectorStore=vectorStore;
    }


    @Override
    public void saveData(List<String> list) {

        List<Document>documentList = list.stream().map(item -> new Document(item)).collect(Collectors.toList());
        this.vectorStore.add(documentList);
        System.out.println("data is saved successfully");
    }

    @Override
    public String getResponse(String userQuery) {

         RetrievalAugmentationAdvisor.builder()
                 .queryTransformers(
                         RewriteQueryTransformer.builder()
                                 .chatClientBuilder(chatClient.mutate().clone())
                                 .build()
                 )
                 .documentRetriever(
                         VectorStoreDocumentRetriever.builder()
                                 .vectorStore(vectorStore)
                                 .topK(3)
                                 .similarityThreshold(0.3)
                                 .build()
                 )
                 .build();



        return   chatClient
                .prompt()
                .user(userQuery)
                .call()
                .content();
    }


}
