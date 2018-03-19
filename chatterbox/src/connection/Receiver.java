/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import ChatBox;
import connInfo.ConnInfo;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhilash Saseedharan
 */
public class Receiver implements Runnable{
    private final ConnInfo cinfo;
    private Socket socket;
    private ChatBox chatbox;
    public Receiver(ConnInfo cp, Socket socket) {
        this.cinfo = cp;
        this.socket = socket;
    }
    public Receiver(ConnInfo cp, Socket socket, ChatBox cb) {
        this.cinfo = cp;
        this.socket = socket;
        this.chatbox = cb;
    }
    @Override
    public void run() {
        InputStream datastream = null;
        try {
            //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            Socket s = cinfo.getSocket();
            datastream = socket.getInputStream();
            DataInputStream indata = new DataInputStream(datastream);
            while(true){
                String data = new String (indata.readUTF());
                chatbox.getTextareamap().get(cinfo.getUsername()).append("\n"+cinfo.getUsername().toUpperCase()+": "+data+"\n");
                System.out.println(data);
            }
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                datastream.close();
            } catch (IOException ex) {
                Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
