package com.kou.dao.impl;

import com.kou.dao.IAccountDao;
import com.kou.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import com.kou.domian.Account;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.BeanUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JIAJUN KOU
 * 账户持久层实现类
 */
public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account", new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where id=?", new BeanHandler<Account>(Account.class), accountId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void updateAccount(Account account) {

        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set name=?,money=? where id=? ",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteAccount(Integer accountId) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"delete from account where id=?",accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Account findAccountByName(String accountName) {

        try {
            List<Account> accounts =runner.query(connectionUtils.getThreadConnection(),"select * from account where name=?", new BeanListHandler<Account>(Account.class), accountName);
            if(accounts==null || accounts.size()==0){
                return null;
            }
            if(accounts.size()>1){
                throw new RuntimeException("结果集不唯一");
            }
            return accounts.get(0);
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }

    }
}
