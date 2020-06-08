/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IndicacaoDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Indicacao;
import model.Usuario;

/**
 *
 * @author Red
 */
public class IndicacaoController extends HttpServlet {
    private IndicacaoDAO indicacaoDAO;

    public IndicacaoController() {
        this.indicacaoDAO = new IndicacaoDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opcao = request.getParameter("acao");
        
        switch(opcao.trim()){               
            case "listar":
                String nomeUsuario = request.getParameter("nome");
                int idUsuario = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("indicacoes", listarIndicacaoUsuario(idUsuario));
              
                Usuario usuario = new Usuario();
                usuario.setNomeCompleto(nomeUsuario);
                usuario.setId(idUsuario);
                
                request.setAttribute("usuario", usuario);
                
                request.getRequestDispatcher("indicacao.jsp").forward(request, response);
                break;
            
            default:
                response.setStatus(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opcao = request.getParameter("acao");
        
        switch(opcao.trim()){               
            case "cadastrar":
                cadastrarIndicacao(request, response);
                break;
            
            default:
                response.setStatus(400);
        }
    }
    
    private void cadastrarIndicacao(HttpServletRequest request, 
                                    HttpServletResponse response) 
            throws ServletException, IOException {
        
        int usuarioId = Integer.parseInt(request.getParameter("id"));
        String nomePessoa = request.getParameter("nome");
        String comentario = request.getParameter("comentario");
        
        int result = indicacaoDAO.save(
                              new Indicacao(nomePessoa, comentario), usuarioId);
        
        if(result != -1) {
            response.sendRedirect("indicacao?acao=listar&id=" 
                                           + usuarioId + "&nome=" + nomePessoa);
        } else {
            response.setStatus(500);
        }
    }
    
    private List<Indicacao> listarIndicacaoUsuario(int idUsuario) {
        ResultSet rs = indicacaoDAO.findByUsuarioId(idUsuario);
        
        try {
            List<Indicacao> indicacoes = new ArrayList<>();
            
            while(rs.next()) {
                Indicacao indicacao = new Indicacao();
                
                indicacao.setId(rs.getInt("id"));
                indicacao.setNomePessoa(rs.getString("nome_pessoa"));
                indicacao.setComentario(rs.getString("comentario"));
                
                indicacoes.add(indicacao);
            }
            
            return indicacoes;
        } catch (SQLException ex) {
            Logger.getLogger(IndicacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
