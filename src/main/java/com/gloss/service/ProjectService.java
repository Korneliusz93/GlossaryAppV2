package com.gloss.service;

import com.gloss.model.Project;
import com.gloss.repository.ProjectCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectCrudRepository projectCrudRepository;

    public Project addProject(String projectName) {
        Project project = Project.builder()
                .projectName(projectName)
                .build();
        return projectCrudRepository.save(project);
    }

    public Optional<Project> getProject(int id){
        return projectCrudRepository.findById(id);
    }

    public void deleteProject(int id) {
        projectCrudRepository.deleteById(id);
    }

    public List<Project> getProjects(){
        return projectCrudRepository.findAll();
    }

}
