package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "especialidades")
public class Especialidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_especialidad")
	Integer idEspecialidad;

	@Column(name = "nombre_especialidad")
	String nombreEspecialidad;

	@Column(name = "tiempo_resolucion")
	Integer tiempoResolucion;

	public Especialidad(String nombreEspecialidad, Integer tiempoResolucion) {

		this.nombreEspecialidad = nombreEspecialidad;
		this.tiempoResolucion = tiempoResolucion;
	}

}
