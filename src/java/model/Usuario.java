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
    private String email;
    private String profissao;
    private String resumo;
    private String cargoPretendido;
    private List<Contato> contatos;
    private List<Indicacao> indicacoes;

    public Usuario(
            String urlFoto, String nomeCompleto, String email, String profissao, 
            String resumo, String cargoPretendido, List<Contato> contatos) {
        this.urlFoto = urlFoto;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.profissao = profissao;
        this.resumo = resumo;
        this.cargoPretendido = cargoPretendido;
        this.contatos = contatos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getCargoPretendido() {
        return cargoPretendido;
    }

    public void setCargoPretendido(String cargoPretendido) {
        this.cargoPretendido = cargoPretendido;
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
