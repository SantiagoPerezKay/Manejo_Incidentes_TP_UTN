package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.ConexionHibernate;
import model.Servicio;

public class ServicioDAO implements CrudInterface<Servicio> {

    @Override
    public void insertar(Servicio objeto) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(objeto);
            session.getTransaction().commit();
        }
    }

    @Override
    public void actualizar(Servicio objeto) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(objeto);
            session.getTransaction().commit();
        }
    }

    @Override
    public void eliminar(Servicio objeto) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(objeto);
            session.getTransaction().commit();
        }
    }

    @Override
    public Servicio leer(Integer id) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.find(Servicio.class, id);
        }
    }

    public Servicio leerPorNombre(String nombre) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Query<Servicio> query = session.createQuery(
                    "SELECT s FROM Servicio s WHERE s.nombreServicio = :nombre", Servicio.class);
            query.setParameter("nombre", nombre);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Servicio> listarTodos() {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Query<Servicio> query = session.createQuery("FROM Servicio", Servicio.class);
            return query.getResultList();
        }
    }
}
