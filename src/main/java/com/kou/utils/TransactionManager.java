package com.kou.utils;

import java.sql.SQLException;

/**
 * @author JIAJUN KOU
 *
 * 事务管理的工具类
 *
 * 包含开启事务
 * 提交事务
 * 回滚事务
 * 释放连接
 */
public class TransactionManager {

    private ConnectionUtils  connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void close(){
        try {
            connectionUtils.getThreadConnection().close();//不是真的关闭，而是还回了线程池中
            connectionUtils.removeConnection();//解绑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
