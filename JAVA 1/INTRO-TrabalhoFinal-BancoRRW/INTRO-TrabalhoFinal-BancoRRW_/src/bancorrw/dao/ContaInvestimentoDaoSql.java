/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import bancorrw.conta.ContaInvestimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class ContaInvestimentoDaoSql implements ContaInvestimentoDao{
    public ContaInvestimentoDaoSql(){
    }
    private static ContaInvestimentoDaoSql dao;
    public static ContaInvestimentoDaoSql getContaInvestimentoDaoSql(){
        if(dao==null)
            return dao = new ContaInvestimentoDaoSql();
        else
            return dao;
    } 
    private String insertContaInvstimento = 
        "INSERT INTO " +
            "contas_investimento " +
            "(id_conta," +
            "taxa_remuneracao_investimento," +
            "montante_minimo," +
            "deposito_minimo) " +
        "VALUES" +
            "(?,?,?,?)";
    private String insertConta = 
        "INSERT INTO " +
            "contas " +
            "(id_cliente," +
            "saldo) " +
        "VALUES" +
            "(?,?)";
    private String selectAll = 
        "SELECT "+
            "contas_investimento.id_conta, "+
            "saldo, "+
            "taxa_remuneracao_investimento, "+
            "montante_minimo, "+
            "deposito_minimo, "+
            "clientes.id_cliente,"+
            "nome, "+
            "cpf, "+
            "data_nascimento, "+
            "cartao_credito "+
        "FROM "+
            "contas "+
        "INNER JOIN "+
            "contas_investimento "+
        "ON "+
            "contas.id_conta=contas_investimento.id_conta "+
        "INNER JOIN "+
            "clientes "+
        "ON "+
            "contas.id_cliente=clientes.id_cliente ";
    private String selectById = 
        "SELECT "+
            "contas_investimento.id_conta, "+
            "saldo, "+
            "taxa_remuneracao_investimento, "+
            "montante_minimo, "+
            "deposito_minimo, "+
            "clientes.id_cliente,"+
            "nome, "+
            "cpf, "+
            "data_nascimento, "+
            "cartao_credito "+
        "FROM "+
            "contas "+
        "INNER JOIN "+
            "contas_investimento "+
        "ON "+
            "contas.id_conta=contas_investimento.id_conta "+
        "INNER JOIN "+
            "clientes "+
        "ON "+
            "contas.id_cliente=clientes.id_cliente "+
        "WHERE "+
            "contas.id_conta=?";
    private String selectByCliente = 
        "SELECT "+
            "contas_investimento.id_conta, "+
            "saldo, "+
            "taxa_remuneracao_investimento, "+
            "montante_minimo, "+
            "deposito_minimo, "+
            "clientes.id_cliente,"+
            "nome, "+
            "cpf, "+
            "data_nascimento, "+
            "cartao_credito "+
        "FROM "+
            "contas "+
        "INNER JOIN "+
            "contas_investimento "+
        "ON "+
            "contas.id_conta=contas_investimento.id_conta "+
        "INNER JOIN "+
            "clientes "+
        "ON "+
            "contas.id_cliente=clientes.id_cliente "+
        "WHERE "+
            "contas.id_cliente=?";

    private String updateContaInvestimento = 
        "UPDATE " +
            "contas_investimento " +
        "SET " + 
            "taxa_remuneracao_investimento=? ," +
            "montante_minimo=? ," +            
            "deposito_minimo=? " +
        "WHERE id_conta = ?";    
    private String updateConta = 
        "UPDATE " +
            "contas " +
        "SET " + 
            "saldo=? " +
        "WHERE id_conta = ?";   
    private String deleteById = 
                        "DELETE FROM "+
                            "contas " +
                        "WHERE " +
                            "id_conta=?";
    private String deleteAll = 
                        "DELETE " +
                            "contas,contas_investimento "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_investimento "+
                        "ON "+
                            "contas.id_conta=contas_investimento.id_conta ";     
    private final String ressetAIContas = "ALTER TABLE contas AUTO_INCREMENT =1";

    @Override
    public void add(ContaInvestimento conta) throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234")) {
        con.setAutoCommit(false);
            try (PreparedStatement stmtConta = con.prepareStatement(insertConta, Statement.RETURN_GENERATED_KEYS)) {
                stmtConta.setLong(1, conta.getCliente().getId());
                stmtConta.setDouble(2, conta.getSaldo());
                int affectedRowsConta = stmtConta.executeUpdate();

                if (affectedRowsConta == 0) {
                    throw new SQLException("Falha ao criar a conta, nenhuma linha foi afetada.");
                }

                try (ResultSet generatedKeys = stmtConta.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        conta.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Falha ao criar conta, ID não obtido.");
                    }
                }
            }

            try (PreparedStatement stmtContaInvestimento = con.prepareStatement(insertContaInvstimento)) {
                stmtContaInvestimento.setLong(1, conta.getId());
                stmtContaInvestimento.setDouble(2, conta.getTaxaRemuneracaoInvestimento());
                stmtContaInvestimento.setDouble(3, conta.getMontanteMinimo());
                stmtContaInvestimento.setDouble(4, conta.getDepositoMinimo());
                stmtContaInvestimento.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ContaInvestimento> getAll() throws Exception {
       List<ContaInvestimento> contasInvestimento = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
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
                ContaInvestimento ci = new ContaInvestimento(
                    rs.getDouble("taxa_remuneracao_investimento"),
                    rs.getDouble("montante_minimo"),
                    rs.getDouble("deposito_minimo"),
                    rs.getDouble("saldo"),
                    rs.getLong("id_conta"), 
                    cliente
                );

                contasInvestimento.add(ci);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
        return contasInvestimento;
    }

    @Override
    public ContaInvestimento getById(long id) throws Exception {
        ContaInvestimento contaInvestimento = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
             PreparedStatement stmt = con.prepareStatement(selectById)) {
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
                contaInvestimento = new ContaInvestimento(
                    rs.getDouble("taxa_remuneracao_investimento"),
                    rs.getDouble("montante_minimo"),
                    rs.getDouble("deposito_minimo"),
                    rs.getDouble("saldo"),
                    rs.getLong("id_conta"),
                    cliente
                );
                List<ContaInvestimento> contasInvestimento = cliente.getContasInvestimento();
                contasInvestimento.add(contaInvestimento);
                cliente.setContasInvestimento(contasInvestimento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
        return contaInvestimento;
    }

    @Override
    public void update(ContaInvestimento conta) throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234")) {
            try (PreparedStatement stmtConta = con.prepareStatement(updateConta)) {
                stmtConta.setDouble(1, conta.getSaldo());
                stmtConta.setLong(2, conta.getId());
                stmtConta.executeUpdate();
            }

            try (PreparedStatement stmtContaInvestimento = con.prepareStatement(updateContaInvestimento)) {
                stmtContaInvestimento.setDouble(1, conta.getTaxaRemuneracaoInvestimento());
                stmtContaInvestimento.setDouble(2, conta.getMontanteMinimo());
                stmtContaInvestimento.setDouble(3, conta.getDepositoMinimo());
                stmtContaInvestimento.setLong(4, conta.getId());
                stmtContaInvestimento.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(ContaInvestimento conta) throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
           PreparedStatement stmt = con.prepareStatement(deleteById)) {
           stmt.setLong(1, conta.getId());
           stmt.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
       }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
            Statement stmt = con.createStatement()) {
            stmt.execute(deleteAll);
            stmt.execute(ressetAIContas);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ContaInvestimento> getContasInvestimentoByCliente(Cliente cliente) throws Exception  {
        List<ContaInvestimento> contasInvestimento = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bancorrw", "root", "1234");
             PreparedStatement stmt = con.prepareStatement(selectByCliente)) {
            stmt.setLong(1, cliente.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ContaInvestimento contaInvestimento = new ContaInvestimento(
                    rs.getDouble("taxa_remuneracao_investimento"),
                    rs.getDouble("montante_minimo"),
                    rs.getDouble("deposito_minimo"),
                    rs.getDouble("saldo"),
                    rs.getLong("id_conta"),
                    cliente
                );
                contasInvestimento.add(contaInvestimento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
        // Atualiza a lista de contas do cliente apenas uma vez
        cliente.setContasInvestimento(contasInvestimento);
        return contasInvestimento;
   }
}
