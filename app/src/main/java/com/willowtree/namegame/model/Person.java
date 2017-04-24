package com.willowtree.namegame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek on 4/20/2017.
 */

public class Person {

    private String id;
    private String type;
    private String slug;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private Headshot headshot;
    private ArrayList<String> socialLinks;

    public Person(String id, String type, String slug, String jobTitle, String firstName, String lastName, Headshot headshot, ArrayList<String> socialLinks) {
        this.id = id;
        this.type = type;
        this.slug = slug;
        this.jobTitle = jobTitle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.headshot = headshot;
        this.socialLinks = socialLinks;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSlug() {
        return slug;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Headshot getHeadshot() {
        return headshot;
    }

    public List<String> getSocialLinks() {
        return socialLinks;
    }
}
