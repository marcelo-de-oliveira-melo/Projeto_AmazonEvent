package com.spring.cevento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="TB_EVENTO")

public class Evento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @NotBlank
    @Lob
    private String descricao;

    @NotBlank
    private String local;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private  String data;

    @NotBlank
    private String hora;

    @OneToMany
    private List<Participante> participante;


    private String imagem;
    //private byte[] imagem;

   // public byte[] getImagem() {
     //   return imagem;
    //}

    //public void setImagem(byte[] imagem) {
      //  this.imagem = imagem;
    //}


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
