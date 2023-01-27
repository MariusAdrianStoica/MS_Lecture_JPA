package se.lexicon.ms_lecture_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ms_lecture_jpa.entity.Course;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository // step1: add @Repository
public class CourseDaoImpl implements CourseDao{

    @PersistenceContext // step2: add entity Manager and @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional //step3: add @Transactional to the methods
    public Course persist(Course course) {
        entityManager.persist(course);
        return course;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Course> findAll() {
        return entityManager.createQuery("SELECT c FROM Course c").getResultList();
    }

    @Override
    @Transactional
    public Course update(Course course) {
        return entityManager.merge(course);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        entityManager.remove(entityManager.find(Course.class, id));

    }
}
