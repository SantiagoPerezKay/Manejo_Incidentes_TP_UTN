package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.Cliente;
import model.Servicio;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "clientes_tiene_servicios")
public class Servicios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio_cliente")
	Integer idServicioCliente;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_servicio")
	Servicio servicio;

	public Servicios(Cliente cliente, Servicio servicio) {
		this.cliente = cliente;
		this.servicio = servicio;
	}



}