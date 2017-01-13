/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermusiclibary;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMusicLibrary {

    public static void main(String[] args) {
      int port=5555;// случайный порт (может быть любое число от 1025 до 65535)
        
         ServerSocket srvSocket = null;
        try {
            try {
                int i = 0; // Счётчик подключений
                // Подключение сокета к localhost
                InetAddress ia = InetAddress.getByName("localhost");
                srvSocket = new ServerSocket(port, 0, ia);
                
                System.out.println("Server started\n\n");

                while(true) {
                    // ожидание подключения
                    Socket socket = srvSocket.accept();
                    System.err.println("Client accepted");
                    // Стартуем обработку клиента в отдельном потоке
                    new Server().setSocket(i++, socket);
                }
            } catch(Exception e) {
                System.out.println("Exception : " + e);
            }
        } finally {
            try {
                if (srvSocket != null)
                    srvSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
    
}
