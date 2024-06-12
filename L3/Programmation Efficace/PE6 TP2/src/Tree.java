import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Tree{
    Node racine;
    ArrayList<Node> feuilles=new ArrayList<Node>();
    int creditsR;
    
    Tree(Ville v, int c, int length, int[][] distance, HashMap<Integer, Ville> villes){
        racine=new Node(v, 0, null);
        creditsR=c;
        createTree(racine, creditsR, length, distance, villes);
    }
    
    void createTree(Node dep, int credits, int length, int[][] distance, HashMap<Integer, Ville> villes){
        boolean isFeuille=true;
        for(int i=0; i<distance.length; i++) //cherche les voisins de dep
            if(distance[dep.ville.index][i]>0 && !villes.get(i).isVisited()
                    && !dep.alreadyInBranch(villes.get(i))
                    && distance[dep.ville.index][i]<=credits)
                isFeuille=!dep.enfants.add(new Node(villes.get(i), dep.distance+distance[dep.ville.index][i], dep));
        if(isFeuille || length==0) feuilles.add(dep);
        else
            for(Node n : dep.enfants) createTree(n, creditsR-n.distance, length-1, distance, villes);
    }
    
    
    LinkedList<Ville> findPath(int length){
        LinkedList<Ville> path=new LinkedList<Ville>();
        Node[] nodes;
        for(int i=length; i>0; i--){ //on essaie d'avoir le nombre max de villes
            int len=i;
            nodes=feuilles.stream()
                    .filter(n -> n.getBranchLength()==len)
                    .toArray(Node[]::new);
            if(nodes.length>0){
                int distMin=nodes[0].distance;
                int index=0;
                for(int j=1; j<nodes.length; j++){
                    if(distMin>nodes[j].distance){
                        distMin=nodes[j].distance;
                        index=j;
                    }
                }
                while(nodes[index].parent!=null){ //pas la racine
                    path.addFirst(nodes[index].ville);
                    nodes[index]=nodes[index].parent;
                }
                return path;
            }
        }
        return path;
    }
    
    class Node{
        Ville ville;
        int distance;
        Node parent;
        ArrayList<Node> enfants=new ArrayList<Node>();
        
        Node(Ville v, int d, Node p){
            ville=v;
            distance=d;
            parent=p;
        }
        
        int getBranchLength(){ //depuis les feuilles -> on monte
            return (parent!=null)? 1+parent.getBranchLength():0;
        }
        
        boolean alreadyInBranch(Ville v){
            if(ville==v) return true;
            if(parent!=null) return parent.alreadyInBranch(v);
            return false;
        }
    }
}