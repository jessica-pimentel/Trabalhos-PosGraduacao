/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import static bancorrw.dao.DaoType.SQL;

/**
 *
 * @author rafae
 */
public class DaoFactory {
    private DaoFactory(){
    }
    
    public static ClienteDao getClienteDao(DaoType type){
        return new ClienteDaoSql();
    }
    
    public static ContaCorrenteDao getContaCorrenteDao(DaoType type){
        return new ContaCorrenteDaoSql();
    }
    
    public static ContaInvestimentoDao getContaInvestimentoDao(DaoType type){
        return new ContaInvestimentoDaoSql(); 
    }
    
}
