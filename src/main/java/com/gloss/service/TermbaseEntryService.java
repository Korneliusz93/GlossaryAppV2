package com.gloss.service;

import com.gloss.model.Project;
import com.gloss.model.TermbaseEntry;
import com.gloss.model.Language;
import com.gloss.model.User;
import com.gloss.repository.TermbaseEntryCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TermbaseEntryService {

    private static final Logger LOGGER =  LoggerFactory.getLogger(TermbaseEntryService.class);

    @Autowired
    private TermbaseEntryCrudRepository termbaseEntryCrudRepository;

    public TermbaseEntry addTE(String term, String termDescription, Project project, Language language, User creator) {
        TermbaseEntry termbaseEntry = TermbaseEntry.builder()
                .term(term)
                .termDescription(termDescription)
                .language(language)
                .creator(creator)
                .build();

        return termbaseEntryCrudRepository.save(termbaseEntry);

    }

    /*public void linkTEToProj(TermbaseEntry termbaseEntry, Project project){

    }*/

    public void updateTE(TermbaseEntry termbaseEntry, String description, User user) {

    }

    public TermbaseEntry getTE(int id){

    }

    //delete termbase entry
    public void deleteTE(String TEName){

    }

}
