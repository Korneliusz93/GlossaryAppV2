package com.gloss.repository;


import com.gloss.model.EmailAddress;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface EmailAddressCrudRepository extends JpaRepository<EmailAddress,Integer> {

    @Query("FROM EmailAddress ORDER BY emailAddress")
    List<EmailAddress> findAllOrderByEmailAddress();

}
