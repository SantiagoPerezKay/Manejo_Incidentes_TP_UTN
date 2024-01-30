package controller;

import java.util.List;

import model.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.ConexionHibernate;
import model.Servicios;

public class ServiciosDAO implements CrudInterface<Servicios> {
    ClienteDAO clienteDAO = new ClienteDAO();
    @Override
    public void insertar(Servicios servicios) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(servicios);
        session.getTransaction().commit();
    }

    @Override
    public void actualizar(Servicios servicios) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(servicios);
        session.getTransaction().commit();
    }

    @Override
    public void eliminar(Servicios servicios) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(servicios);
        session.getTransaction().commit();
        System.out.println("Servicio eliminado");
    }

    @Override
    public Servicios leer(Integer id) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Servicios servicios = session.find(Servicios.class, id);
        return servicios;
    }

    public List<Servicios> listarTodos() {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query<Servicios> query = session.createQuery("FROM Servicios", Servicios.class);

        return query.getResultList();
    }


    public void eliminarPorID(Integer idCliente) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query<Servicios> query = session.createQuery("FROM Servicios WHERE cliente.idCliente = :clienteId", Servicios.class);
            query.setParameter("clienteId", idCliente);
            List<Servicios> serviciosList = query.getResultList();


            // Elimina cada servicio asociado al cliente para que no genere error de integridad
            for (Servicios servicio : serviciosList) {
                session.delete(servicio);
            }
            Cliente cli=clienteDAO.leer(idCliente);
            session.delete(cli);
            session.getTransaction().commit();
            System.out.println("Servicio eliminado");
            session.close();
        } catch (Exception e) {
            System.out.println("ocurrio un error" + e.getMessage());
        }
    }

}
