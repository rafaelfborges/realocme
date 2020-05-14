/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;

/**
 *
 * @author Red
 */
public class UsuarioDAO {
    private Conexao conn;
    private String query;
    private PreparedStatement stmt;
    
    public UsuarioDAO() {
        conn = Conexao.getInstance();
    }
    
    
}
