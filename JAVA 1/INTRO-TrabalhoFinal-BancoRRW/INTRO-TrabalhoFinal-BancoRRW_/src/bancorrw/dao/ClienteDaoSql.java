/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author rafae
 */
public class ClienteDaoSql implements ClienteDao{
    public ClienteDaoSql(){
    }
    private static ClienteDaoSql dao;
    public static ClienteDaoSql getClienteDaoSql(){
        throw new RuntimeException("Não implementado. Implemente aqui");
    }  
    private String selectAll = 
        "SELECT "+ 
            "id_cliente, " +
            "nome, " +
            "cpf, " +
            "data_nascimento, " +
            "cartao_credito " +
        "FROM " +
            "clientes ";
    private String selectById = selectAll + " " + 
            "WHERE "+
                "id_cliente=?";
    private String insertCliente = 
        "INSERT INTO " +
            "clientes " +
            "(nome," +
            "cpf," +
            "data_nascimento, " +
            "cartao_credito) " +
        "VALUES" +
            "(?,?,?,?)";
    private String updateCliente = 
        "UPDATE " +
            "clientes " +
        "SET " + 
            "nome=?, " +
            "cpf=?, " +
            "data_nascimento=?, " +
            "cartao_credito=? " +
        "WHERE id_cliente = ?";
    private String deleteById = 
        "DELETE FROM "+
            "clientes "+
        "WHERE id_cliente=?";
    private String deleteAll = 
        "DELETE FROM "+
            "clientes ";
    private final String ressetAIPessoas = "ALTER TABLE clientes AUTO_INCREMENT =1";
    private final String ressetAIContas = "ALTER TABLE contas AUTO_INCREMENT =1";
    
    @Override
    public void add(Cliente cliente) throws Exception {
        try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost/bancorrw","root","1234");
            PreparedStatement stmt = con.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getCartaoCredito());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getLong(1));  // Define o ID gerado no objeto cliente
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public List<Cliente> getAll() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost/bancorrw","root","1234");
            PreparedStatement stmt = con.prepareStatement(selectAll);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getLong("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("cartao_credito")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    @Override
    public Cliente getById(long id) throws Exception {
        Cliente cliente = null;
        try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost/bancorrw","root","1234");
            PreparedStatement stmt = con.prepareStatement(selectById)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                        rs.getLong("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("cartao_credito")
                    );
                }
            }
        }
        return cliente;
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost/bancorrw","root","1234");
            PreparedStatement stmt = con.prepareStatement(updateCliente)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getCartaoCredito());
            stmt.setLong(5, cliente.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Cliente cliente) throws Exception {
        try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost/bancorrw","root","1234");
            PreparedStatement stmt = con.prepareStatement(deleteById)) {
            stmt.setLong(1, cliente.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                cliente.setId(-1);  // Set the client ID to -1 indicating it is no longer in the DB
            }
       }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost/bancorrw","root","1234");
            Statement stmt = con.createStatement()) {
            stmt.execute(deleteAll);
            stmt.execute(ressetAIPessoas);
            stmt.execute(ressetAIContas);
        }
    }
}
