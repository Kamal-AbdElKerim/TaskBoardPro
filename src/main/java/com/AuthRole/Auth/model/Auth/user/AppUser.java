package com.AuthRole.Auth.model.Auth.user;


import com.AuthRole.Auth.model.*;
import com.AuthRole.Auth.model.Auth.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class AppUser {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRole> userRoles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Permission> permissions;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;


}
