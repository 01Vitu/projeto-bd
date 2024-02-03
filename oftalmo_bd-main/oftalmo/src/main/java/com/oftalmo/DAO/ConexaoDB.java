package com.oftalmo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexaoDB {
    
    
    private static final String dbName = "oftalmo_bd";
    private static final String dbURL = "jdbc:postgresql://localhost:5432/";
    private static final String username = "postgres";
    private static final String password = "victor2411";
    

    protected void printSQLException(Exception e) {
        // Aqui você pode adicionar a lógica para lidar com a exceção de maneira geral
        System.err.println("Ocorreu uma exceção: " + e.getMessage());
        e.printStackTrace();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(dbURL.concat(dbName), username, password);
    }

    public static Connection conexaoDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conexaoDB = DriverManager.getConnection(dbURL.concat(dbName), username, password);
    
        if (conexaoDB != null) {
            System.out.println("Conexão com o banco de dados gerada com sucesso! ");
            return conexaoDB;
        } else {
            throw new RuntimeException("Ops! Erro ao conectar com o banco de dados. :(");
        }
    }
    public static PreparedStatement prepararSQL(String sql) throws SQLException, ClassNotFoundException {
        return getConnection().prepareStatement(sql);
    }

 

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("Estado do SQL: " + ((SQLException) e).getSQLState());
                System.err.println("Codigo do ERRO: " + ((SQLException) e).getErrorCode());
                System.err.println("Messagem: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                      System.out.println("Causa: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

