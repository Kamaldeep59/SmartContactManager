package com.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts")
public class Contact {

    @Id
    private String contactId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 1000)
    private String description;
    private boolean Favorite = false;

    private String websiteLink;
    private String linkedInLink;

    //private List<String> socialLinks = new ArrayList<>();

    @ManyToOne
    private User user;
 
    
    @OneToMany(mappedBy = "contact",cascade = jakarta.persistence.CascadeType.ALL,fetch = jakarta.persistence.FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();



}
