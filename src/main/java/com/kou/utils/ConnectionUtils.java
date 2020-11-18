package com.kou.utils;

import javax.sql.DataSource;
import java.sql.Connection;


/**
 * @author JIAJUN KOU
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl=new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection(){
        //先从ThreadLocal上获取
        Connection conn = tl.get();
        try {
            //判断线程上是否有连接
            if(conn==null){
                conn=dataSource.getConnection();
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}
