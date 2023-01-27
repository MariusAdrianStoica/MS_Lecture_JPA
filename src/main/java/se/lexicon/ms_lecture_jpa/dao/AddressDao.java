package se.lexicon.ms_lecture_jpa.dao;

import se.lexicon.ms_lecture_jpa.entity.Address;

import java.util.Collection;
import java.util.Optional;

public interface AddressDao {

    Address persist (Address address); //Create or Save
    Optional<Address> findById(Integer id);
    Collection<Address> findAll();

    Address update(Address address);

    void remove(Integer id);
}
