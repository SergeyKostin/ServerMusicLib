
package servermusiclibary;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.*;
import java.net.*;
import model.Message;
import model.TrackList;

public class Server extends Thread
{
    private  Socket socket;
    private  int num;

    public Server() {}
    public void setSocket(int num, Socket socket)
    {
        this.num = num;
        this.socket = socket;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        // Старт потока
        start();
    }
    public void run()
    {
        try {
            // Определяем входной и выходной потоки сокета
            // для обмена данными с клиентом 
             while(true) {
            InputStream  sin  =socket.getInputStream();
            OutputStream sout =socket.getOutputStream();
            ObjectInputStream inObj=new ObjectInputStream(sin);
            ObjectOutputStream out = new ObjectOutputStream(sout);
          
            ControllerServer controll=new ControllerServer();
            String line = null;
                Message mes=new Message();
                mes=(Message) inObj.readObject();
                line=mes.getStr();
                System.out.println("line : " + line);
                String [] str;
                str=line.split("%");
                String comand=str[0];
                String loc=str[1];
                
                if(str[0].compareTo("writeTXT")==0 ||str[0].equals("WriteXML")){
                    controll.setModel(mes.getModel());
                }
                
                System.out.println("The Command:  "+comand);
                System.out.println("The location file:  "+loc);
                controll.Comands(comand,loc);
                mes.setModel(controll.getModel());
                mes.setStr("OK");
                out.writeObject(mes);
                out.flush();
               
            }
        } catch(Exception e) {
            System.out.println("Exception : " + e);
        }
    }
    }

