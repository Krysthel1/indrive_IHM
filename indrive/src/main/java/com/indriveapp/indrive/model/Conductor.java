package com.indriveapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conductor")
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConductor;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String licencia;
    private Boolean disponibilidad;

    public Conductor() {
    }

    // Getters y Setters
    

    /**
     * @return Integer return the idConductor
     */
    public Integer getIdConductor() {
        return idConductor;
    }

    /**
     * @param idConductor the idConductor to set
     */
    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    /**
     * @return Usuario return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return String return the licencia
     */
    public String getLicencia() {
        return licencia;
    }

    /**
     * @param licencia the licencia to set
     */
    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    /**
     * @return Boolean return the disponibilidad
     */
    public Boolean isDisponibilidad() {
        return disponibilidad;
    }

    /**
     * @param disponibilidad the disponibilidad to set
     */
    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}