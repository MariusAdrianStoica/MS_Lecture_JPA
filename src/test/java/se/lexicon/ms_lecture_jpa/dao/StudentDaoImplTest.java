package se.lexicon.ms_lecture_jpa.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ms_lecture_jpa.entity.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class StudentDaoImplTest {

    //step1: create resources directory in test
    //step2: copy application properties from H2
    //step3: @SpringBootTest in from of the class
    //step4: @AutoConfigureTestDatabase -> to use a temp DB and not our original SqL DB
    //step5: @Transactional -> each method has one transaction
    //step6: @AutoConfigureTestEntityManager -> provides functionalities
    //step7: @DirtiesContext - to remove temp DB at the end of the test


    @Autowired //in order to inject em in StudentDaoImplTest
    TestEntityManager em;

    @Autowired
    StudentDaoImpl testObject;

    String createdStudentId1;
    String createdStudentId2;


    @BeforeEach
    public void setup(){
        // before each test we need data in the storage

        Student studentData1 = new Student("Marius", "Stoica", "marius@yahoo.com", LocalDate.parse("2000-01-01"));
                //we need a constructor in Student class with fName, lName, email, birthdate

        Student studentData2 = new Student("Test", "Teston", "test@yahoo.com", LocalDate.parse("2001-01-01"));

        Student createdStudent1 = em.persist(studentData1);
        //add created testStudent to H2(temp) DB
        Student createdStudent2 = em.persist(studentData2);

        createdStudentId1 = createdStudent1.getId(); //in order to have the id
        createdStudentId2 = createdStudent2.getId();

    }

    @Test
    public void persist(){
        Student studentDataTest = new Student("Peter", "Peter", "peter@yahoo.com",LocalDate.parse("2002-02-02"));

        //in order to get id, status end regDate
        Student createdStudentTest = testObject.persist(studentDataTest);

        /*Assertions.assertNotNull(createdStudentTest);
        Assertions.assertNotNull(createdStudentTest.getId());
         */

        //before starting the test we have to modify in pom.xml
        //-> H2 DB is only for test -> modify the scope:
        // <scope>runtime</scope> into <scope>test</scope>

        // -> MySqL DB is for runtime

        //import static method Assertions directly
        assertNotNull(createdStudentTest);
        assertNotNull(createdStudentTest.getId());

    }
    // todo: add more tests
}
