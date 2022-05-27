package com.gloss.repository;

import com.gloss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCrudRepository extends JpaRepository <User,Integer> {

    @Query("FROM User ORDER BY firstName")
    List<User> findAllOrderByFirstName();

    @Query("FROM User ORDER BY lastName")
    List<User> findAllOrderByLastName();

    @Query("FROM User WHERE lastName = :lastName")
    List<User> findAllByLastName(@Param("lastName") String lastName);

}
