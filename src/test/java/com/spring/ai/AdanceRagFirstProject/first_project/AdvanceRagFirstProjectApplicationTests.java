package com.spring.ai.AdanceRagFirstProject.first_project;

import com.spring.ai.AdanceRagFirstProject.first_project.helper.Helper;
import com.spring.ai.AdanceRagFirstProject.first_project.services.CharServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdvanceRagFirstProjectApplicationTests {

    @Autowired
    CharServiceImp chatService;

    @Test
    void contextLoads() {
    }

    @Test
    void savetoVectorDB() {
        chatService.saveData(Helper.getData());

    }
}
