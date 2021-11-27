/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11bai4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Sever {
    public static final String GROUP_ADDRESS = "226.0.0.1";
    public static final int PORT = 8888;
 
    public static void main(String[] args) throws InterruptedException {
        DatagramSocket socket = null;
        try {
            
            InetAddress address = InetAddress.getByName(GROUP_ADDRESS);
 
            socket = new DatagramSocket();
 
            DatagramPacket outPacket = null;
            long counter = 0;
            while (true) {
                
                
                
                Scanner sc= new Scanner(System.in);
                String msg=sc.nextLine();
//                String msg = "Sent message No. " + counter;
                counter++;
                outPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, PORT);
                socket.send(outPacket);
                //System.out.println("Server sent packet with msg: " + msg);
                Thread.sleep(1000);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
