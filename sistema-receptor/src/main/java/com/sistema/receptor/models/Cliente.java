package com.sistema.receptor.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "cliente")
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;

	@Column(name = "fecha_venta")
	private String fechaVenta;

	private Double monto;
	
	private int sucursal;

	@Column(name = "id_cliente")
	private String idCliente;

	private String canal;
	
	@Transient
	private String tipoDeProceso;

	public Cliente() {

	}

	public Cliente(Double monto, int sucursal, String idCliente, String canal) {
		this.monto = monto;
		this.sucursal = sucursal;
		this.idCliente = idCliente;
		this.canal = canal;
	}

	public Cliente(Long id, String fechaVenta, Double monto, int sucursal, String idCliente, String canal) {
		super();
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.monto = monto;
		this.sucursal = sucursal;
		this.idCliente = idCliente;
		this.canal = canal;
	}
	
	

	public Long getId() {
		return id;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
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

	public String getTipoDeProceso() {
		return tipoDeProceso;
	}

	public void setTipoDeProceso(String tipoDeProceso) {
		this.tipoDeProceso = tipoDeProceso;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", fechaVenta=" + fechaVenta + ", monto=" + monto + ", sucursal=" + sucursal
				+ ", idCliente=" + idCliente + ", canal=" + canal + ", tipoDeProceso=" + tipoDeProceso + "]";
	}
	
	
	
}

