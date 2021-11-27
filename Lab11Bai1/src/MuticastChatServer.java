/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.net.*;

/**
 *
 * @author Admin
 */
public class MuticastChatServer {

    public static void main(String args[]) throws Exception {
        int portnumber = 5000;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }
        // Create a MulticastSocket
        MulticastSocket ServerMulticastSocket = new MulticastSocket(portnumber);
        System.out.println("MulticastSocket is created at: " + portnumber);
        // Determine the IP address of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6");
        ServerMulticastSocket.joinGroup(group);
        System.out.println("joinGroup method is called...");
        boolean infinite = true;
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            ServerMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();
            System.out.println("Message received from Client = " + msg);
        }
        ServerMulticastSocket.close();
    }
}
