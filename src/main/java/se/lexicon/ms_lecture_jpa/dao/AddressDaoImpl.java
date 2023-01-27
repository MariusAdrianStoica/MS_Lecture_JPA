package se.lexicon.ms_lecture_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ms_lecture_jpa.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository // step1: add @Repository
public class AddressDaoImpl implements AddressDao{

    @PersistenceContext // step2: add @PersistenceContext
    // entityManager access DB through @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Address persist(Address address) {
        // todo: param  validation
        entityManager.persist(address);
        return address;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findById(Integer id) {
        // -> 1:
        // return Optional.ofNullable(entityManager.find(Address.class, id));

        //Address.class == table name
        // JPA will execute query: SELECT * FROM ADDRESS A WHERE A.ID = id;

        /*
        2->:
        return entityManager.createQuery("SELECT a FROM Address a WHERE a.id = :id")
                .setParameter("id", id)
                .getResultStream()
                .findFirst();

         */

        // 3->
        // on top of the Address class, we can declare a query: @NameQuery
        return entityManager.createNamedQuery("Address.findById", Address.class)
                .setParameter(1,id)
                //1 -> first question mark
                .getResultStream()
                .findFirst();

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Address update(Address address) {
        return entityManager.merge(address);
        // merge is executing update if the address exists

    }

    @Override
    @Transactional
    public void remove(Integer id) {
        Address result = entityManager.find(Address.class, id);
        if (result != null) {
            entityManager.remove(result);
        }else{
            //todo: add -> throws a custom exception
        }

    }
}
