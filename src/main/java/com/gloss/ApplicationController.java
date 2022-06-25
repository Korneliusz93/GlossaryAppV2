package com.gloss;

import com.gloss.model.*;
import com.gloss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ApplicationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailAddressService emailAddressService;
    @Autowired
    private UserPermissionsService userPermissionsService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TermbaseEntryService termbaseEntryService;
    @Autowired
    private LanguageService languageService;


    @PostMapping("/users")
    public UserDto addUser(@RequestBody UserDto userDto){
        return asDto(
                userService.addUser(
                        userDto.getFirstName(), userDto.getLastName(),
                        userDto.getUsername(), userDto.getPassword(), userDto.getEmailAddress()));

    }

    @GetMapping("/users")
    public List<UserDto> getUsers(@RequestParam(value = "sortBy", required = false) String sortBy){
        return userService.getUsers(sortBy).stream().map(this::asDto).collect(Collectors.toList());
    }

    @PutMapping("/users/{id}/setpassword")
    public UserDto updatePassword(@PathVariable("id") Integer id,
                              @RequestBody UserDto userDto){
        return userService
                .updatePassword(id,userDto.getPassword())
                .map(this::asDto)
                .orElseThrow();
    }

    @PutMapping("/users/{id}/addemail")
    public UserDto addEmailAddress(@PathVariable("id") Integer id,
                              @RequestBody UserDto userDto){
        return userService
                .addEmailAddress(id,userDto.getEmailAddress())
                .map(this::asDto)
                .orElseThrow();
    }

    @PutMapping("/projects/{idProj}/addUser/{idUsr}")
    public UserDto addUserToProject(@PathVariable("idProj") Integer idProj, @PathVariable("idUsr") Integer idUsr/*,
                                   @RequestBody UserDto userDto*/){
        return userPermissionsService
                .addUserToProject(idProj,idUsr)
                .map(this::asDto)
                .orElseThrow();
    }

    @PostMapping("/projectentries/projects/{idProj}/lang/{langCode}/users/{idUsr}/addentry")
    public TermbaseEntryDto addTe(@PathVariable("idProj") Integer idProj, @PathVariable("idUsr") Integer idUsr,
                                  @PathVariable("langCode") String langCode,
                                  @RequestBody  TermbaseEntryDto termbaseEntryDto){
        return termbaseEntryService
                .addTE(termbaseEntryDto.getTerm(),termbaseEntryDto.getTermDescription(),idProj,langCode,idUsr)
                .map(this::asDto)
                .orElseThrow();
    }

    @PutMapping("/projectentries/{idTE}/users/{idUsr}/addentry")
    public TermbaseEntryDto addTe(@PathVariable("idTE") Integer idTe, @PathVariable("idUsr") Integer idUsr,
                                  @RequestBody  TermbaseEntryDto termbaseEntryDto){
        return termbaseEntryService
                .addTE(idTe,termbaseEntryDto.getTermDescription(),idUsr)
                .map(this::asDto)
                .orElseThrow();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) { userService.deleteUser(id); }

    @PostMapping("/projects")
    public ProjectDto addProject(@RequestBody ProjectDto projectDto){
        return asDto(
                projectService.addProject(
                        projectDto.getProjectName()));
    }

    @PostMapping("/languages")
    public LanguageDto addLanguage(@RequestBody LanguageDto languageDto){
        return asDto(
                languageService.addLanguage(
                        languageDto.getLanguageCode()));
    }

    @GetMapping("/languages")
    public List<LanguageDto> getLanguages(){
        return languageService.getLanguages().stream().map(this::asDto).collect(Collectors.toList());
    }

    @GetMapping("/projects")
    public List<ProjectDto> getProjects(){
        return projectService.getProjects().stream().map(this::asDto).collect(Collectors.toList());
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable("id") Integer id) { projectService.deleteProject(id); }

    @GetMapping("/emails")
    public List<EmailAddressDto> getEmailAddresses(){
        return emailAddressService.getEmailAddresses().stream().map(this::asDto).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}/emails")
    public List<EmailAddressDto> getEmailAddresses(@PathVariable("id") Integer id){
        return userService.getUserEmailAddresses(id).stream().map(this::asDto).collect(Collectors.toList());
    }

    @GetMapping("/termbaseentries/project/{id}")
    public List<TermbaseEntryDto> getTermbaseEntries(@PathVariable("id") Integer id){
        return termbaseEntryService.getTEList(id).stream().map(this::asDto).collect(Collectors.toList());
    }

    public UserDto asDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public EmailAddressDto asDto(EmailAddress emailAddress){
        return EmailAddressDto.builder()
                .id(emailAddress.getId())
                .emailAddress(emailAddress.getEmailAddress())
                .build();
    }

    public ProjectDto asDto(Project project){
        return ProjectDto.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .build();
    }

    public LanguageDto asDto(Language language){
        return LanguageDto.builder()
                .languageCode(language.getLanguageCode())
                .build();
    }

    public TermbaseEntryDto asDto(TermbaseEntry termbaseEntry){
        if(!Objects.isNull(termbaseEntry.getUpdater())){
            return TermbaseEntryDto.builder()
                    .id(termbaseEntry.getId())
                    .term(termbaseEntry.getTerm())
                    .termDescription(termbaseEntry.getTermDescription())
                    .language(termbaseEntry.getLanguage().getLanguageCode())
                    //.author(termbaseEntry.getUpdater())
                    .entryInputDate(termbaseEntry.getEntryUpdateDate()) //obsluga
                    .build();
        }
        return TermbaseEntryDto.builder()
                .id(termbaseEntry.getId())
                .term(termbaseEntry.getTerm())
                .termDescription(termbaseEntry.getTermDescription())
                .language(termbaseEntry.getLanguage().getLanguageCode())
                //.author(termbaseEntry.getCreator())
                .entryInputDate(termbaseEntry.getEntryCreationDate()) //obsluga
                .build();
    }


}