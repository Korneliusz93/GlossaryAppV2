package com.gloss.service;

import com.gloss.model.EmailAddress;
import com.gloss.repository.EmailAddressCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailAddressService {

    @Autowired
    private EmailAddressCrudRepository emailAddressCrudRepository;

    public Optional<EmailAddress> getEmailAddress(int id){
        return emailAddressCrudRepository.findById(id);
    }

    public void deleteEmailAddress(int id){
        emailAddressCrudRepository.deleteById(id);
    }

    public List<EmailAddress> getEmailAddresses(){
        return emailAddressCrudRepository.findAll();
    }

}
