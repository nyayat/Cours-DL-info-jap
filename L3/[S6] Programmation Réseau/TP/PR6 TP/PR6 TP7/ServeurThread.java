import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ServeurThread implements Runnable{
        Socket client;
        String pageWeb;
        int id;
        static int cptClients=1;
        
        ServeurThread(Socket client){
            this.client=client;
            synchronized(this){
                cptClients++;
            }
        }

        void initializePageWeb(String requete){
            pageWeb="HTTP/1.1 200 OK\nContent-Type: text/html; charset=utf-8\nConnection: close\n\n";
            if(requete.contains(".html")){
                requete=requete.substring(1);
                try{
                    Scanner sc=new Scanner(new File(requete));
                    while(sc.hasNextLine()) pageWeb+=sc.nextLine();
                    sc.close();
                }
                catch(FileNotFoundException e){
                    pageWeb+=pageErreur();
                }
            }
            else {
                pageWeb+="<!DOCTYPE html>\n<html lang=\"fr\"\n";
                pageWeb+="\t<head>\n\t\t<meta charset=\"utf-8\">\n\t\t<title>Ma Page Web</title>\n\t</head>\n";
                pageWeb+="\t<body>\n\t\t<p>Vous &ecirc;tes le "+id+(id!=1?"&egrave;":"er")+" client.</p>";
                pageWeb+="<p>"+requete+"</p>\n\t</body>\n</html>";
            }
        }

        String pageErreur(){
            return "<!DOCTYPE html>\n<html lang=\"fr\"\n"
                    +"\t<head>\n\t\t<meta charset=\"utf-8\">\n\t\t<title>ERROR 404</title>\n\t</head>\n"
                    +"\t<body>\n\t\t<p>Page non trouv&eacute;e.</p>"
                    +"\n\t</body>\n</html>";
        }

        String parseRequete(String requete){
            Scanner sc=new Scanner(requete);
            sc.useDelimiter("<br>");
            String tmp="", host="", path="", version="", userAgent="";
            while(sc.hasNext()){
                tmp=sc.next();
                if(tmp.contains("favicon.ico")){
                    synchronized(this){
                        id--;
                        cptClients--;
                    }
                } 
                else if(tmp.contains("GET")){
                    path=tmp.substring(4, tmp.indexOf("HTTP/"));
                    version=tmp.substring(tmp.indexOf("HTTP/")+5);
                    if(path.contains(".html")) return path;
                }
                else if(tmp.contains("Host")) host=tmp.substring(5, tmp.length()-1);
                else if(tmp.contains("User-Agent")) userAgent=tmp.substring("User-Agent: ".length());
            }
            sc.close();
            tmp="Vous avez visit&eacute; la page "+host+path+" avec la version "+version+" de HTTP<br>\nNavigateur : "+userAgent;
            return tmp;
        }
        
        public void run(){
            try{
                BufferedReader buff=new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter printW=new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                String requete="";
                
                int k=0;
                int[] stop={'\r', '\n'};
                while(k!=4){
                    char read=(char)buff.read();
                    if(read==stop[k%2]) k++;
                    else k=0;
                    requete+=(read=='\n'?"<br>":read);
                }
                
                initializePageWeb(parseRequete(requete));
                printW.println(pageWeb);
                printW.flush();
                
                buff.close();
                printW.close();
                client.close();
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }