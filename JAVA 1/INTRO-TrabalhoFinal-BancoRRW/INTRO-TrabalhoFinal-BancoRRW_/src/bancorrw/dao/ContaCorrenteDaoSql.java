/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import bancorrw.conta.ContaCorrente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rafae
 */
public class ContaCorrenteDaoSql implements ContaCorrenteDao{
    public ContaCorrenteDaoSql(){
    }
    private static ContaCorrenteDaoSql dao;
    public static ContaCorrenteDaoSql getContaCorrenteDaoSql(){
        throw new RuntimeException("Não implementado. Implemente aqui");
    } 
    private String insertContaCorrente = 
        "INSERT INTO " +
            "contas_corrente " +
            "(id_conta," +
            "limite," +
            "taxa_juros_limite) " +
        "VALUES" +
            "(?,?,?)";
    private String insertConta = 
        "INSERT INTO " +
            "contas " +
            "(id_cliente," +
            "saldo) " +
        "VALUES" +
            "(?,?)";
    
    private String updateClienteIdContaCorrente = 
        "UPDATE " +
            "clientes " +
        "SET " + 
            "id_conta_corrente=? " +
        "WHERE id_cliente = ?";
    private String updateContaCorrente = 
        "UPDATE " +
            "contas_corrente " +
        "SET " + 
            "limite=? ," +
            "taxa_juros_limite=? " +
        "WHERE id_conta = ?";    
    private String updateConta = 
        "UPDATE " +
            "contas " +
        "SET " + 
            "saldo=? " +
        "WHERE id_conta = ?";    
    private String selectByCliente = 
                        "SELECT "+
                            "contas_corrente.id_conta, "+
                            "saldo, "+
                            "limite, "+
                            "taxa_juros_limite, "+
                            "clientes.id_cliente,"+
                            "nome, "+
                            "cpf, "+
                            "data_nascimento, "+
                            "cartao_credito "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta "+
                        "INNER JOIN "+
                            "clientes "+
                        "ON "+
                            "contas.id_conta=clientes.id_conta_corrente "+
                        "WHERE "+
                            "contas.id_cliente=?";
        private String selectById = 
                        "SELECT "+
                            "contas_corrente.id_conta, "+
                            "saldo, "+
                            "limite, "+
                            "taxa_juros_limite, "+
                            "clientes.id_cliente,"+
                            "nome, "+
                            "cpf, "+
                            "data_nascimento, "+
                            "cartao_credito "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta "+
                        "INNER JOIN "+
                            "clientes "+
                        "ON "+
                            "contas.id_conta=clientes.id_conta_corrente "+
                        "WHERE "+
                            "contas.id_conta=?";
    private String selectAll = 
                        "SELECT "+
                            "contas_corrente.id_conta, "+
                            "saldo, "+
                            "limite, "+
                            "taxa_juros_limite, "+
                            "clientes.id_cliente,"+
                            "nome, "+
                            "cpf, "+
                            "data_nascimento, "+
                            "cartao_credito "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta "+
                        "INNER JOIN "+
                            "clientes "+
                        "ON "+
                            "contas.id_conta=clientes.id_conta_corrente ";   
    private String deleteById = 
                        "DELETE FROM "+
                            "contas " +
                        "WHERE " +
                            "id_conta=?";
    private String deleteAll = 
                        "DELETE " +
                            "contas,contas_corrente "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta ";            
    private final String ressetAIContas = "ALTER TABLE contas AUTO_INCREMENT =1";
    
    @Override
    public void add(ContaCorrente contaCorrente) throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234")) {
            con.setAutoCommit(false);

