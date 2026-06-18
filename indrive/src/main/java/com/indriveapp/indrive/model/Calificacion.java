package com.indriveapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacion;

    @OneToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;

    private Integer puntuacion;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    public Calificacion() {
    }

    // Getters y Setters
    

    /**
     * @return Integer return the idCalificacion
     */
    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    /**
     * @param idCalificacion the idCalificacion to set
     */
    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
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
     * @return Integer return the puntuacion
     */
    public Integer getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * @return String return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}