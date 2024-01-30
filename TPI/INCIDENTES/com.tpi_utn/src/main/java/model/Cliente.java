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
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	Integer idCliente;

	@Column(name = "razon_social")
	String razonSocial;

	@Column(name = "cuit")
	Integer cuit;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	List<Servicios> listaDeServicios;

	public Cliente(String razonSocial, Integer cuit) {
		this.razonSocial = razonSocial;
		this.cuit = cuit;
	}

	public Cliente(String razonSocial, Integer cuit, List<Servicios> listaDeServicios) {
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.listaDeServicios = listaDeServicios;
	}
}