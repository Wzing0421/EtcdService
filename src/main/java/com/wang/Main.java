package com.wang;

import com.wang.server.udpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");

        System.out.println("Udp server starts!");


        udpServer server = (udpServer) ctx.getBean("udpServer");
        server.run();
    }
}
