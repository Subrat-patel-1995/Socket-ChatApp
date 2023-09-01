
import java.net.*;
import java.io.*;
class Client{

    Socket socket;
    BufferedReader br;
    PrintWriter out;
    Client(){
        try {
            System.out.println("Sending Request to erver");
            socket=new Socket("127.0.0.1",7777);
            System.out.println("Connection done");

            br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            stratReading();
            stratWritig();



        }catch (Exception e ){
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
                        System.out.println("Server  has Stopped");
                        break;
                    }
                    System.out.println("Server :-"+msg);
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
        new Client();
    }
}