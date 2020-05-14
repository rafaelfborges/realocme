package controller;

import dao.UsuarioDAO;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;    
    
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    
}
