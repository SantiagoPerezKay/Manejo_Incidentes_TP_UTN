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
@Table(name="servicios")
public class Servicio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	Integer idServicio;
	
	@Column(name="nombre_servicio")
	String nombreServicio;
	
	
	
	public Servicio(String nombreServicio) {
		
		this.nombreServicio = nombreServicio;
	}
	
}
