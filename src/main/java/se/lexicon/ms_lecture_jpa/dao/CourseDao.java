package se.lexicon.ms_lecture_jpa.dao;


import se.lexicon.ms_lecture_jpa.entity.Course;

import java.util.Collection;
import java.util.Optional;

public interface CourseDao {

    Course persist(Course course);
    Optional<Course> findById(Integer id);
    Collection<Course> findAll();

    Course update(Course course);

    void remove(Integer id);

}
