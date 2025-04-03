package com.scm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
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
    private String phoneNumber;
    
    //information
    @Getter(value = AccessLevel.NONE)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

}
