/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettin;

/**
 *
 * @author Hieu Bom IT
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class Server {
 
   public static void main(String args[]) {
 
       ServerSocket cong = null;
       String line="";
       BufferedReader is;
       BufferedWriter os;
       Socket socket = null;
       try {
           cong = new ServerSocket(9999);
           System.out.println(e);
           System.exit(1);
       }
 
       try {
           System.out.println("Chạy Client!");
           socket = cong.accept();
           System.out.println("Chấp nhận máy con!");
           is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           while (true) {
               line = is.readLine();
               System.out.println("Client: >> " + line);
               if (line.equals("Thoat")) {
                   os.write(">> OK");
                   os.newLine();
                   os.flush();
                   break;
               }
               Scanner sc=new Scanner(System.in);
               System.out.print("Nhập tin: ");
               String tin=sc.nextLine();
               os.write(">> " + tin);
               os.newLine();
               os.flush();  
           }
 
       } catch (IOException e) {
           System.out.println(e);
       }
       System.out.println("Dừng kết nối Sever!");
   }
}
