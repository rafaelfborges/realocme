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
import model.Indicacao;

/**
 *
 * @author Red
 */
public class IndicacaoDAO {
    private Conexao conn;
    private String query;
    private PreparedStatement stmt;
    ResultSet resultSet;

    public IndicacaoDAO() {
        conn = Conexao.getInstance();
    }
    
    public int save(Indicacao indicacao, int idUsuario) {
        try {
            //BEGIN TRANSACTION
            conn.getConnection().setAutoCommit(false);
            
            //Inserir indicacao na tabela 'indicacao
            query = "INSERT INTO indicacao(nome_pessoa, comentario) VALUES (?,?)";
            stmt = conn
                    .getConnection()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, indicacao.getNomePessoa());
            stmt.setString(2, indicacao.getComentario());
            int result = stmt.executeUpdate();
             
            resultSet = stmt.getGeneratedKeys();
            resultSet.first();
            int idIndicacao = resultSet.getInt(1);
            
            if(result == 1) {
                //Inserir relacionamentos na tabela indicacao_usuario
                query = "INSERT INTO indicacao_usuario (id_indicacao, id_usuario) "
                        + "VALUES (?,?)";
                stmt = conn.getConnection().prepareStatement(query);
                stmt.setInt(1, idIndicacao);
                stmt.setInt(2, idUsuario);
                stmt.executeUpdate();
                
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
    
    public ResultSet findByUsuarioId(int usuarioId){
        try {
            query = "SELECT indicacao.id, indicacao.nome_pessoa, indicacao.comentario "
                  + "FROM indicacao INNER JOIN indicacao_usuario "
                  + "ON indicacao_usuario.id_indicacao = indicacao.id "
                  + "INNER JOIN usuario "
                  + "ON indicacao_usuario.id_usuario = usuario.id "
                  + "WHERE usuario.id = ?";
            
            stmt = conn.getConnection().prepareStatement(query);            
            stmt.setInt(1, usuarioId);
            
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}
