package com.gloss.service;

import com.gloss.model.EmailAddress;
import com.gloss.model.User;
import com.gloss.repository.EmailAddressCrudRepository;
import com.gloss.repository.UserCrudRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private EmailAddressService emailAddressService;

    @Autowired
    private EmailAddressCrudRepository emailAddressCrudRepository;

    // Dodaj uzytkownika do bazy
    public User addUser(String firstName, String lastName,
                        String username, String password,String email) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .emailAddress(addEmailAddress(email))
                .build();


        return userCrudRepository.save(user);
    }

    // Usun uzytkownika z bazy
    public void deleteUser(int id) { userCrudRepository.deleteById(id); }

    // Aktualizuj haslo uzytkownika
    public Optional<User> updatePassword(int id, String newPassword) {

        Optional<User> user = userCrudRepository
                .findById(id);

        user.get().setPassword(newPassword);

        return userCrudRepository
                .findById(id)
                .map(updatedUser -> userCrudRepository.save(user.get()));

    }

    // dodaj email uzytkownika uzytkownika
    public Set<EmailAddress> addEmailAddress(String email) {

        HashSet<EmailAddress> emails = new HashSet<EmailAddress>();
        emails.add(emailAddressService.addEmailAddress(email));
        return emails;

    }

    public Optional<User> addEmailAddress(int id, String email) {

        Optional<User> user = userCrudRepository
                .findById(id);
        user.get().getEmailAddress().add(emailAddressService.addEmailAddress(email));

        return userCrudRepository
                .findById(id)
                .map(updatedUser -> userCrudRepository.save(user.get()));

    }

    //delete email from user
    /*public Optional<User> deleteEmail(int id, String emailAddress){

    }*/

    // method getUserEmails
    public List<EmailAddress> getUserEmailAddresses(int id){
        ArrayList<EmailAddress> usersEmails = new ArrayList<EmailAddress>();
        Optional<User> user = userCrudRepository
                .findById(id);
        for (EmailAddress email: user.get().getEmailAddress()) {
            usersEmails.add(email);
        }

        return usersEmails;
    }

    // Pobierz uzytkownika
    public Optional<User> getUser(int id){ return userCrudRepository.findById(id); }

    //wypisz uzytkownikow
    public List<User> getUsers(String sortBy) {
        if ("firstName".equals(sortBy)) {
            return userCrudRepository.findAllOrderByFirstName();
        } else if ("lastName".equals(sortBy)) {
            return userCrudRepository.findAllOrderByLastName();
        } else {
            return userCrudRepository.findAll();
        }
    }

}
