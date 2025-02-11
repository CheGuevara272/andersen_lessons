package org.andersen_project.repository;

import jakarta.persistence.criteria.CriteriaQuery;
import org.andersen_project.exception.InputException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class HibernateRepository<T extends Serializable> implements Repository<T>, Serializable {
    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public HibernateRepository(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public void update(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Can not save entity", e);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(entityClass);
            criteriaQuery.from(entityClass);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can not find all entities", e);
        }
    }

    @Override
    public Optional<T> findById(Integer id) throws InputException {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(entityClass, id));
        } catch (Exception e) {
            throw new InputException("Can not find entity", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            T entity = session.get(entityClass, id);

            if (entity != null) {
                session.remove(entity);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Can not delete entity", e);
        }
    }
}
