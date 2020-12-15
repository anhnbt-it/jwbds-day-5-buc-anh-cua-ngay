package org.example.service;

import org.example.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CommentSvcImpl implements CommentSvc {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        sessionFactory = new Configuration()
                .configure("hibernate.conf.xml")
                .buildSessionFactory();
//        sessionFactory.close(); // Tao Bang Database
        entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public Comment update(Comment comment) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Comment origin = findById(comment.getId());
            origin.setRating(comment.getRating());
            origin.setAuthor(comment.getAuthor());
            origin.setMessage(comment.getMessage());
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Comment> findAll() {
        String queryStr = "SELECT c FROM Comment c";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        return query.getResultList();
    }

    @Override
    public void save(Comment comment) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            comment.setLikeCount(0);
            session.saveOrUpdate(comment);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Comment like(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Comment comment = findById(id);
            comment.setLikeCount(comment.getLikeCount()+1);
            session.saveOrUpdate(comment);
            transaction.commit();
            return comment;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Comment findById(Long id) {
        String queryStr = "SELECT c FROM Comment c WHERE c.id = :id";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
