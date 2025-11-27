package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.Anagrafica;
import com.taskm.task_manager.model.Utenti;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public class UtentiRepository {
    @PersistenceContext
    private EntityManager em;

    public UtentiRepository(EntityManager em) {
        this.em = em;
    }

    public List<Utenti> findAll() {
        String jpql = "Select u from Utenti u";
        return em.createQuery(jpql, Utenti.class).getResultList();

    }

    // GET BY ID (aggiunto)
    public Optional<Utenti> findById(Long id) {
        // Il metodo find() è il modo standard per recuperare un'entità per chiave primaria
        Utenti utente = em.find(Utenti.class, id);
        return Optional.ofNullable(utente);
    }

    public Optional<Utenti> findByUsername(String username) {
        String jpql = "SELECT u FROM Utenti u WHERE u.userid = :username";
        try {
            Utenti utente = em.createQuery(jpql, Utenti.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(utente);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // CREATE
    // L'annotazione @Transactional è obbligatoria per le operazioni di scrittura (persist)
    @Transactional
    public Utenti create(Utenti utente) {
        // Persist dell'utente e dell'anagrafica associata
        if (utente.getAnagrafica() != null) {
            em.persist(utente.getAnagrafica());
        }
        em.persist(utente);
        return utente;
    }

    // UPDATE (aggiunto)
    // Merge è usato per sincronizzare lo stato dell'entità con il database.
    @Transactional
    public Utenti update(Utenti utente) {
        return em.merge(utente);
    }

    // DELETE BY ID (aggiunto)
    // Trova l'entità per ID e la rimuove
    @Transactional
    public Utenti deleteById(Long id) {

        Utenti utente = em.find(Utenti.class, id);

            em.remove(utente);
            return utente;




}}