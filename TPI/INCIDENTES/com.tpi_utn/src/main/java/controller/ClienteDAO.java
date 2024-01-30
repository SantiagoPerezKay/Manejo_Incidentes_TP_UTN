package controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.ConexionHibernate;
import model.Cliente;

public class ClienteDAO implements CrudInterface<Cliente> {

    @Override
    public void insertar(Cliente objeto) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(objeto);
        session.getTransaction().commit();
      
    }

    @Override
    public void actualizar(Cliente objeto) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(objeto);
        session.getTransaction().commit();
     
    }

    @Override
    public void eliminar(Cliente objeto) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(objeto);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Cliente leer(Integer id) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Cliente cliente = session.find(Cliente.class, id);
        if (cliente != null) {
            Hibernate.initialize(cliente.getListaDeServicios());
            session.close();
        }
        session.close();
        return cliente;
    }

    public Cliente leerPorNombre(String nombre) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<Cliente> query = session.createQuery(
                "SELECT c FROM Cliente c WHERE c.nombreCliente = :nombre", Cliente.class
        );
        query.setParameter("nombre", nombre);
        Cliente cliente = query.uniqueResult();
        if (cliente != null) {
            Hibernate.initialize(cliente.getListaDeServicios());
        }

        return cliente;
    }

    @Override
    public List<Cliente> listarTodos() {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<Cliente> query = session.createQuery("FROM Cliente", Cliente.class);
        List<Cliente> listaClientes = query.getResultList();
        for (Cliente cliente : listaClientes) {
            Hibernate.initialize(cliente.getListaDeServicios());
        }
        
        return listaClientes;
    }


    public void eliminarPorId(Long idCliente) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(idCliente);
        session.getTransaction().commit();
        session.close();
    }
}
