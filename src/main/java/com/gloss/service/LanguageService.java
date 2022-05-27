package com.gloss.service;

import com.gloss.model.Language;
import com.gloss.repository.LanguageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageCrudRepository languageCrudRepository;

    public Language addLanguage(String languageCode) {
        Language language = Language.builder()
                .languageCode(languageCode)
                .build();
        return languageCrudRepository.save(language);
    }

    public Optional<Language> getLanguage(int id){
        return languageCrudRepository.findById(id);
    }

    public List<Language> getLanguages(){
        return languageCrudRepository.findAll();
    }
}
