package controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.ConexionHibernate;
import model.Especialidad;

public class EspecialidadDAO implements CrudInterface<Especialidad> {

    @Override
    public void insertar(Especialidad objeto) {
    	
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(objeto);
        session.getTransaction().commit();
      
    }

    @Override
    public void actualizar(Especialidad objeto) {
    	
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(objeto);
        session.getTransaction().commit();
      
    }

    @Override
    public void eliminar(Especialidad objeto) {
    	
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(objeto);
        session.getTransaction().commit();
       
        
    }

    @Override
    public Especialidad leer(Integer id) {
    	
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Especialidad especialidad = session.find(Especialidad.class, id);
       
        return especialidad;
    }

    public Especialidad leer(String nombre) {
    		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
    	
             Session session = sessionFactory.openSession();
            
            Query<Especialidad> query = session.createQuery(
                    "SELECT e FROM Especialidad e WHERE e.nombreEspecialidad = :nombre", Especialidad.class
            												);
            query.setParameter("nombre", nombre);
            return query.uniqueResult();
     
    }

	@Override
	   public List<Especialidad> listarTodos() {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<Especialidad> query = session.createQuery("FROM Especialidad", Especialidad.class);
        List<Especialidad> listaEspecialidades = query.getResultList();
       
        return listaEspecialidades;
    }
    
 
}