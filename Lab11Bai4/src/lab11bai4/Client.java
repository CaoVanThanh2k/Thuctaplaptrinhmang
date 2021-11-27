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
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Client {
    public static final byte[] BUFFER = new byte[4096];
 
    public static void main(String[] args) {
        MulticastSocket socket = null;
        DatagramPacket inPacket = null;
        try {
            // Get the address that we are going to connect to.
            InetAddress address = InetAddress.getByName(Sever.GROUP_ADDRESS);
 
            // Create a new Multicast socket
            socket = new MulticastSocket(Sever.PORT);
 
            // Joint the Multicast group
            socket.joinGroup(address);
 
            while (true) {
                
                // Receive the information and print it.
                inPacket = new DatagramPacket(BUFFER, BUFFER.length);
                socket.receive(inPacket);
                String msg = new String(BUFFER, 0, inPacket.getLength());
                System.out.println(" MsgSever : " + msg);
//                String msg = new String(BUFFER, 0, inPacket.getLength());
//                System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
