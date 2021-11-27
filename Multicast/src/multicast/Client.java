/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multicast;
import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author duchi
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
