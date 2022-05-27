package com.gloss.service;

import com.gloss.model.User;
import com.gloss.repository.UserCrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserCrudRepository userCrudRepository;

    // Dodaj uzytkownika do bazy
    public User addUser(String firstName, String lastName,
                        String username, String password) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .build();
        return userCrudRepository.save(user);
    }

    // Usun uzytkownika z bazy
    public void deleteUser(int id) { userCrudRepository.deleteById(id); }

    // Aktualizuj uzytkownika
    public Optional<User> updateUser(int id, String userName, String newPassword,
                                     String firstName, String lastName) {
        User user = User.builder()
                .id(id)
                .username(userName)
                .password(newPassword)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return userCrudRepository
                .findById(id)
                .map(updatedUser -> userCrudRepository.save(user));

    }

    //czy potrzebuje tutaj przekazywac string? przeladowanie funkcji
    /*public Optional<User> deleteEmail(int id, String emailAddress){
        List<User> users = getUser(id).stream()
                .filter(usr -> usr.getId() == id )
                .collect(Collectors.toList());
        users.get(0).removeEmail(emailAddress);

        return userCrudRepository
                .findById(id)
                .map(savedCustomer -> userCrudRepository.save(users.get(0)));
    }

    // Dodaj adres e-mail
    public Optional<User> addEmail(int id, String emailAddress){

        List<User> users = getUser(id).stream()
                .filter(usr -> usr.getId() == id )
                .collect(Collectors.toList());
        users.get(0).addEmail(emailAddress);

        return userCrudRepository
                .findById(id)
                .map(savedCustomer -> userCrudRepository.save(users.get(0)));

    }

    public Optional<User> updatePassword(int id, String newPassword){
        List<User> users = getUser(id).stream()
                .filter(usr -> usr.getId() == id)
                .collect(Collectors.toList());
        users.get(0).setPassword(newPassword);

        return userCrudRepository
                .findById(id)
                .map(savedCustomer -> userCrudRepository.save(users.get(0)));

    }*/

    //Pobierz uzytkownika
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
