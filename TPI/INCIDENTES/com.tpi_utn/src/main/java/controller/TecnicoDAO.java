package controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.ConexionHibernate;
import model.Tecnico;

public class TecnicoDAO implements CrudInterface<Tecnico>{

	
	@Override
	public void insertar(Tecnico objeto) {
	
		
		
		  	SessionFactory sessionFactory = ConexionHibernate.getSessionFactory(); //obteniendo session para realizar transacciones
	        Session session = sessionFactory.openSession();
	        session.beginTransaction(); //comienzo transaccion para almacenar 1 o varias transacciones.
	        session.persist(objeto);	//agregar. 
	        session.getTransaction().commit(); // obtengo transaccion y realizo el ascentamiento en la bd.
	       
	        
	}

	@Override
	public void actualizar(Tecnico objeto) {

	  	SessionFactory sessionFactory = ConexionHibernate.getSessionFactory(); 
        Session session = sessionFactory.openSession();
        session.beginTransaction();      
        session.merge(objeto);     //en caso de no tener identificador el objeto que paso por param,crea un registro nuevo.
        session.getTransaction().commit(); 
       
 
		
	}

	@Override
	public void eliminar(Tecnico objeto) {
		
	 	SessionFactory sessionFactory = ConexionHibernate.getSessionFactory(); 
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(objeto);
        session.getTransaction().commit(); 
      
      
		
	}

	@Override
	public Tecnico leer(Integer id) {// Obtener el Tecnico por su ID
		
	    SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();

	    Tecnico tecnico = session.find(Tecnico.class, id);

	    // Forzar la carga de las especialidades si el técnico no es nulo
	    if (tecnico != null) {
	    	  Hibernate.initialize(tecnico.getEspecialidades()); // Forzar carga de especialidades
	    }

	  
	   

	    return tecnico;
	}
	
	
	public Tecnico leer(String nombre) {
	    try (SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
	         Session session = sessionFactory.openSession()) {

	        Query<Tecnico> query = session.createQuery(
	                "SELECT t FROM Tecnico t WHERE t.nombreTecnico = :nombre", Tecnico.class
	        );
	        query.setParameter("nombre", nombre);
	        Tecnico tecnico = query.uniqueResult();

	        if (tecnico != null) {
	            Hibernate.initialize(tecnico.getEspecialidades());
	        }

	        return tecnico;
	    }catch(Exception e) {
	    	System.out.println("error al leer");
	    	return null;
	    }
	    
	}
	@Override
	public List<Tecnico> listarTodos() {
		   SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		    Session session = sessionFactory.openSession();
		    Query<Tecnico> query = session.createQuery("FROM Tecnico", Tecnico.class);
		    List<Tecnico> listaTecnicos = query.getResultList();
		    for (Tecnico tecnico : listaTecnicos) {
		        Hibernate.initialize(tecnico.getEspecialidades()); // Forzar carga de especialidades
		    }
		 
		   
		    return listaTecnicos;
		
	}
	
	
	
	

}
