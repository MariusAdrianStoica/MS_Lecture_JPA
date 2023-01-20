package se.lexicon.ms_lecture_jpa.dao;

import se.lexicon.ms_lecture_jpa.entity.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentDao {

    Student persist(Student student); // create
    Optional<Student> findById(String id);
    Optional<Student> findByEmail(String email);
    Collection<Student> findByNameContains(String name);
    Collection<Student> findAll();
    Student update(Student student);
    void remove(String id);
}
