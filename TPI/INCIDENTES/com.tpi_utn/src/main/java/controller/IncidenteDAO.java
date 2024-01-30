package controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import model.ConexionHibernate;
import model.Incidente;
import model.Tecnico;

public class IncidenteDAO implements CrudInterface<Incidente> {

	@Override
	public void insertar(Incidente incidente) {
		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(incidente);
		session.getTransaction().commit();
	}

	@Override
	public void actualizar(Incidente incidente) {
		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(incidente);
		session.getTransaction().commit();
	}

	@Override
	public void eliminar(Incidente incidente) {
		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.remove(incidente);
		session.getTransaction().commit();
		System.out.println("Incidente eliminado");
	}

	@Override
	public Incidente leer(Integer id) {
		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		Session session = sessionFactory.openSession();
		Incidente incidente = session.find(Incidente.class, id);
		return incidente;
	}

	public List<Incidente> listarTodos() {
		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query<Incidente> query = session.createQuery("FROM Incidente", Incidente.class);
		List<Incidente> listaIncidentes = query.getResultList();
		return listaIncidentes;
	}

	public Map<Tecnico, List<Incidente>> agruparIncidentesPorTecnico() {
		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Query<Incidente> query = session.createQuery("FROM Incidente", Incidente.class);
		List<Incidente> listaIncidentes = query.getResultList();

		// Agrupo los incidentes por técnico
		return listaIncidentes.stream().collect(Collectors.groupingBy(Incidente::getTecnico));

	}
	public Tecnico obtenerTecnicoConMasIncidentes() {
		SessionFactory sessionFactory = ConexionHibernate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Query<Incidente> query = session.createQuery("FROM Incidente", Incidente.class);
		List<Incidente> listaIncidentes = query.getResultList();

		// Agrupo los incidentes por técnico y cuento la cantidad de incidentes por técnico
		Map<Tecnico, Long> incidentesPorTecnico = listaIncidentes.stream()
				.collect(Collectors.groupingBy(Incidente::getTecnico, Collectors.counting()));

		// Encuentro el técnico con mayor cantidad de incidentes
		Optional<Map.Entry<Tecnico, Long>> tecnicoConMasIncidentes = incidentesPorTecnico.entrySet().stream()
				.max(Map.Entry.comparingByValue());

		// Devuelvo el técnico con mayor cantidad de incidentes
		return tecnicoConMasIncidentes.map(Map.Entry::getKey).orElse(null);
	}
}
