package com.kou.factory;

import com.kou.domian.Account;
import com.kou.service.IAccountService;
import com.kou.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author JIAJUN KOU
 * 用于创建Service 的代理对象工厂
 */
public class BeanFactory {

    private IAccountService accountService;
    //spring注入对象
    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    private TransactionManager transactionManager;
    public  void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    /**
     * 获取Service代理对象
     * @return
     */
    public IAccountService getAccountService() {

       IAccountService proxyAccountService=(IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object rtValue = null;

                try {
                    //1.开启事务
                    transactionManager.beginTransaction();
                    //2.执行操作
                    rtValue = method.invoke(accountService, args);
                    //3.提交事务
                    transactionManager.commit();
                    //4.返回结果
                    return rtValue;
                } catch (Exception e) {
                    //5.回滚
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                } finally {
                    //6.释放连接
                    transactionManager.close();
                }
            }
        });
        return proxyAccountService;
    }

}
