/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai2;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author Admin
 */
public class areaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket cl = null;
        BufferedReader inp = null;//luong nhap
        PrintWriter outp = null;//luong xuat
        BufferedReader key = null;//luong nhap tu ban phim
        String ipServer = "127.0.0.1";//Chuoi dia chi Server
        int portServer = 3456; //dia chi cong Server
        String r; //Tao socket va ket noi toi Server
        try{
            cl = new Socket(ipServer, portServer);
            //tao luong nha/xuatp kieu ky tu cho socket
            inp = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            outp = new PrintWriter(cl.getOutputStream(),true);
            //tao luong nhap tu ban phim
            key = new BufferedReader(new InputStreamReader(System.in));
            //Nhap ban kinh r tu ban phim
            System.out.print("r=");
            r = key.readLine().trim();
            //gui r toi Server
            outp.println(r);
            //Nhan dien tich tra ve tu Server va hien thi
            System.out.println("Area:" + inp.readLine());
            //ket thuc chuong trinh
            if (inp != null) {
                inp.close();
            }
            if (key != null) {
                key.close();
            }
            if (outp != null) {
                outp.close();
            }
            if (cl != null) {
                cl.close();
            }
        }
        catch(IOException e)
           {
              System.out.println(e);
           }
           
    }

}
