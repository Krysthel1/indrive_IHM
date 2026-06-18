package com.indriveapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idViaje;

    @ManyToOne
    @JoinColumn(name = "id_pasajero")
    private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "id_conductor")
    private Conductor conductor;

    private String origen;
    private String destino;
    private Double precioPropuesto;
    private Double distancia;
    private Integer duracion;
    private String estado;
    private LocalDateTime fecha;

    public Viaje() {
    }

    // Getters y Setters
    

    /**
     * @return Integer return the idViaje
     */
    public Integer getIdViaje() {
        return idViaje;
    }

    /**
     * @param idViaje the idViaje to set
     */
    public void setIdViaje(Integer idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * @return Pasajero return the pasajero
     */
    public Pasajero getPasajero() {
        return pasajero;
    }

    /**
     * @param pasajero the pasajero to set
     */
    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    /**
     * @return Conductor return the conductor
     */
    public Conductor getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    /**
     * @return String return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return String return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return Double return the precioPropuesto
     */
    public Double getPrecioPropuesto() {
        return precioPropuesto;
    }

    /**
     * @param precioPropuesto the precioPropuesto to set
     */
    public void setPrecioPropuesto(Double precioPropuesto) {
        this.precioPropuesto = precioPropuesto;
    }

    /**
     * @return Double return the distancia
     */
    public Double getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    /**
     * @return Integer return the duracion
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * @return String return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return LocalDateTime return the fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}