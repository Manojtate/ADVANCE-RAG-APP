package com.spring.ai.AdanceRagFirstProject.first_project.services;

import java.util.List;

public interface ChatService {

    public  void saveData(List<String> list);
    public String getResponse(String userQuery);
}
