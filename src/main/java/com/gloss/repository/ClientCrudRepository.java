package com.gloss.repository;

import com.gloss.model.Category;
import com.gloss.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCrudRepository extends JpaRepository<Client,Integer> {
}
