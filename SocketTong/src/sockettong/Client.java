/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettong;

/**
 *
 * @author Hieu Bom IT
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class Client {
 
   public static void main(String[] args) {
 
       // Địa chỉ máy chủ.
       final String serverHost = "127.0.0.1";
       Socket socket = null;
       BufferedWriter os = null;
       BufferedReader is = null;
       
       try {
           //System.out.println("Ấn 'Thoat' để dừng trò chuyện!");
           // Gửi yêu cầu kết nối tới Server đang lắng nghe
           // Trên máy 'localhost' cổng 9999.
           socket = new Socket(serverHost, 9999);
           os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
       } catch (UnknownHostException e) {
           System.err.println("Không thấy được Máy Chủ: " + serverHost);
           return;
       } catch (IOException e) {
           System.err.println("Không kết nối được Máy Chủ: " + serverHost);
           return;
       }
 
       try {
           Scanner sc=new Scanner(System.in);
           String gui="";
           //while (true){
                System.out.print("Nhập a: ");
                String a=sc.nextLine();
                System.out.print("Nhập b: ");
                String b=sc.nextLine();
                os.write(a+"\n");
                os.write(b+"\n");
                os.newLine(); 
                os.flush(); 
                String nhan= is.readLine();
                System.out.println("Tong la: " + nhan);
               // break;
           //}
           os.close();
           is.close();
           socket.close();
           System.exit(0);
       } catch (IOException e) {
           System.err.println(e);
       }
   } 
}