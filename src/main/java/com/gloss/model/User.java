package com.gloss.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<EmailAddress> emailAddress = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_projects",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects = new HashSet<>();

   /* public void addEmail(String eMailAddress){
        this.getEmailAddress()
                .add(EmailAddress.builder()
                        .emailAddress(eMailAddress)
                        .build());
    }

    public void removeEmail(String eMailAddress){
        List <EmailAddress> deletedEmail = this.getEmailAddress().stream()
                .filter(emailAddress1 -> emailAddress1.getEmailAddress() == eMailAddress)
                .collect(Collectors.toList());
        this.getEmailAddress().remove(deletedEmail.get(0));
    }*/

}
