package com.real.estate.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagedata;

    @ManyToOne
    @Cascade(CascadeType.PERSIST) // Adjust cascade type as needed
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @Cascade(CascadeType.PERSIST) // Adjust cascade type as needed
    @JoinColumn(name = "property_id")
    private Properties properties;

    // Default constructor
    public Images() {}

    // Parameterized constructor
    public Images(int id, byte[] imagedata, User user, Properties properties) {
        this.id = id;
        this.imagedata = imagedata;
        this.user = user;
        this.properties = properties;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagedata() {
        return imagedata;
    }

    public void setImagedata(byte[] imagedata) {
        this.imagedata = imagedata;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
