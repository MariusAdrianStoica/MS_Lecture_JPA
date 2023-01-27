package se.lexicon.ms_lecture_jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = ?1")
// instead of ":id", we can use ?1 (first question mark)
public class Address {

    @Id //if we add @Entity, we need also to declare a primary key
    // -> @Id (javax.persistence)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //autoincrement for int values
    private int id;
    @Column(nullable = false,length = 80)
    private String city;
    @Column(length = 80)
    private String street;
    @Column(nullable = false,length = 6)
    //nullable=false -> zipCode is mandatory
    private String zipCode;

    //in order to have Bi-directional relation, we define a field Student in Address

    @OneToOne(mappedBy = "address")
    private Student student;
    // we don't use JoinColumn, instead we use mappedBy
    // -> we know that 1 student has only one address
    // considering that field from Student table (address), we know what Student has a specific address


    public Address() {
        //if we use @Entity, default constructor is mandatory (without fields)
        //in order to map the class to the DB
    }

    public Address(String city, String street, String zipCode) {
        // we don't need id when we use persist (create) a new address
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address(int id, String city, String street, String zipCode) {
        //when we want fetch data from DB, we need a full constructor
        this.id = id;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, zipCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
