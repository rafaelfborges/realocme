/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Usuario {
    private String urlFoto;
    private String nome;
    private String sobreNome;
    private String titulo;
    private String email;
    private String ultimoEmprego;
    private String resumo;
    private String interesse;
    
    public Usuario() {   
    }

    public Usuario(String urlFoto, String nome, String sobreNome, String titulo, String email, String ultimoEmprego, String resumo, String interesse) {
        this.urlFoto = urlFoto;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.titulo = titulo;
        this.email = email;
        this.ultimoEmprego = ultimoEmprego;
        this.resumo = resumo;
        this.interesse = interesse;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
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

    public String getUltimoEmprego() {
        return ultimoEmprego;
    }

    public void setUltimoEmprego(String ultimoEmprego) {
        this.ultimoEmprego = ultimoEmprego;
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

    
}
