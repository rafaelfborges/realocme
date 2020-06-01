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
    private String resumo;
    private String interesse;
    private Empresa ultimoEmprego;
    private List<Contato> contatos;
    private List<Indicacao> indicacoes;

    public Usuario() {
    }

    public Usuario(String urlFoto, String nomeCompleto, String titulo, 
                    String resumo, String interesse, Empresa ultimoEmprego, 
                    List<Contato> contatos, List<Indicacao> indicacoes) {
        this.urlFoto = urlFoto;
        this.nomeCompleto = nomeCompleto;
        this.titulo = titulo;
        this.resumo = resumo;
        this.interesse = interesse;
        this.ultimoEmprego = ultimoEmprego;
        this.contatos = contatos;
        this.indicacoes = indicacoes;
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

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Indicacao> getIndicacoes() {
        return indicacoes;
    }

    public void setIndicacoes(List<Indicacao> indicacoes) {
        this.indicacoes = indicacoes;
    }
}
