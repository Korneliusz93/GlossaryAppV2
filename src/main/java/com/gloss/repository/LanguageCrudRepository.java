package com.gloss.repository;

import com.gloss.model.Category;
import com.gloss.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageCrudRepository extends JpaRepository<Language,Integer> {

}
