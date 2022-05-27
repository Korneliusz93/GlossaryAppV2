package com.gloss.repository;

import com.gloss.model.Category;
import com.gloss.model.EmailAddress;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface EmailAddressCrudRepository extends JpaRepository<EmailAddress,Integer> {

}
