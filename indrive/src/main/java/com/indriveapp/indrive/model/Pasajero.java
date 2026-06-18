package com.indriveapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pasajero")
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPasajero;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Pasajero() {
    }

    // Getters y Setters
    

    /**
     * @return Integer return the idPasajero
     */
    public Integer getIdPasajero() {
        return idPasajero;
    }

    /**
     * @param idPasajero the idPasajero to set
     */
    public void setIdPasajero(Integer idPasajero) {
        this.idPasajero = idPasajero;
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

}