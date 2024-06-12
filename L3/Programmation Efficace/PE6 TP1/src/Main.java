package TP1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main{
    static void PCC(String path, String pathRes) throws FileNotFoundException{
        File file=new File(path);
        Scanner sc=new Scanner(file);
        sc.useDelimiter(" "); //parser selon " "
        
        int size=sc.nextInt();
        int[][] matrice=new int[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                matrice[i][j]=-1; //-1=infini
        matrice[0][0]=0;
        
        String noeudDest=sc.next();
        int dest=Integer.parseInt(noeudDest.substring(1, noeudDest.length()));
        
        String nx, ny;
        int x, y;
        while(sc.hasNext()){
            nx=sc.next();
            x=Integer.parseInt(nx.substring(1, nx.length()));
            ny=sc.next();
            y=Integer.parseInt(ny.substring(1, ny.length()));
            matrice[x][y]=sc.nextInt();
            matrice[y][x]=matrice[x][y];
        }
        
        dijkstra(matrice, dest, size, pathRes);
        sc.close();
    }
    
    static void dijkstra(int[][] matrice, int dest, int size, String pathRes){
        LinkedList<Integer> queue=new LinkedList<Integer>();
        queue.add(0);
        int[] dist=new int[size];
        int[] prev=new int[size];
        for(int i=1; i<size; i++){
            dist[i]=-1;
            prev[i]=-1;
            queue.add(i);
        }
        
        int tmpDist;
        int u;
        while(!queue.isEmpty()){
            u=min(queue, dist);
            for(int v=0; v<size; v++){
                if(v!=u && matrice[u][v]!=-1){
                    tmpDist=dist[u]+matrice[u][v];
                    if(dist[v]==-1 || tmpDist<dist[v]){
                        dist[v]=tmpDist;
                        prev[v]=u;
                    }
                }
            }
        }
        
        writeResponse(dest, prev, pathRes);
    }
    
    static int min(LinkedList<Integer> queue, int[] dist){
        int res=queue.get(0);
        int ind=0; //index a supprimer
        
        int tmp;
        for(int i=1; i<queue.size(); i++){
            tmp=queue.get(i);
            if(dist[res]==-1 || (dist[tmp]>-1 && dist[tmp]<dist[res])){
                res=tmp;
                ind=i;
            }
        }
        queue.remove(ind);
        return res;
    }
    
    static void writeResponse(int dest, int[] prev, String pathRes){
        String toWrite="";
        int sommetToWrite=dest;
        while(sommetToWrite!=0){
            toWrite=("N"+sommetToWrite+" ")+toWrite;
            sommetToWrite=prev[sommetToWrite];
        }
        toWrite=("N"+sommetToWrite+" ")+toWrite; //ecrire le sommet 0
        
        try{
            FileWriter descr=new FileWriter(pathRes);
            descr.write(toWrite);
            descr.close();
        }
        catch(IOException e){}
    }
    
    static String compare(String path1, String path2) throws FileNotFoundException{
        File file1=new File(path1);
        File file2=new File(path2);
        
        Scanner sc=new Scanner(file1);
        String s1=sc.next();
        
        sc=new Scanner(file2);
        String s2=sc.next();
        
        return (s1.equals(s2))?"same":"different";
    }
    
    public static void main(String[] args){
        for(int i=1; i<5; i++){
            try{
                PCC("src/TP1/ex"+i+".gr", "src/TP1/mySol"+i+".gr");
                System.out.println("graphe "+i+" : "+compare("src/TP1/mySol"+i+".gr", "src/TP1/sol"+i+".gr"));
            }
            catch(FileNotFoundException e){
                System.out.println("file "+i+" not found");
            }
        }
    }
}