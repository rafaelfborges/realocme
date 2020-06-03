/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Contato;
import model.Usuario;

/**
 *
 * @author Red
 */
public class UsuarioController extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opcao = request.getParameter("acao");
        
        switch(opcao.trim()){
            case "cadastrar":
                if(cadastrarUsuario(request) != 1062) {
                    response.setStatus(201);
                } else {
                    response.setStatus(409);
                    response.setContentType("text/plain");
                    response.getWriter().write("Usuário já existe!");
                }
                break;
                        
        }
   }
    
    private int cadastrarUsuario(HttpServletRequest request){                      
        String urlFoto = request.getParameter("urlFoto");
        String nomeCompleto = request.getParameter("nomeCompleto");
        String email = request.getParameter("email");
        String profissao = request.getParameter("profissao");
        String resumo = request.getParameter("resumo");
        String cargo = request.getParameter("cargoPretendido");    
        String[] tipoContato = request.getParameterValues("tipoContato");        
        String[] urlContato = request.getParameterValues("urlContato");
        
        List<Contato> contatos = new ArrayList<>();
        for(int i = 0; i < tipoContato.length; i++) {
            contatos.add(new Contato(tipoContato[i], urlContato[i]));
        }
        
        return usuarioDAO.inserirUsuario(new Usuario(urlFoto, nomeCompleto, 
                                    email, profissao, resumo, cargo, contatos));
    }
}
