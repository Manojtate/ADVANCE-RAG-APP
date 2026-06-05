package com.spring.ai.AdanceRagFirstProject.first_project;

import com.spring.ai.AdanceRagFirstProject.first_project.helper.Helper;
import com.spring.ai.AdanceRagFirstProject.first_project.services.CharServiceImp;
import com.spring.ai.AdanceRagFirstProject.first_project.services.DataLoader;
import com.spring.ai.AdanceRagFirstProject.first_project.services.DataTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdvanceRagFirstProjectApplicationTests {

    @Autowired
   private CharServiceImp chatService;

    @Autowired
    private DataLoader dataLoader;

    @Autowired
    private DataTransformer dataTransformer;

    @Autowired
    private VectorStore vectorStore;

    @Test
    void contextLoads() {
    }

    @Test
    void savetoVectorDB() {
        chatService.saveData(Helper.getData());

    }

    @Test
    void testDataFromloadDocumnetFromJson()
    {
       var documents = dataLoader.loadDocumnetFromJson();
        IO.println(documents.size());
        documents.forEach(item->{
            IO.println(item);
        });
    }

    @Test
    void testDataFromloadDocumnetFromPdf()
    {
        List<Document> documents = dataLoader.loadDocumentFromPdf();
        IO.println(documents.size());

        documents.forEach(item->{
            IO.println(item);
            IO.println("-----");
        });

        IO.println("Readed now going to transform");
        var tansformedDocument =this.dataTransformer.transform(documents);

        IO.println(tansformedDocument.size());

        IO.println("going to save data to database");
        this.vectorStore.add(tansformedDocument);
        IO.println("Done");
    }


}
