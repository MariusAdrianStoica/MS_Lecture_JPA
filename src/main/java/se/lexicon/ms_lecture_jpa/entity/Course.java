package se.lexicon.ms_lecture_jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    private List<Student> participants;
    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getParticipants() {
        if (participants == null) participants = new ArrayList<>();
        return participants;
    }

    public void setParticipants(List<Student> participants) {
        this.participants = participants;
    }

    // because we have a list of students,
    // -> it is better to have 2 helper methods
    public void addStudent(Student student){
        if (student == null) throw new IllegalArgumentException("Student was null");
        if (participants == null) participants = new ArrayList<>();
        participants.add(student);
        //in case of bidirectional relationship,
        // -> we need to inform also the other side
        student.getEnrolledCourses().add(this);
    }
    public void removeStudent(Student student){
        if (student == null) throw new IllegalArgumentException("Student was null");

        student.getEnrolledCourses().remove(this);
        participants.remove(student);


    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
