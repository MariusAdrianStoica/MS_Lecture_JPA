package se.lexicon.ms_lecture_jpa.dao;

import se.lexicon.ms_lecture_jpa.entity.Address;
import se.lexicon.ms_lecture_jpa.entity.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookDao {

    Book persist(Book book);
    Optional<Book> findById(Integer id);
    Collection<Book> findAll();

    Book update(Book book);

    void remove(Integer id);

}
