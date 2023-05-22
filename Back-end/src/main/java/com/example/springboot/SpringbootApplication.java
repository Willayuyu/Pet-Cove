package com.example.springboot;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.ByteArrayOutputStream;

@SpringBootApplication
@MapperScan("com.example.springboot.mapper")
/*@ComponentScan(basePackages = {
        //"com.example.springboot.config",
        "com.example.springboot.controller",
        "com.example.springboot.service"})*/
public class SpringbootApplication {

    public static void main(String[] args) throws Exception {
        Session session = sshPass();
        SpringApplication.run(SpringbootApplication.class, args);
    }

    public static Session sshPass() {
        String host = "47.250.53.77";
        int port = 22;
        String username = "root";
        String password = "aliyunRoot0722";

        try {
            JSch jsch = new JSch();

            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            session.setPortForwardingL(3307, "localhost", 3306);

            Thread backgroundThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            backgroundThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
