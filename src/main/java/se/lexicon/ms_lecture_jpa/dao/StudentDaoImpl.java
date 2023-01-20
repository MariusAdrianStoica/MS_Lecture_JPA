package se.lexicon.ms_lecture_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ms_lecture_jpa.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

// we don't need to use @Component in order to transform the class in a Spring bean
// -> we use @Repository in Spring Boot - for all Dao layers
@Repository
//@Transactional
// we can add @Transactional in the front of the class and remove from the methods
// but if we want to customize (readOnly = true)
// -> it is better to write in front of every method
public class StudentDaoImpl implements StudentDao{

    //List<Student> storage = new ArrayList<>();
// -> we don't need to define storage because we save the students directly to DB

    //@PersistenceContext instead of @Autowired
    @PersistenceContext
    EntityManager entityManager;
    // in Entity Manager we can execute all operation to populate DB
    // with @PersistenceContext, we injected entityManager into StudentDaoImpl
    // entityManager uses DB described in application.properties (lecture_jpa_db)

    @Override
    @Transactional //@Transactional -from spring transaction annotation
    public Student persist(Student student) {
        if(student == null)throw new IllegalArgumentException ("student was null");
        // query: INSERT INTO STUDENT(firstName, lastName, email, birthdate) VALUES("A", "B","C@Y","2020-01-01");

        entityManager.persist(student);
        //entityManager.persist() -> predefined method to insert data to DB
        //id will be generated automatically
        return student;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(String id) {
        return Optional.ofNullable(entityManager.find(Student.class,id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findByEmail(String email) { //Mehrdad@lexicon.se
        // email.toLowerCase(); // -> to ignore case sensitive
        return entityManager
                .createQuery("SELECT s FROM Student s WHERE UPPER(s.email) = upper(:e)", Student.class)
                .setParameter("e", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Student> findByNameContains(String name) {
        return entityManager
                .createQuery("SELECT s FROM Student s" +
                        " WHERE " +
                        "upper(s.firstName) LIKE upper(concat('%',:name,'%')) " +
                        "OR " +
                        "upper(s.firstName) LIKE upper(concat('%',:name,'%'))" , Student.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Student> findAll() {
        return entityManager
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    @Transactional
    //transactional is not read-only, because we want to update the field
    public Student update(Student student) {
        return entityManager.merge(student); //if id exists in DB, method will update that row
        // -> merge == update
    }

    @Override
    @Transactional
    public void remove(String id) {
        Student student = entityManager.find(Student.class, id);
        if (student!=null) entityManager.remove(student);
        //else // throw exception - Data Not Find

    }
}
