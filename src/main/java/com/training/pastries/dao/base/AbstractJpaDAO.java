package com.training.pastries.dao.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractJpaDAO<ID, T extends Serializable> implements BaseDao<ID, T> {
    protected final EntityManager entityManager;

    protected abstract Class<T> getClazz();

    @SuppressWarnings("unchecked")
    public AbstractJpaDAO() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Optional.of(entityManager.find(getClazz(), id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return entityManager.createQuery("from " + getClazz().getName()).getResultList();
    }

    @Override
    public T create(T entity) {
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            entityManager.persist(entity);
            et.commit();
            return entity;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public T update(T entity) {
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            T updatedEntity = entityManager.merge(entity);
            et.commit();
            return updatedEntity;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(T entity) {
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            entityManager.remove(entity);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(ID entityId) {
        Optional<T> entity = findOne(entityId);
        if (entity.isPresent()) {
            delete(entity.get());
        } else {
            throw new NoSuchElementException("Entity not found for id " + entityId);
        }
    }
}
