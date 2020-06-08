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
    ResultSet resultSet;
    
    public UsuarioDAO() {
        conn = Conexao.getInstance();
    }
    
    public int save(Usuario usuario){               
        try {           
            //BEGIN TRANSACTION
            conn.getConnection().setAutoCommit(false);
            
            //Inserir Usuário na tabela 'usuario'
            query = "INSERT INTO usuario(nome_completo, email, senha, "
                    + "url_foto, profissao, resumo, cargo_pretendido) "
                    + "VALUES (?,?,?,?,?,?,?)";
            
            stmt = conn.getConnection().prepareStatement(query, 
                                               Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getUrlFoto());
            stmt.setString(5, usuario.getProfissao());
            stmt.setString(6, usuario.getResumo());
            stmt.setString(7, usuario.getCargoPretendido());
            int result = stmt.executeUpdate();
            
            resultSet = stmt.getGeneratedKeys();
            resultSet.first();
            int usuarioId = resultSet.getInt(1);
            
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
                    
                    resultSet = stmt.getGeneratedKeys();
                    resultSet.first();
                    idsContato.add(resultSet.getInt(1));
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
    
    public ResultSet findAll(){
        try {
            query = "SELECT * FROM usuario WHERE perfil_ativo = 1";
            stmt = conn.getConnection().prepareStatement(query);
                       
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
    
    public ResultSet findUsuarioById(int usuarioId) {
        try {
            query = "SELECT * FROM usuario WHERE id = ?";
            stmt = conn.getConnection().prepareStatement(query);
            stmt.setInt(1, usuarioId);
                       
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
    
    public ResultSet authUsuario(String email, String password) {
        try {
            query = "SELECT id, nome_completo FROM usuario "
                    + "WHERE email = ? AND senha = ?";
            stmt = conn.getConnection().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
    
    public int update(Usuario usuario) {
        try {                      
            query = "UPDATE usuario SET "
                    + "nome_completo = ?, "
                    + "email = ?, "
                    + "senha = ?, "
                    + "url_foto = ?, "
                    + "profissao = ?, "
                    + "resumo = ?, "
                    + "cargo_pretendido = ?, "
                    + "perfil_ativo = ? "
                    + "WHERE usuario.id = ?";
            
            stmt = conn.getConnection().prepareStatement(query);
            
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getUrlFoto());
            stmt.setString(5, usuario.getProfissao());
            stmt.setString(6, usuario.getResumo());
            stmt.setString(7, usuario.getCargoPretendido());
            stmt.setInt(8, usuario.getPerfilAtivo());
            stmt.setInt(9, usuario.getId());
            
            return stmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return e.getErrorCode();
        }
    }
}
