package model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tecnicos")
public class Tecnico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tecnico")
	Integer idTecnico;

	@Column(name = "nombre_tecnico")
	String nombreTecnico;

	@Column(name = "estado")
	String estado;

	@OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL)
	List<Especialidades> especialidades;

	public Tecnico(String nombreTecnico, String estado) {

		this.nombreTecnico = nombreTecnico;
		this.estado = estado;

	}

}
