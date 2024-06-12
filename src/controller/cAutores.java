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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.mAutores;

/**
 *
 * @author guest01
 */
public class cAutores {

    public void cadastrar(mAutores modelA) {
        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO autores (nome,endereco,numero,bairro,cidade,cpf) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, modelA.getNome());
            stmt.setString(2, modelA.getEndereco());
            stmt.setString(3, modelA.getNumero());
            stmt.setString(4, modelA.getBairro());
            stmt.setString(5, modelA.getCidade());
            stmt.setString(6, modelA.getCpf());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor(a) cadastrados com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<mAutores> listar() {

        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<mAutores> lista = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();

            while (rs.next()) {
                mAutores modelE = new mAutores();
                modelE.setId_autor(rs.getInt("id_autor"));
                modelE.setNome(rs.getString("nome"));
                modelE.setEndereco(rs.getString("endereco"));
                modelE.setBairro(rs.getString("bairro"));
                modelE.setNumero(rs.getString("numero"));
                modelE.setCidade(rs.getString("cidade"));
                modelE.setCpf(rs.getString("cpf"));

                lista.add(modelE);

            }

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }

    public List<mAutores> pesquisar(String texto, int filtro) {

        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<mAutores> lista = new ArrayList<>();

        try {

            switch (filtro) {
                case 0:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE id_autor like ?");
                    break;
                case 1:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE nome like ?");
                    break;
                case 2:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE endereco like ?");
                    break;
                case 3:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE numero like ?");
                    break;
                case 4:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE bairro like ?");
                    break;
                case 5:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE cidade like ?");
                    break;
                case 6:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE cpf like ?");
                    break;
                default:
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE nome like ?");
                    break;
            }
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                mAutores modelE = new mAutores();
                modelE.setId_autor(rs.getInt("id_autor"));
                modelE.setNome(rs.getString("nome"));
                modelE.setEndereco(rs.getString("endereco"));
                modelE.setBairro(rs.getString("bairro"));
                modelE.setNumero(rs.getString("numero"));
                modelE.setCidade(rs.getString("cidade"));
                modelE.setCpf(rs.getString("cpf"));

                lista.add(modelE);

            }

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }

    public void alterar(mAutores modelE) {
        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE autores SET nome = ?, endereco = ?, bairro = ?, numero = ?, cidade = ?, cpf = ? WHERE id_autor = ? ");
            stmt.setString(1, modelE.getNome());
            stmt.setString(2, modelE.getEndereco());
            stmt.setString(3, modelE.getBairro());
            stmt.setString(4, modelE.getNumero());
            stmt.setString(5, modelE.getCidade());
            stmt.setString(6, modelE.getCpf());
            stmt.setInt(7, modelE.getId_autor());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor(a) alterado com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null,
                    ex);
        }

    }

    public void excluir(mAutores modelE) {
        Connection conn = mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM autores WHERE id_autor  = ?");
            stmt.setInt(1, modelE.getId_autor());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor(a) excluída com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
