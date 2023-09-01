import java.net.*;
import java.io.*;
class Server{

    ServerSocket serverSocket;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public Server(){
        try {

            serverSocket=new ServerSocket(7777);
            System.out.println("Server is Ready");
            System.out.println("Waiting");
            socket=serverSocket.accept();
            br= new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out=new PrintWriter(socket.getOutputStream());

            stratReading();
            stratWritig();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void stratReading(){
        System.out.println("Strat Reading");
        Runnable r1=()->{
            while (true){
                try {
                    String msg=br.readLine();
                    if(msg.equals("exit")){
                        System.out.println("Client  has Stopped");
                        break;
                    }
                    System.out.println("Client :-"+msg);
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }

        };
        new Thread(r1).start();

    }

    public void stratWritig(){
        System.out.println("Start Writing");
        Runnable r2=()->{
            while (true){
                try {
                    BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();

                }catch (Exception e ){
                    e.printStackTrace();
                }
            }

        };
        new Thread(r2).start();

    }


    public static void main(String[] args) {
        System.out.println("Maiin");
        new Server();
    }
}