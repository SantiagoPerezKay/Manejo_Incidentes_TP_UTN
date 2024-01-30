package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//JPA//Hibernate
@Entity
@Table(name = "incidentes")
public class Incidente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_incidente")
	Integer idIncidente;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	Cliente cliente;

	@Column(name = "descripcion_problema")
	String descripcionProblema;
	
	@Column(name = "estado")
	String estado;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico")
	Tecnico tecnico;
	
	@Column(name = "fecha_inicio")
	LocalDateTime fechaInicio;
	
	@Column(name = "fecha_fin")
	LocalDateTime fechaFin;


	public Incidente(Cliente cliente, String descripcionProblema, String estado, Tecnico tecnico, LocalDateTime fechahoraActual,
			LocalDateTime fechahoraActualFin) {
		this.cliente = cliente;
		this.descripcionProblema = descripcionProblema;
		this.estado = estado;
		this.tecnico = tecnico;
		this.fechaInicio = fechahoraActual;
		this.fechaFin = fechahoraActualFin;
	}



	@Override
	public String toString() {
		return "Incidente [idIncidente=" + idIncidente + ", cliente=" + cliente.getRazonSocial() + ", descripcionProblema="
				+ descripcionProblema + ", estado=" + estado + ", tecnico=" + tecnico.getNombreTecnico() + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + "]";
	}




	
	
	

}
