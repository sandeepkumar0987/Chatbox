

import java.io.*;
import java.net.*;

public final class Client {
    Socket socket;

    BufferedReader br;
    PrintWriter out;
    public Client()
    {
        try{
            System.out.println("Sending request to server");
          socket = new Socket("127.0.0.1",7778);
            System.out.println("Connection done");
            br =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startReading()
    {
        // thread-read karke deta rahega
        Runnable r1=()->{
            System.out.println("reader started...");
            while(true)
            {
                try {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Server terminated the chat");
                        break;
                    }
                    System.out.println("Server :" + msg);
                } catch(IOException _)
                {
                }
            }

        };
        new Thread(r1).start();
    }

    public void startWriting()
    {
        // thread-data user lega and the send karega client tak
        Runnable r2=()->{
            System.out.println("Writer started...");
            while(true)
            {
                try{

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content =br1.readLine();

                    out.println(content);
                    out.flush();

                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
            }

        };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("clint is ready");
        new Client();
    }
}
