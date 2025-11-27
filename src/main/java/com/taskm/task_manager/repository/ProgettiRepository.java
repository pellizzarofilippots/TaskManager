package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.Progetti;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProgettiRepository {

    @PersistenceContext
    public EntityManager em;


    public ProgettiRepository(EntityManager em) {
        this.em = em;
    }


    public List<Progetti> findAll() {
        String jpql = "select p from Progetti p";
        return em.createQuery(jpql, Progetti.class).getResultList();

    }

    public Progetti findById(Long id) {
        String jpql = "select p from Progetti p where id= :id";
        return em.createQuery(jpql, Progetti.class).setParameter("id", id).getSingleResult();


    }

    @Transactional
    public void save(Progetti progetti) {
        em.persist(progetti);


    }
}
