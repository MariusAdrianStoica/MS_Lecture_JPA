package se.lexicon.ms_lecture_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ms_lecture_jpa.dao.AddressDao;
import se.lexicon.ms_lecture_jpa.dao.BookDao;
import se.lexicon.ms_lecture_jpa.dao.CourseDao;
import se.lexicon.ms_lecture_jpa.dao.StudentDao;
import se.lexicon.ms_lecture_jpa.entity.Address;
import se.lexicon.ms_lecture_jpa.entity.Book;
import se.lexicon.ms_lecture_jpa.entity.Course;
import se.lexicon.ms_lecture_jpa.entity.Student;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentDao studentDao;

    @Autowired
    AddressDao addressDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    CourseDao courseDao;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //used to add some date in DB during runtime

        /*
        System.out.println("####################");
        Student createdStudent1= studentDao.persist(new Student(
                "Test",
                "Test",
                "test.test@test.se",
                LocalDate.parse("2000-01-01")));

        System.out.println("\n Student : -> "+createdStudent1.toString());
        System.out.println("####################");

        */

        ex5();

    }
    public void ex1(){
        // create an address for a Student
        Address addressData = new Address("Växjö", "Teleborg", "35256");

        Address createdAddress = addressDao.persist(addressData);

        Student studentData = new Student("Test", "Test", "test1.test@test.se", LocalDate.parse("2000-01-01"));
        studentData.setAddress(createdAddress);
        //before saving the student in the DB, we add the address for the student
        Student createdStudent = studentDao.persist(studentData);
    }


    public void ex2(){
        Student studentData = new Student(
                "Test",
                "Test",
                "test1.test@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256"));

        Student createdStudent = studentDao.persist(studentData);

        studentDao.remove(createdStudent.getId());
    }

    public void ex3(){
        Student studentData = new Student(
                "Test",
                "Test",
                "test1.test@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256"));

        Student createdStudent = studentDao.persist(studentData);


        addressDao.findAll().forEach(address ->{
                    System.out.println(address);
                    System.out.println(address.getStudent());
                }
                );
    }


    public void ex4(){

        Student studentData = new Student(
                "Test",
                "Test",
                "test1.test@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256"));

        Student createdStudent = studentDao.persist(studentData);

        Book ocaBookData = new Book("OCA");
        Book ocpBookData = new Book("OCP");

        ocaBookData.setBorrower(createdStudent);
        ocpBookData.setBorrower(createdStudent);

        Book createdOcaBook = bookDao.persist(ocaBookData);
        Book createdOcpBook = bookDao.persist(ocpBookData);

        createdStudent.borrowBook(createdOcpBook);
        createdStudent.borrowBook(createdOcaBook);

        Optional<Student> optionalStudent = studentDao.findById(createdStudent.getId());
        if (optionalStudent.isPresent()){
            System.out.println("\nSize: "+optionalStudent.get().getBorrowedBooks().size()+"\n");
        }


        createdStudent.returnBook(createdOcaBook);
        if (optionalStudent.isPresent()){
            System.out.println("\nSize: "+optionalStudent.get().getBorrowedBooks().size()+"\n");
        }

        studentDao.remove(createdStudent.getId());

    }
    public void ex5(){

        Course java = courseDao.persist(new Course("Java"));
        Course cSharp = courseDao.persist(new Course("C#"));

        Student studentData = studentDao.persist(new Student(
                "Test",
                "Test",
                "test1.test@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256")));

        studentData.enrollCourse(java);
        Student createdStudent1 = studentDao.persist(studentData);

        cSharp.addStudent(createdStudent1);








    }
}
