/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class Server {
     public static void main(String args[]) {
 
       ServerSocket cong = null;
       String line="";
       BufferedReader is;
       BufferedWriter os;
       Socket socket = null;
       try {
           cong = new ServerSocket(9999);
       } catch (IOException e) {
           System.out.println(e);
           System.exit(1);
       }
       try {
           System.out.println("Chạy Client!");
           socket = cong.accept();
           System.out.println("Chấp nhận máy con!");
           is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           //while (true) {
               int a = Integer.parseInt(is.readLine());
               int b = Integer.parseInt(is.readLine());
               String c=String.valueOf(a+b);
               os.write(c);
               os.newLine();
               os.flush();  
           //}
 
       } catch (IOException e) {
           System.out.println(e);
       }
       System.out.println("Dừng kết nối Sever!");
   }
}
