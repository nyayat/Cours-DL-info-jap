import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//3.1
public class Serveur{
    static String titre="Ma page web !";
    static final String entete1="HTTP/1.1 200 OK\nContent-Type: text/html; charset=utf-8\nConnection: close\n\n";
    static final String entete2="<!DOCTYPE html>\n<html lang=\"fr\">\n\t<head>\n\t\t<meta charset=\"utf-8\">\n\t\t<title>"+titre+"</title>\n\t</head>\n\t<body>\n";
    static final String end="\t</body>\n</html>";
    
    public static void serveurHTML(){
        try{
            ServerSocket server=new ServerSocket(8080);
            while(true){
                Socket socket=server.accept();
                BufferedReader buff=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printW=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                int k=0;
                int[] stop={'\r', '\n'};
                while(k!=4){
                    if(buff.read()==stop[k%2]) k++;
                    else k=0;
                }
                
                String corps="\t\t<p>Ceci est un paragraphe de ma page web.</p>\n";
                printW.println(entete1+entete2+corps+end);
                printW.flush();
                
                buff.close();
                printW.close();
                socket.close();
            }
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void serveurHTMLThreads(){
        try{
            ServerSocket server=new ServerSocket(8080);
            while(true){
                Service service=new Service(server.accept());
                Thread th=new Thread(service);
                th.start();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static class Service implements Runnable{
        static int id=0;
        Socket socket;
        String requestToPrint="\t\t<p>";
        private String request="";

        public Service(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run(){
            try{
                BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                int k=0;
                char[] stop=new char[]{'\r', '\n'};
                while(k!=4){
                    char read=(char)br.read();
                    if(read==stop[k%2]) k++;
                    else k=0;
                    requestToPrint+=read=='\n'?"<br />\n\t\t":read;
                    request+=read;
                }
                requestToPrint=requestToPrint.substring(0, requestToPrint.length()-19);
                requestToPrint+="</p>\n";
                
                String headers=getHeaders(request);
                String headersToPrint="\t\t<p>"+headers+"</p>\n";
                if(!headers.contains("NO_INCREMENT")) increment();
                
                String body="\t\t<p>Vous &ecirc;tes la "+id+"&egrave;me personne &agrave; vous connecter ici</p>\n";				
                if(headers.contains(".html")) sendPage(pw, headers);
                else pw.println(entete1+entete2+body+requestToPrint+headersToPrint+end);
                
                pw.flush();
                br.close();
                pw.close();
                socket.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        private synchronized void increment(){
            id++;
        }

        private String getHeaders(String request){
            String res1="Vous avez visit&eacute; le lien ", res2=" avec la version ", res3=" de HTTP";
            String host="", path="", version="", browser="";
            try(Scanner sc=new Scanner(request)){
                while(true){
                    String line=sc.nextLine();
                    if(line.contains("favicon.ico")) return "NO_INCREMENT";
                    else if(line.contains("GET")){
                        path=line.substring(5, line.indexOf("HTTP/"));
                        version=line.substring(line.indexOf("HTTP/")+5);
                        if(path.contains(".html")) return path;
                    }
                    else if(line.contains("Host")) host=line.substring(5);
                    else if(line.contains("User-Agent")) browser=line.substring("User-Agent: ".length());
                }
            }
            catch (Exception e) {}
            return res1+host+"/"+path+res2+version+res3+" ; Navigateur: "+browser;
        }

        private void sendPage(PrintWriter pw, String filepath){
            try(Scanner sc=new Scanner(new File(filepath))){
                String page="";
                while(sc.hasNextLine()) page+=sc.nextLine()+'\n';
                pw.println(entete1+page);
            }
            catch(Exception e){
                pw.println(entete1+entete2+"<p>Page not found</p>\n"+end);
            }
        }
    }

    public static void main(String[] args) {
        //serveurHTML();
        serveurHTMLThreads();
    }
}