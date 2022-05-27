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
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TermbaseEntry> entries;

    /*public void addEntryToProj(TermbaseEntry tEntry){
        entries.add(tEntry);
    }

    public void removeEntry(TermbaseEntry tEntry){
        this.entries.remove(tEntry);
    }*/

    @Column(name = "project_name")
    private String projectName;

    @OneToOne
    private Category category;

    @OneToOne
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects", cascade = CascadeType.MERGE)
    private Set<User> users = new HashSet<>();

    /*public void addUser(User user){
        this.users.add(user);
        user.getProjects().add(this);
    }

    public void removeUser(User user){
        boolean check = this.users.contains(user);
        this.users.remove(user);
        user.getProjects().remove(this);
    }*/



}
