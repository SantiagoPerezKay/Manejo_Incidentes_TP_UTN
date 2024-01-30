package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="tecnicos_tiene_especialidades")
public class Especialidades {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_tecnico_especialidad")
	Integer idEspecialidad;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico")
	Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name="id_especialidad")
	Especialidad especialidad;

	public Especialidades(Tecnico tecnico, Especialidad especialidad) {
		this.tecnico = tecnico;
		this.especialidad = especialidad;
	}

	@Override
	public String toString() { // modifique el @toString para evitar bucle infinito, ya que find tecnico imprime su toString donde un atributo
		//es especialidades y luego asi mismo especialades estaba imprimiendo Tecnico. 
		return "Especialidades [idEspecialidad=" + idEspecialidad + ", tecnico=" + tecnico.getNombreTecnico() + ", especialidad="
				+ especialidad + "]";
	}
	
	
	
}
