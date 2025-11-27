package com.taskm.task_manager.repository;


import com.taskm.task_manager.model.RuoliUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RuoloUtenteRepository {

    @PersistenceContext
    private EntityManager em;

    public  RuoloUtenteRepository(EntityManager em) {
        this.em=em;
    }

    public List<RuoliUtente> findAll(){
        String jpql = "select u from RuoliUtente u";
        return em.createQuery(jpql, RuoliUtente.class).getResultList();
    }
    public RuoliUtente findById(Long id){
        String jpql ="select r from  RuoliUtente r where r.id = :id";
        return em.createQuery(jpql,RuoliUtente.class).setParameter("id",id).getSingleResult();

    }
}
