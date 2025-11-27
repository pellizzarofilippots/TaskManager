package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.RuoliUtente;
import com.taskm.task_manager.model.StatiUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatiUtenteRepository {
    @PersistenceContext
    private EntityManager em;


        public StatiUtenteRepository(EntityManager em) {
            this.em = em;
        }

        public List<StatiUtente> findAll() {
            String jpql = "select u from StatiUtente u";
            return em.createQuery(jpql, StatiUtente.class).getResultList();
        }

        public StatiUtente findById(Long id) {
            String jpql = "select r from  StatiUtente r where r.id = :id";
            return em.createQuery(jpql, StatiUtente.class).setParameter("id", id).getSingleResult();

        }



}
