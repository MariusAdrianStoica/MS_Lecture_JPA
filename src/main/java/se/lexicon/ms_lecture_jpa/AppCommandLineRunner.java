package se.lexicon.ms_lecture_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.ms_lecture_jpa.dao.StudentDao;
import se.lexicon.ms_lecture_jpa.entity.Student;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentDao studentDao;
    @Override
    public void run(String... args) throws Exception {
        //used to add some date in DB during runtime

        System.out.println("####################");
        Student createdStudent1= studentDao.persist(new Student(
                "Test",
                "Test",
                "test.test@test.se",
                LocalDate.parse("2000-01-01")));

        System.out.println("\n Student : -> "+createdStudent1.toString());
        System.out.println("####################");


    }
}