            try (PreparedStatement stmtConta = con.prepareStatement(insertConta, Statement.RETURN_GENERATED_KEYS)) {
                stmtConta.setLong(1, contaCorrente.getCliente().getId());
                stmtConta.setDouble(2, contaCorrente.getSaldo());
                int affectedRowsConta = stmtConta.executeUpdate();

                if (affectedRowsConta == 0) {
                    throw new SQLException("Falha ao criar a conta, nenhuma linha foi afetada.");
                }

                try (ResultSet generatedKeys = stmtConta.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        contaCorrente.setId(generatedKeys.getLong(1));  // Atribui o ID gerado
                    } else {
                        throw new SQLException("Falha ao criar conta, ID não obtido.");
                    }
                }
            }

            try (PreparedStatement stmtContaCorrente = con.prepareStatement(insertContaCorrente)) {
                stmtContaCorrente.setLong(1, contaCorrente.getId());
                stmtContaCorrente.setDouble(2, contaCorrente.getLimite());
                stmtContaCorrente.setDouble(3, contaCorrente.getTaxaJurosLimite());
                stmtContaCorrente.executeUpdate();
            }

            try (PreparedStatement stmtUpdateCliente = con.prepareStatement(updateClienteIdContaCorrente)) {
                stmtUpdateCliente.setLong(1, contaCorrente.getId());
                stmtUpdateCliente.setLong(2, contaCorrente.getCliente().getId());
                stmtUpdateCliente.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ContaCorrente> getAll() throws Exception {
            List<ContaCorrente> contasCorrentes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/bancorrw", "root", "1234");
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
                ContaCorrente cc = new ContaCorrente(
                    rs.getDouble("limite"),
                    rs.getDouble("taxa_juros_limite"),
                    rs.getLong("id_conta"),
                    cliente,
                    rs.getDouble("saldo")
                );
                contasCorrentes.add(cc);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
        return contasCorrentes;
    }

    @Override
    public ContaCorrente getById(long id) throws Exception {
        ContaCorrente contaCorrente = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
             PreparedStatement stmt = con.prepareStatement(selectByCliente)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getLong("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("cartao_credito")
                );
                contaCorrente = new ContaCorrente(
                    rs.getDouble("limite"),
                    rs.getDouble("taxa_juros_limite"),
                    rs.getLong("id_conta"),
                    cliente,
                    rs.getDouble("saldo")
                );
            }
        }
        return contaCorrente;
    }

    @Override
    public void update(ContaCorrente contaCorrente) throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
        PreparedStatement stmtConta = con.prepareStatement(updateConta)) {
        stmtConta.setDouble(1, contaCorrente.getSaldo());
        stmtConta.setLong(2, contaCorrente.getId());
        stmtConta.executeUpdate();
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
             PreparedStatement stmtContaCorrente = con.prepareStatement(updateContaCorrente)) {
            stmtContaCorrente.setDouble(1, contaCorrente.getLimite());
            stmtContaCorrente.setDouble(2, contaCorrente.getTaxaJurosLimite());
            stmtContaCorrente.setLong(3, contaCorrente.getId());
            stmtContaCorrente.executeUpdate();
        }
    }

    @Override
    public void delete(ContaCorrente contaCorrente) throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
        PreparedStatement stmt = con.prepareStatement(deleteById)) {
        stmt.setLong(1, contaCorrente.getId());
        stmt.executeUpdate();
       }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
        Statement stmt = con.createStatement()) {
        stmt.execute(deleteAll);
        stmt.execute(ressetAIContas);
        }
    }

    @Override
    public ContaCorrente getContaCorrenteByCliente(Cliente cliente) throws Exception{
        ContaCorrente contaCorrente = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
             PreparedStatement stmt = con.prepareStatement(selectByCliente)) {
            stmt.setLong(1, cliente.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contaCorrente = new ContaCorrente(
                    rs.getDouble("limite"),
                    rs.getDouble("taxa_juros_limite"),
                    rs.getLong("id_conta"),
                    cliente,
                    rs.getDouble("saldo")
                );
                cliente.setContaCorrenteInterno(contaCorrente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
        return contaCorrente;
    }
    
}
