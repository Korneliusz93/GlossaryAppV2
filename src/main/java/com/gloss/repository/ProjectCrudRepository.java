package com.gloss.repository;

import com.gloss.model.Category;
import com.gloss.model.Project;
import com.gloss.model.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Registered
public interface ProjectCrudRepository extends JpaRepository<Project,Integer> {

    @Query("FROM Project ORDER BY projectName")
    List<Project> findAllOrderByProjectName();

}
