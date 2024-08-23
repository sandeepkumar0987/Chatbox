import java.io.*;
import java.net.*;



 final class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    //constructor
    public Server(){
        try{
            server = new ServerSocket(7778);
            System.out.println("srever is ready to accept to connection.");
            System.out.println("waiting..");
            socket =server.accept();

            br =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();


        } catch(IOException e) {
            e.printStackTrace();
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
                        System.out.println("client terminated the chat");
                        break;
                    }
                    System.out.println("Client :" + msg);
                } catch(IOException e) {
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }

        };
        new Thread(r2).start();
    }
    public static void main(String[] args) {

        System.out.println("This is a server..going to start.");
          new Server();
    }
}
