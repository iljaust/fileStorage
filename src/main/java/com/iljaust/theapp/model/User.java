package com.iljaust.theapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "Last name is mandatory")
    private String secondName;
    @Column(name = "phone_number")
    @NotBlank(message = "Phone number name is mandatory")
    private String phoneNumber;
    @Column(name = "password")
    @NotBlank(message = "Password number name is mandatory")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name="enabled")
    private Boolean enabled;
    @Column(name="code")
    private String verCode;
    @OneToMany
    @JoinTable(name = "user_files",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "file_id", referencedColumnName = "id")})
    private List<File> files;
    @OneToMany
    private List<Event> events;

    public User(String username, String firstName, String secondName, String password, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phoneNumber = phoneNumber;

    }

}
