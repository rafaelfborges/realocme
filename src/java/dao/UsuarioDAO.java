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
import java.util.ArrayList;
import java.util.List;
import model.Contato;
import model.Usuario;

/**
 *
 * @author Red
 */
public class UsuarioDAO {
    private Conexao conn;
    private String query;
    private PreparedStatement stmt;
    ResultSet resultado;
    
    public UsuarioDAO() {
        conn = Conexao.getInstance();
    }
    
    public int inserirUsuario(Usuario usuario){               
        try {           
            //BEGIN TRANSACTION
            conn.getConnection().setAutoCommit(false);
            
            //Inserir Usuário na tabela 'usuario'
            query = "INSERT INTO usuario(nome_completo, email, url_foto, "
                  + "profissao, resumo, cargo_pretendido) VALUES (?,?,?,?,?,?)";
            
            stmt = conn.getConnection().prepareStatement(query, 
                                               Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getUrlFoto());
            stmt.setString(4, usuario.getProfissao());
            stmt.setString(5, usuario.getResumo());
            stmt.setString(6, usuario.getCargoPretendido());
            int result = stmt.executeUpdate();
            
            resultado = stmt.getGeneratedKeys();
            resultado.first();
            int usuarioId = resultado.getInt(1);
            
            if(result == 1){
                List<Integer> idsContato = new ArrayList<>();
                
                //Inserir Contatos na tabela 'contato'
                query = "INSERT INTO contato(tipo, url) VALUES (?,?)";
                stmt = conn
                        .getConnection()
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                                
                for(Contato contato : usuario.getContatos()){
                    stmt.setString(1, contato.getTipo());
                    stmt.setString(2, contato.getUrl());
                    stmt.executeUpdate();
                    
                    resultado = stmt.getGeneratedKeys();
                    resultado.first();
                    idsContato.add(resultado.getInt(1));
                }
                
                //Inserir relacionamentos na tabela usuario_contato
                query = "INSERT INTO usuario_contato (id_usuario, id_contato) "
                        + "VALUES (?,?)";
                stmt = conn.getConnection().prepareStatement(query);
                
                for(int i = 0; i < idsContato.size(); i++){
                   stmt.setInt(1, usuarioId);
                   stmt.setInt(2, idsContato.get(i));
                   stmt.executeUpdate();
                }
                
                //COMMIT TRANSACTION
                conn.getConnection().commit();
                return 1;
            } else {
                //ROLLBACK TRANSACTION WHEN FAILS
                conn.getConnection().rollback();
                return -1;
            }
        } catch(SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return e.getErrorCode();
        }
    }
    
    public int inserirContato(Contato contato) {
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
}
