package com.gloss.service;

import com.gloss.model.Project;
import com.gloss.model.TermbaseEntry;
import com.gloss.model.Language;
import com.gloss.model.User;
import com.gloss.repository.LanguageCrudRepository;
import com.gloss.repository.ProjectCrudRepository;
import com.gloss.repository.TermbaseEntryCrudRepository;
import com.gloss.repository.UserCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TermbaseEntryService {

    private static final Logger LOGGER =  LoggerFactory.getLogger(TermbaseEntryService.class);

    @Autowired
    private TermbaseEntryCrudRepository termbaseEntryCrudRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private ProjectCrudRepository projectCrudRepository;

    @Autowired
    private LanguageCrudRepository languageCrudRepository;

    public Optional<TermbaseEntry> addTE(String term, String termDescription, int idProj, String languageCode, int idUsr) {
        Optional<User> user = userCrudRepository
                .findById(idUsr);

        Optional<Project> project = projectCrudRepository
                .findById(idProj);

        List<Language> languages = languageCrudRepository
                .findAll().stream()
                .filter(language1 -> language1.getLanguageCode().equals(languageCode))
                .collect(Collectors.toList());

        if (project.get().getUsers().contains(user.get())){
            TermbaseEntry termbaseEntry = TermbaseEntry.builder()
                    .term(term)
                    .termDescription(termDescription)
                    .language(languages.get(0))
                    .creator(user.get())
                    .entryCreationDate(LocalDate.now())
                    .build();

            project.get().getEntries().add(termbaseEntry);
            return Optional.of(termbaseEntryCrudRepository.save(termbaseEntry));
        }

        LOGGER.warn(String.format("No access to %s project for user: %s",project.toString(),user.toString()));
        return Optional.empty();

    }

    public Optional<TermbaseEntry> addTE(int idTerm, String termDescription, int idUsr) {
        Optional<User> user = userCrudRepository
                .findById(idUsr);
        TermbaseEntry entry = termbaseEntryCrudRepository.getById(idTerm);
        List<Project> projs = user.get().getProjects().stream()
                .filter(project -> project.getEntries().contains(entry) == true)
                .collect(Collectors.toList());

        if (!projs.isEmpty()){

            Optional<TermbaseEntry> updatedTE = termbaseEntryCrudRepository
                    .findById(idTerm);
            updatedTE.get()
                    .setUpdater(user.get());
            updatedTE.get()
                    .setEntryUpdateDate(LocalDate.now());
            updatedTE.get()
                    .setTermDescription(termDescription);

            return termbaseEntryCrudRepository
                    .findById(idTerm)
                    .map(updatedTe -> termbaseEntryCrudRepository.save(updatedTe));
        }

        LOGGER.warn(String.format("No access for user: %s", user.toString()));
        return Optional.empty();
    }

    public List<TermbaseEntry> getTEList(int idProj){
        Optional<Project> project = projectCrudRepository
                .findById(idProj);
        return project.get().getEntries().stream()
                .collect(Collectors.toList());
    }

    //delete termbase entry
    public void deleteTE(String TEName){

    }

}
