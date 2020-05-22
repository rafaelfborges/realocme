/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;


public class Usuario {
    private String urlFoto;
    private String nomeCompleto;
    private String titulo;
    private String email;
    private String resumo;
    private String interesse;
    private Empresa ultimoEmprego;
    private List<RedeSocial> redesSociais;
    private List<Comentario> comentarios;

    public Usuario() {
    }

    public Usuario(String urlFoto, String nomeCompleto, String titulo, String email, String resumo, String interesse, Empresa ultimoEmprego, List<RedeSocial> redesSociais, List<Comentario> comentarios) {
        this.urlFoto = urlFoto;
        this.nomeCompleto = nomeCompleto;
        this.titulo = titulo;
        this.email = email;
        this.resumo = resumo;
        this.interesse = interesse;
        this.ultimoEmprego = ultimoEmprego;
        this.redesSociais = redesSociais;
        this.comentarios = comentarios;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getInteresse() {
        return interesse;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    public Empresa getUltimoEmprego() {
        return ultimoEmprego;
    }

    public void setUltimoEmprego(Empresa ultimoEmprego) {
        this.ultimoEmprego = ultimoEmprego;
    }

    public List<RedeSocial> getRedesSociais() {
        return redesSociais;
    }

    public void setRedesSociais(List<RedeSocial> redesSociais) {
        this.redesSociais = redesSociais;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
