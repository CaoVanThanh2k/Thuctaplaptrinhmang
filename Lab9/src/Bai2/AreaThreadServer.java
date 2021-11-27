/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai2;

import java.io.*;
import java.net.*;

/**
 *
 * @author Admin
 */
public class AreaThreadServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Khai bao bien
        int count;
        ServerSocket svr = null;
        Socket cl = null;
        int portServer = 3456;
        try {
            svr = new ServerSocket(portServer);
            count = 0;
            while (true) {
                cl = svr.accept();
                new NewThread(cl, count);
                count++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}


