package com.agtrack;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ServletContextClass implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public ServletContextClass(){}


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try{

            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("application.properties");

            Throwable throwable = null;

            try{

                if(inputStream != null){
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    System.getProperties().putAll(properties);
                }

            }catch (Throwable e){
                throwable = e;
                throw e;
            }finally {
                if(inputStream != null){
                    if(throwable != null){
                        try{
                            inputStream.close();
                        }catch (Throwable t){
                            throwable.addSuppressed(t);
                        }
                    }else{
                        inputStream.close();
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
