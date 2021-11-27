/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class tcp_SeverThread extends Thread {

    ServerSocket mServer;
    JTextArea mTxaStatus; //JTextArea để lưu status của Server

   public tcp_SeverThread(JTextArea txaStatus){
       mTxaStatus = txaStatus;
   }
    @Override
    public void run() {
        try {
            String so1, so2, so3;
            int tong;
            mServer = new ServerSocket(1234);
            mTxaStatus.append("Server đã sẵn sàng!\nĐang chờ dữ liệu...\n\n");
            while (true) {
                Socket connectionSocket = mServer.accept();
                DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                so1 = inFromClient.readLine();
                so2 = inFromClient.readLine();
                mTxaStatus.append("Đã nhận 2 số a = " + so1 + ", b = " + so2 + "\n");
                int a = Integer.parseInt(so1);
                int b = Integer.parseInt(so2);
                tong = a + b;
                so3 = String.valueOf(tong);
                mTxaStatus.append("Thực hiện phép tính a + b\n");
                outToClient.writeBytes(so3 + '\n');
                mTxaStatus.append("Đã gửi kết quả về cho Client thành công\n\n");
            }
        } catch (Exception ex) {

            mTxaStatus.append("Server đã xảy ra lỗi\n");
        }
    }

    public void StopServer() {
        super.stop();
        try {
            mServer.close();
        } catch (Exception e) {
        }
    }

}
