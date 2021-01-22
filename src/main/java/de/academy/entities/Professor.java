package de.academy.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "expertise")
    private String expertise;

    @Column(name = "room")
    private String room;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "professor_id")
    private List<Assistant> assistants;

    @OneToMany(mappedBy = "professor",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Lecture> lectures;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Professor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expertise='" + expertise + '\'' +
                ", room='" + room + '\'' +
                '}';
    }

    public List<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<Assistant> assistants) {
        this.assistants = assistants;
    }

    // uni-directional convenience method; Professor -> Assistant;
    public void addAssistant(Assistant assistant) {

        if (assistants == null) {
            assistants = new ArrayList<>();
        }
        assistants.add(assistant);
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    // bi-directional convenience method; Professor < - > Lecture
    public void addLecture(Lecture lecture) {

        if(lectures == null) {
            lectures = new HashSet<>();
        }
        lectures.add(lecture);
        lecture.setProfessor(this);
    }

    // bi-directional convenience method; Professor < - > Lecture
    public void removeLecture(Lecture lectureForRemoval) {

        if (lectures == null) {
            lectures = new HashSet<>();
        }

/*        Iterator<Lecture> iterator = lectures.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().equals(lectureForRemoval)) {
                iterator.remove();
            }
        }*/

        Set<Lecture> found = new HashSet<>();
        for(Lecture lecture : lectures) {
            if(lecture.equals(lectureForRemoval)) {
                lecture.setProfessor(null);
                found.add(lecture);
            }
        }
        lectures.removeAll(found);
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
        if(!(obj instanceof Professor)) {
            return false;
        }
        Professor professor = (Professor) obj;
        return this.id != null && Objects.equals(this.id, professor.getId());
    }
}