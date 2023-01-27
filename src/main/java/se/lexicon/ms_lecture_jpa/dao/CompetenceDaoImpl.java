package se.lexicon.ms_lecture_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ms_lecture_jpa.entity.Competence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository // step1: add @Repository
public class CompetenceDaoImpl implements CompetenceDao{

    @PersistenceContext // step2: add entity Manager and @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional //step3: add @Transactional to the methods
    public Competence persist(Competence competence) {
        entityManager.persist(competence);
        return competence;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Competence> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Competence.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Competence> findAll() {
        return entityManager.createQuery("SELECT cpt FROM Competence cpt").getResultList();
    }

    @Override
    @Transactional
    public Competence update(Competence competence) {
        return entityManager.merge(competence);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        Competence competence = entityManager.find(Competence.class, id);
        if (competence != null) entityManager.remove(competence);
    }
    //else throw exception
}
