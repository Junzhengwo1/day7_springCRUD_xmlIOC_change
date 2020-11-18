package com.kou.test;

import com.kou.domian.Account;
import com.kou.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")

public class AccountServiceTest {
    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService as;


    @Test
    public void testFindAll(){
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testTransfer(){
        System.out.println("开始转账。。。。。。。");
        as.transfer("bbb","aaa",500f);

        testFindAll();
    }


}
