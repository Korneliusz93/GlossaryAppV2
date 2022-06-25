package com.gloss.service;

import com.gloss.model.Project;
import com.gloss.repository.ProjectCrudRepository;
import com.gloss.repository.UserCrudRepository;
import org.hibernate.Session;

import com.gloss.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPermissionsService {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectCrudRepository projectCrudRepository;


    //Add user to project
    public Optional<User> addUserToProject(int idProj, int idUser){
        Optional<User> user = userCrudRepository
                .findById(idUser);

        Optional<Project> project = projectCrudRepository
                .findById(idProj);

        //Adding permission for user mechanism
        user.get().getProjects().add(project.get());

        return userCrudRepository
                .findById(idUser)
                .map(updatedUser -> userCrudRepository.save(user.get()));


    }

    //Delete user from project
    public Optional<User> deleteUserFromProject(int idProj, int idUser){
        Optional<User> user = userCrudRepository
                .findById(idUser);

        Optional<Project> project = projectCrudRepository
                .findById(idProj);

        //Adding permission for user mechanism
        user.get().getProjects().remove(project.get());

        return userCrudRepository
                .findById(idUser)
                .map(updatedUser -> userCrudRepository.save(user.get()));
    }
}
