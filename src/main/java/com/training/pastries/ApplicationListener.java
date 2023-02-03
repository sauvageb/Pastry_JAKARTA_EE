//package com.training.pastries;
//
//import com.training.pastries.dao.base.JPAUtil;
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//
//@WebListener
//public class ApplicationListener implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("APP INITLIAZED");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        System.out.println("APP DESTROYED");
//
//        // Fermer l'EMF
//        JPAUtil.shutdown();
//    }
//}
