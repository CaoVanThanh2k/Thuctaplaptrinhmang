/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 *
 * @author Admin
 */
public class ChatUDPClient extends JFrame implements ActionListener {

    static String nickName = "Dev";
    static String hostName = "localhost";
    static int port = 23;
    static DatagramSocket socket;
    InetAddress address = null;
    static JTextArea chatInput = null;
    static JTextArea chatView = null;
    JTextField hostTF;
    JTextField portTF;
    JTextField nickNameTF;
    JButton sendBtn;
    JButton connBtn;
    static Thread getMsgThread;

    public ChatUDPClient() {
        initFrame();
    }

    public static void main(String[] args) {
        new ChatUDPClient();
        getMsgThread = new Thread(new MsgReceiver());
    }

    static class MsgReceiver implements Runnable {

        byte[] buf = new byte[2048];

        @Override
        public void run() {
            while (true) {
                DatagramPacket inPacket = new DatagramPacket(buf, buf.length);
                try {
                    socket.receive(inPacket);
                    if (inPacket.getLength() != 0) {
                    }
                    String inMsg = new String(inPacket.getData(), 0, inPacket.getLength());
                    chatView.append(inMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
// Phương thức tạo giao diện

    private void initFrame() {
        setSize(500, 400);
        setMinimumSize(new Dimension(500, 400));
        setTitle(nickName + "'s chat box");
        Container container = getContentPane();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        JLabel lb1 = new JLabel("Host");
        hostTF = new JTextField(10);
        JLabel lb2 = new JLabel("Port");
        portTF = new JTextField(4);
        JLabel lb3 = new JLabel("Nickname");
        nickNameTF = new JTextField(6);
        connBtn = new JButton("Connect");
        connBtn.addActionListener(this);
        hostTF.setText(hostName);
        portTF.setText(port + "");
        nickNameTF.setText(nickName);
        topPanel.add(lb1);
        topPanel.add(hostTF);
        topPanel.add(lb2);
        topPanel.add(portTF);
        topPanel.add(lb3);
        topPanel.add(nickNameTF);
        topPanel.add(connBtn);
        panel.add(topPanel, "North");
        chatView = new JTextArea();
        chatView.setEditable(false);
        chatView.setLineWrap(true);
        JScrollPane conversationScrollView = new JScrollPane(chatView);
        JScrollPane chatInputScrollView = new JScrollPane();
        panel.add(conversationScrollView, "Center");
        chatInput = new JTextArea(3, 10);
        chatInput.setLineWrap(true);
        chatInput.setEditable(false);
        chatInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public  void removeUpdate(DocumentEvent e){
                checkInput();
            }
            
            @Override
            public void insertUpdate(DocumentEvent e){
                checkInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        chatInputScrollView.setViewportView(chatInput);
        JPanel chatToolPanel = new JPanel(new BorderLayout());
        chatToolPanel.add(chatInputScrollView, "Center");
        sendBtn = new JButton("Send");
        sendBtn.setEnabled(false);
        sendBtn.addActionListener(this);
        JPanel btnPanel = new JPanel();
        btnPanel.add(sendBtn);
        chatToolPanel.add(btnPanel, "East");
        panel.add(chatToolPanel, "South");
        container.add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                leave();
            }
        }));
        setVisible(true);
    }

    private void leave() {
        try {
            sendMsg(nickName + "\t" + "~leave");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkInput() {
        if (chatInput.getText().length() > 0) {
            sendBtn.setEnabled(true);
        } else {
            sendBtn.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = ((JButton) e.getSource()).getText();
        switch (btnName) {
            case "Connect":
                nickName = nickNameTF.getText();
                hostName = hostTF.getText();
                port = Integer.parseInt(portTF.getText());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(hostName);
                    String msg = nickName + "\t" + "join";
                    sendMsg(msg);
                    getMsgThread.start();
                    chatInput.setEditable(true);
                    this.setTitle(nickName + "'s chat box");
                    hostTF.setEditable(false);
                    portTF.setEditable(false);
                    nickNameTF.setEditable(false);
                    connBtn.setEnabled(false);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                break;
            case "Send":
                String msg = nickName + "\t" + chatInput.getText();
                try {
                    sendMsg(msg);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                chatInput.setText("");
                break;
            default:
                break;
        }
    }

    private void sendMsg(String msg) throws IOException {
        DatagramPacket outPacket = new DatagramPacket(msg.getBytes(),
                msg.length(), address, port);
        socket.send(outPacket);
    }
}
