package com.gloss.repository;

import com.gloss.model.Category;
import com.gloss.model.Language;
import com.gloss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageCrudRepository extends JpaRepository<Language,Integer> {


}
