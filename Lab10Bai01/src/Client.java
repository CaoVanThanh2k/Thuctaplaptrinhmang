/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.net.*;
import java.io.*;
import java.applet.Applet;

/**
 *
 * @author Admin
 */
public class Client extends java.applet.Applet {

    String myHost;

    public void init() {
        myHost = getCodeBase().getHost();
    }

    public void sendMessage(String message) {
//phương thức gửi tin nhắn
        try {
            byte[] data = new byte[message.length()];
            message.getBytes(0, data.length, data, 0);
            InetAddress addr = InetAddress.getByName(myHost);
            DatagramPacket pack = new DatagramPacket(data, data.length, addr, 1234);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void start() {
        sendMessage("Bắt đầu!");
    }

    public void stop() {
        sendMessage("Kết thúc!");
    }
}
