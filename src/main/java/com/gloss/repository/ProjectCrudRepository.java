package com.gloss.repository;

import com.gloss.model.Category;
import com.gloss.model.Project;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ProjectCrudRepository extends JpaRepository<Project,Integer> {

}
