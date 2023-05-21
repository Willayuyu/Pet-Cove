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
//        listFolderStructure("root", "aliyunRoot0722", "47.250.53.77", 22, "ls");
        Session session = sshPass();
        SpringApplication.run(SpringbootApplication.class, args);
        session.disconnect();
    }

    public static void listFolderStructure(String username, String password,
                                           String host, int port, String command) throws Exception {

        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    public static Session sshPass() {
        String host = "47.250.53.77";
        int port = 22;
        String username = "root";
        String password = "aliyunRoot0722";

        String localPortForwarding = "3307:localhost:3306";

        try {
            JSch jsch = new JSch();

            // Create SSH session
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Set up local port forwarding
            int assignedPort = session.setPortForwardingL(3307, "localhost", 3306);

            System.out.println("Local port forwarding established: localhost:" + assignedPort + " -> " + host + ":" + localPortForwarding);

            // You can now connect to the local port (e.g., 3307) to access the remote MySQL database

            // Close the SSH session
            return session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
