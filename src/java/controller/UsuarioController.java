/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContatoDAO;
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
import javax.servlet.http.HttpSession;
import model.Contato;
import model.Usuario;


public class UsuarioController extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    private ContatoDAO contatoDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.contatoDAO = new ContatoDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opcao = request.getParameter("acao");
        
        switch(opcao.trim()){               
            case "logoff":
                request.getSession().invalidate();
                response.sendRedirect("home");
                break;
                
            case "listar":
                perfilUsuario(request, response);
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
                if(cadastrarUsuario(request) != 1062) {
                    response.setStatus(201);
                } else {
                    response.setStatus(409);
                    response.setContentType("text/plain");
                    response.getWriter().write("Usuário já existe!");
                }
                break;
            
            case "atualizar":
                if(atualizarUsuario(request) != 1062) {
                    response.setStatus(201);
                } else {
                    response.setStatus(409);
                    response.setContentType("text/plain");
                    response.getWriter().write("Usuário já existe!");
                }
                break;
                 
            case "login":
                if(logarUsuario(request)){
                    response.setStatus(200);
                } else {
                    response.setStatus(401);
                    response.setContentType("text/plain");
                    response.getWriter().write("Usuário ou senha inválidos!");
                }
                break;
                
            default:
                response.setStatus(400);
        }
    }
    
    private Usuario perfilUsuario(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException {
        int usuarioId = Integer.parseInt(request.getParameter("id"));
        
        try {
            Usuario usuario = new Usuario();
            
            ResultSet rs = usuarioDAO.findUsuarioById(usuarioId);
            while(rs.next()) {                                
                usuario.setId(rs.getInt("id"));
                usuario.setUrlFoto(rs.getString("url_foto"));
                usuario.setNomeCompleto(rs.getString("nome_completo"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setProfissao(rs.getString("profissao"));
                usuario.setResumo(rs.getString("resumo"));
                usuario.setCargoPretendido(rs.getString("cargo_pretendido"));
                usuario.setPerfilAtivo(rs.getInt("perfil_ativo"));
            }
            
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("perfil.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private boolean logarUsuario(HttpServletRequest request) {
        HttpSession session = request.getSession();
               
        ResultSet rs = usuarioDAO.authUsuario(request.getParameter("email"),
                                                request.getParameter("senha"));
              
        try {
            if(!rs.isBeforeFirst()){
                session.invalidate();
                return false;
            } else {               
                while(rs.next()) {
                    session.setAttribute("id", rs.getInt("id"));
                    session.setAttribute("nome", rs.getString("nome_completo"));
                }
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("UsuarioControler: " + ex.getMessage());
            return false;
        }
    }
        
    private int cadastrarUsuario(HttpServletRequest request){                      
        String urlFoto = request.getParameter("urlFoto");
        String nomeCompleto = request.getParameter("nomeCompleto");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String profissao = request.getParameter("profissao");
        String resumo = request.getParameter("resumo");
        String cargo = request.getParameter("cargoPretendido");    
        String[] tipoContato = request.getParameterValues("tipoContato");        
        String[] urlContato = request.getParameterValues("urlContato");
        
        List<Contato> contatos = new ArrayList<>();
        for(int i = 0; i < tipoContato.length; i++) {
            contatos.add(new Contato(tipoContato[i], urlContato[i]));
        }
        
        return usuarioDAO.save(new Usuario(urlFoto, nomeCompleto, email, senha,
                                           profissao, resumo, cargo, contatos));
    }
    
    private int atualizarUsuario(HttpServletRequest request){                      
        Usuario usuario = new Usuario();
        String perfilAtivo = request.getParameter("perfilAtivo");
        
        usuario.setId(Integer.parseInt(request.getParameter("id")));
        usuario.setUrlFoto(request.getParameter("urlFoto"));
        usuario.setNomeCompleto(request.getParameter("nomeCompleto"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setProfissao(request.getParameter("profissao"));
        usuario.setResumo(request.getParameter("resumo"));
        usuario.setCargoPretendido(request.getParameter("cargoPretendido"));
        if(perfilAtivo != null)
            usuario.setPerfilAtivo(1);
        else
            usuario.setPerfilAtivo(0);
                            
        return usuarioDAO.update(usuario);
    }
}
