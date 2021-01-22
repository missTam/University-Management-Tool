package de.academy.dto;

import de.academy.entities.Lecture;
import de.academy.entities.User;

import java.util.Set;

public class ProfessorDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String expertise;
    private String room;
    private Set<Lecture> lectures;
    private User user;

    public ProfessorDTO() {}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User rawUser) {
        this.user = new User();
        user.setId(rawUser.getId());
        user.setRole(rawUser.getRole());
    }

}
