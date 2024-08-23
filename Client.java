
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

public  class Client  extends JFrame {
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    // Declare component
    private JLabel heading = new JLabel("Client.Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    private Font font = new Font("Roboto",Font.PLAIN,20);
    //constructor
    public Client()
    {
        try{
            System.out.println("Sending request to server");
            socket = new Socket("127.0.0.1",7778);
            System.out.println("Connection done");
             br =new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(socket.getOutputStream());
            
           createGUI();
           handleEvents();
           startReading();
          //  startWriting();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEvents() {
        messageInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("key released " + e.getKeyCode());
                if(e.getKeyCode()==10){
                  //  System.out.println("You have passed Enter Bottom");
                    String contentToSend = messageInput.getText();
                    messageArea.append("Me " + contentToSend +"\n");
                    out.println(contentToSend);
                    out.flush();
                    messageInput.setText("");
                    messageInput.requestFocus();
                }
            }
        });
    }

    private void createGUI() {
        //gui code..
        this.setTitle("Client message[END]");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //coding  for component
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        heading.setIcon(new ImageIcon("logo.png"));
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading .setHorizontalTextPosition(SwingConstants.CENTER);
        messageInput .setHorizontalAlignment(SwingConstants.CENTER);


        messageArea.setEditable(false);
        //frame ka layout set karenge
        this.setLayout(new BorderLayout());

        //adding the components to frmae
        this.add(heading,BorderLayout.NORTH);
        JScrollPane jScrollPane = new JScrollPane(messageArea);
        this.add(messageArea,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);



        this.setVisible(true);
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
                        JOptionPane.showMessageDialog(this,"server treminated the chat");
                        messageInput.setEnabled(false);
                        break;
                    }
                   // System.out.println("Server :" + msg);
                    messageArea.append("server :" + msg+"\n");
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



              
