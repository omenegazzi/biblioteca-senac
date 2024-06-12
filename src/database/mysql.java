/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author guest01
 */
public class mysql {
    public static Connection conexao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection("jdbc:mysql://localhost/db_biblioteca_senac?" +
                                   "user=root&password=root");
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao conectar no banco de dados");
        }
    }
    
}
