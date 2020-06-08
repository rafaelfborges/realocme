/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContatoDAO;
import dao.IndicacaoDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class HomeController extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    private ContatoDAO contatoDAO;
    private IndicacaoDAO indicacaoDAO;
    
    public HomeController() {
        this.usuarioDAO = new UsuarioDAO();
        this.contatoDAO = new ContatoDAO();
        this.indicacaoDAO = new IndicacaoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = new ArrayList<>();
            
            ResultSet rsUsuarios = usuarioDAO.findAll();
            while(rsUsuarios.next()) {                
                //Pega os contatos
                List<Contato> contatos = new ArrayList<>();
                ResultSet rsContatos = contatoDAO.findByUsuarioId(rsUsuarios.getInt("id"));        
                while(rsContatos.next()) {
                    contatos.add(new Contato(rsContatos.getString("tipo"), 
                                             rsContatos.getString("url")));
                }
                
                Usuario usuario = new Usuario();
                usuario.setId(rsUsuarios.getInt("id"));
                usuario.setUrlFoto(rsUsuarios.getString("url_foto"));
                usuario.setNomeCompleto(rsUsuarios.getString("nome_completo"));
                usuario.setEmail(rsUsuarios.getString("email"));
                usuario.setProfissao(rsUsuarios.getString("profissao"));
                usuario.setResumo(rsUsuarios.getString("resumo"));
                usuario.setCargoPretendido(rsUsuarios.getString("cargo_pretendido"));
                usuario.setContatos(contatos);
                
                usuarios.add(usuario);
            }
            
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (IOException | SQLException | ServletException ex) {
            System.out.println("Erro HomeController: " + ex.getMessage());
        }
    }

}
