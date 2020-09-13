package com.spring.cevento.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="participante")
public class Participante {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String nomeParticipante;

    @NotEmpty
    private String email;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "usuario_login")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }


    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomePartipante) {
        this.nomeParticipante = nomePartipante;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
