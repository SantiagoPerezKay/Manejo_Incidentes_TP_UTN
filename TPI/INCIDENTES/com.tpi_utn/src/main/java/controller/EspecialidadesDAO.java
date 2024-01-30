package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.ConexionHibernate;
import model.Especialidad;
import model.Especialidades;
import model.Tecnico;

public class EspecialidadesDAO implements CrudInterface<Especialidades> {

    @Override
    public void insertar(Especialidades especialidades) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(especialidades);
        session.getTransaction().commit();
      
    }

    @Override
    public void actualizar(Especialidades especialidades) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(especialidades);
        session.getTransaction().commit();
       
    }

    @Override
    public void eliminar(Especialidades especialidades) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(especialidades);
        session.getTransaction().commit();
       System.out.println("usuario ELiminado");
    }

    @Override
    public Especialidades leer(Integer id) {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Especialidades especialidades = session.find(Especialidades.class, id);
       
        return especialidades;
    }

    public List<Especialidades> listarTodos() {
        SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<Especialidades> query = session.createQuery("FROM Especialidades", Especialidades.class);
        List<Especialidades> listaEspecialidades = query.getResultList();
      
        return listaEspecialidades;
    }
    
    public int busquedaId(Tecnico tecnico,Especialidad especialidad) {
    	  SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
          Session session = sessionFactory.openSession();
          Especialidades nuevaEspecialidad=new Especialidades(tecnico,especialidad);
          Integer id=(Integer)session.getIdentifier(nuevaEspecialidad);
    	return  id;
    }
    
    
}
