/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Contato;

/**
 *
 * @author Red
 */
public class ContatoDAO {
    private Conexao conn;
    private String query;
    private PreparedStatement stmt;
    ResultSet resultSet;

    public ContatoDAO() {
        conn = Conexao.getInstance();
    }
    
    public int save(Contato contato) {
        try {
            query = "INSERT INTO contato(tipo, url) VALUES (?,?)";
            stmt = conn
                    .getConnection()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, contato.getTipo());
            stmt.setString(2, contato.getUrl());
            stmt.executeUpdate();
             
            ResultSet rs = stmt.getGeneratedKeys();
            rs.first();
            
            return rs.getInt(1);
        } catch(SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return e.getErrorCode();
        }
    }
    
    public ResultSet findByUsuarioId(int usuarioId){
        try {
            query = "SELECT contato.id, contato.tipo, contato.url FROM contato "
                  + "INNER JOIN usuario_contato "
                  + "ON usuario_contato.id_contato = contato.id "
                  + "INNER JOIN usuario "
                  + "ON usuario.id = usuario_contato.id_usuario "
                  + "WHERE usuario.id = ? ORDER BY "
                  + "FIELD(tipo,'linkedin','site','facebook','outro')";
            
            stmt = conn.getConnection().prepareStatement(query);
            stmt.setInt(1, usuarioId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}
