package com.spring.cevento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="evento")

public class Evento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotEmpty(message = "Preencha este campo")
    private long id;

    @NotEmpty(message = "Preencha este campo")
    private String nome;

    @NotEmpty(message = "Preencha este campo")
    private String categoria;

    @Lob
    @NotEmpty(message = "Preencha este campo")
    private String descricao;

    @NotEmpty(message = "Preencha este campo")
    private String local;

    @NotEmpty(message = "Preencha este campo")
    private String endereco;

    private String complemento;

    @NotEmpty(message = "Preencha este campo")
    private  String data;

    @NotEmpty(message = "Preencha este campo")
    private String hora;

    @OneToMany(mappedBy = "evento" ,cascade = CascadeType.ALL)
    private List<Participante> participante;

    @ManyToOne
    @JoinColumn(name = "usuario_login")
    private Usuario usuario;


    private String imagem;
    //private byte[] imagem;

   // public byte[] getImagem() {
     //   return imagem;
    //}

    //public void setImagem(byte[] imagem) {
      //  this.imagem = imagem;
    //}


    public List<Participante> getParticipante() {
        return participante;
    }

    public void setParticipante(List<Participante> participante) {
        this.participante = participante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}
