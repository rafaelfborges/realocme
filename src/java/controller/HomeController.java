/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Red
 */
public class HomeController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opcao = request.getParameter("opcao");
        
        switch(opcao.trim()){
            case "cadastrar":
                request.getRequestDispatcher("pages/cadastro.jsp").forward(request, response);
                break;
            
            case "listar":
                //request.getServletContext().getRequestDispatcher("/ListarUsuarioController").forward(request, response);
                break;
                
            case "editar":
                //request.getServletContext().getRequestDispatcher("/EditarUsuarioController?funcao=opcoes").forward(request, response);
                break;
                
            case "deletar":
                //request.getServletContext().getRequestDispatcher("/DeletarUsuarioController?funcao=opcoes").forward(request, response);
                break;
        }
    }

}
