package com.kou.service;

import com.kou.domian.Account;

import java.util.List;

/**
 * 账户的service层接口
 * @author dell
 */
public interface IAccountService {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个 通过id
     * @return
     * @param accountId
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据id删除账户
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 转账
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money       金额
     */
    void transfer(String sourceName,String targetName,Float money);
}
