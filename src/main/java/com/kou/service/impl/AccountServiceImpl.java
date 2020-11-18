package com.kou.service.impl;

import com.kou.dao.IAccountDao;
import com.kou.domian.Account;
import com.kou.service.IAccountService;
import com.kou.utils.TransactionManager;

import java.util.List;

/**
 * @author JIAJUN KOU
 * 账户业务层实现类
 *
 * 事务控制应该全都在业务层
 */
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;

    //提供set方法等待spring注入对象
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);

    }
    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);

    }
    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);

    }
    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);

    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {

            //2.执行操作
            //2.1.根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            //2.2.根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            //2.3.转出账户减钱
            source.setMoney(source.getMoney()-money);
            //2.4.转入账户加钱
            target.setMoney(target.getMoney()+money);
            //2.5.更新转出和转入账户
            accountDao.updateAccount(source);
            accountDao.updateAccount(target);


    }
}
