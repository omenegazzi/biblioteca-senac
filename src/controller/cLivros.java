/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.mLivros;

/**
 *
 * @author guest01
 */
public class cLivros {

    public void cadastrar(mLivros modelL) {
        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO livros "
                    + "(fk_editoras_id_editora, fk_autores_id_autor, titulo, descricao, ano)"
                    + "VALUES (?,?,?,?,?)");
            stmt.setInt(1, modelL.getEditoras().getId_editora());
            stmt.setInt(2, modelL.getAutores().getId_autor());
            stmt.setString(3, modelL.getTitulo());
            stmt.setString(4, modelL.getDescricao());
            stmt.setInt(5, modelL.getAno());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
