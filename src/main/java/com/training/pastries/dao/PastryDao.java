package com.training.pastries.dao;

import com.training.pastries.dao.base.AbstractJpaDAO;
import com.training.pastries.dao.entity.Pastry;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class PastryDao extends AbstractJpaDAO<Long, Pastry> {
    @Override
    protected Class<Pastry> getClazz() {
        return Pastry.class;
    }

    public List<Pastry> findPastrysByName(String pastryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pastry> cq = cb.createQuery(Pastry.class);

        Root<Pastry> pastryRoot = cq.from(Pastry.class);
        List<Predicate> predicates = new ArrayList<>();

        if (pastryName != null) {
            predicates.add(cb.like(pastryRoot.get("name"), "%" + pastryName + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

}
