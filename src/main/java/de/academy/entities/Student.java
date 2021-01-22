package de.academy.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "semester")
    private Integer semester;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "lecture_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    private Set<Lecture> lectures;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Student() {
    }

    public Student(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = 1;
        this.lectures = new HashSet<>();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", semester=" + semester +
                '}';
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    // many-to-many convenience method
    public void addLecture(Lecture lecture) {
        if (lectures == null) {
            lectures = new HashSet<>();
        }
        lectures.add(lecture);
    }

    public void removeLecture(long lectureId) {

        if (lectures == null) {
            lectures = new HashSet<>();
        }

        lectures.removeIf(lecture -> lecture.getId() == lectureId);

/*        lectures = lectures
                .stream()
                .filter(lecture -> lecture.getId() != lectureId)
                .collect(Collectors.toSet());*/

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Student)) {
            return false;
        }
        Student student = (Student) obj;
        return this.id != null && Objects.equals(this.id, student.getId());
    }
}
