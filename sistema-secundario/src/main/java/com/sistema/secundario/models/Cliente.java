package com.sistema.secundario.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "cliente")
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_venta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVenta;

	private Double monto;
	
	private int sucursal;

	@Column(name = "id_cliente")
	private String idCliente;
	
	private String canal;

	public Cliente() {

	}

	public Cliente(Double monto, int sucursal, String idCliente, String canal) {
		this.monto = monto;
		this.sucursal = sucursal;
		this.idCliente = idCliente;
		this.canal = canal;
	}

	public Cliente(Long id, Date fechaVenta, Double monto, int sucursal, String idCliente, String canal) {
		super();
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.monto = monto;
		this.sucursal = sucursal;
		this.idCliente = idCliente;
		this.canal = canal;
	}

	@PrePersist()
	public void prePersist() {
		this.fechaVenta = new Date();
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}
}
