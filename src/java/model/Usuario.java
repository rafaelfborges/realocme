/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;


public class Usuario {
    private int id;
    private String urlFoto;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String profissao;
    private String resumo;
    private String cargoPretendido;
    private int perfilAtivo;
    private List<Contato> contatos;
    private List<Indicacao> indicacoes;

    public Usuario() {
    }
    
    public Usuario(String urlFoto, String nomeCompleto, String email, String senha, String profissao, String resumo, String cargoPretendido, List<Contato> contatos) {
        this.urlFoto = urlFoto;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.profissao = profissao;
        this.resumo = resumo;
        this.cargoPretendido = cargoPretendido;
        this.contatos = contatos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public int getPerfilAtivo() {
        return perfilAtivo;
    }

    public void setPerfilAtivo(int perfilAtivo) {
        this.perfilAtivo = perfilAtivo;
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
