package com.scm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "users") 
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(name = "user_name",nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    @Column(length = 1000)
    private String about;
    
    @Column(length = 1000)
    private String profilePic;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;
    
    //information
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;


    ///self  google , fb, 
    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    //add more fileds
    @OneToMany(mappedBy = "user",cascade = jakarta.persistence.CascadeType.ALL,fetch = jakarta.persistence.FetchType.LAZY,orphanRemoval = true) 
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = jakarta.persistence.FetchType.LAZY)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //list of roles[admin,user]
        //collection of simplegrantedauthority{admin,user}
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
