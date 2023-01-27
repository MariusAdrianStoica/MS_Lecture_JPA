package se.lexicon.ms_lecture_jpa.dao;

import se.lexicon.ms_lecture_jpa.entity.Competence;

import java.util.Collection;
import java.util.Optional;

public interface CompetenceDao {

    Competence persist(Competence competence);

    Optional<Competence> findById(Integer id);

    Collection<Competence> findAll();

    Competence update(Competence competence);

    void remove(Integer id);
}