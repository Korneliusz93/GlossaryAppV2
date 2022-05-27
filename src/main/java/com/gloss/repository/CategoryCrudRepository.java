package com.gloss.repository;

import com.gloss.model.Category;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Registered
public interface CategoryCrudRepository extends JpaRepository<Category,Integer> {

    @Query("FROM Category ORDER BY categoryName")
    List<Category> findAllOrderByCategoryName();

    @Query("FROM Category WHERE categoryName = :categoryName")
    List<Category> findAllByCategoryName(@Param("categoryName") String categoryName);
}
