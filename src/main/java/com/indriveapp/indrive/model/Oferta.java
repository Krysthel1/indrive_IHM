package com.indriveapp.indrive.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOferta;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "id_conductor")
    private Conductor conductor;

    private Double precio;
    private Integer tiempoLlegada;

    public Oferta() {
    }

    // Getters y Setters
    

    /**
     * @return Integer return the idOferta
     */
    public Integer getIdOferta() {
        return idOferta;
    }

    /**
     * @param idOferta the idOferta to set
     */
    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    /**
     * @return Viaje return the viaje
     */
    public Viaje getViaje() {
        return viaje;
    }

    /**
     * @param viaje the viaje to set
     */
    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
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
     * @return Double return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return Integer return the tiempoLlegada
     */
    public Integer getTiempoLlegada() {
        return tiempoLlegada;
    }

    /**
     * @param tiempoLlegada the tiempoLlegada to set
     */
    public void setTiempoLlegada(Integer tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

}