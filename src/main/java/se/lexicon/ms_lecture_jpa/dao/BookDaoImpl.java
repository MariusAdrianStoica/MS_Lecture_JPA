package se.lexicon.ms_lecture_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ms_lecture_jpa.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository // step1: add @Repository
public class BookDaoImpl implements BookDao{

    @PersistenceContext // step2: add entity Manager and @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional //step3: add @Transactional to the methods
    public Book persist(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b").getResultList();
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        entityManager.remove(entityManager.find(Book.class, id));

    }
}
