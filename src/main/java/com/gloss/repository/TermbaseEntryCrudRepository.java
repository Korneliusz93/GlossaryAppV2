package com.gloss.repository;

import com.gloss.model.Project;
import com.gloss.model.TermbaseEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermbaseEntryCrudRepository extends JpaRepository<TermbaseEntry,Integer> {
}
