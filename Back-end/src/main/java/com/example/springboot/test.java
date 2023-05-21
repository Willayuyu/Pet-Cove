package com.example.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class test {

    public static void main(String[] args) {
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
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

