package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.Anagrafica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class AnagraficaRepository {

    @PersistenceContext
    private EntityManager em;

    public AnagraficaRepository(EntityManager em) {
        this.em = em;
    }

    // FIND ALL
    public List<Anagrafica> findAll() {
        String jpql = "SELECT a FROM Anagrafica a";
        return em.createQuery(jpql, Anagrafica.class).getResultList();
    }

    // FIND BY ID
    public Optional<Anagrafica> findById(Long id) {
        Anagrafica ana = em.find(Anagrafica.class, id);
        return Optional.ofNullable(ana);
    }

    // CREATE
    @Transactional
    public Anagrafica create(Anagrafica anagrafica) {
        em.persist(anagrafica);
        return anagrafica;
    }

    // UPDATE
    @Transactional
    public Anagrafica update(Anagrafica anagrafica) {
        return em.merge(anagrafica);
    }

    // DELETE BY ID
    @Transactional
    public Anagrafica deleteById(Long id) {
        Anagrafica ana = em.find(Anagrafica.class, id);
        if (ana != null) {
            em.remove(ana);
        }
        return ana;
    }
}
