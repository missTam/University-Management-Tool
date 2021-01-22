package de.academy.dto;

import de.academy.entities.Lecture;
import de.academy.entities.User;

import java.util.Set;

public class StudentDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private Integer semester;
    private Set<Lecture> lectures;
    private User user;

    public StudentDTO() {}

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

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User rawUser) {
        this.user = new User();
        user.setId(rawUser.getId());
        user.setRole(rawUser.getRole());
    }
}
