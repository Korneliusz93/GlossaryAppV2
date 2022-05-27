package com.gloss.service;

import com.gloss.model.Project;
import org.hibernate.Session;

import com.gloss.model.User;
import org.hibernate.SessionFactory;

public class UserPermissionsService {
    /*private final SessionFactory sessionFactory;

    public UserPermissionsService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //Add user to project
    public void addUserToProject(Project updProj, User updUser){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            updProj.addUser(updUser);
            session.saveOrUpdate(updProj);
            session.saveOrUpdate(updUser);
            session.getTransaction().commit();
        }
    }

    //Delete user from project
    public void deleteUserFromProject(Project updProj, User updUser){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            updProj.removeUser(updUser);
            session.saveOrUpdate(updProj);
            session.saveOrUpdate(updUser);
            session.getTransaction().commit();
        }
    }*/
}
