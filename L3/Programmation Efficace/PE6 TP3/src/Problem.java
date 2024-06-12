import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Problem {
    String principale;
    ArrayList<SousChaine> souschaines;

    private Intervalle solution = null;

    public Problem(String principale, ArrayList<SousChaine> souschaines) {
        this.principale = principale;
        this.souschaines = souschaines;
        for (SousChaine schaine : souschaines) schaine.init(principale);
        souschaines.stream().map(SousChaine::getOccurences).flatMap(ArrayList::stream).sorted().collect(Collectors.toList());
    }

    public static Problem fromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        int nb = sc.nextInt();
        sc.useDelimiter("#");
        ArrayList<SousChaine> souschaines=new ArrayList<SousChaine>();

        for(int i=0; i<nb; i++) souschaines.add(new SousChaine(sc.next().replace("\n","")));
        
        String principale=sc.next().replace("\n","");
        return new Problem(principale, souschaines);
    }

    public ArrayList<Intervalle> ajouterIntervalles(SousChaine s, ArrayList<Intervalle> solutions){
        ArrayList<Intervalle> res = new ArrayList<Intervalle>();
        for (int index : s.getOccurences())
            for (Intervalle i : solutions)
                res.add(i.add(index, index + s.getLength()));
        
        return res;
    }

// renvoie longueur de la sous chaine trouvée, l'indice, la sous chaine
    public void getSolution() {
        ArrayList<Intervalle> sol = new ArrayList<>();
        for(SousChaine s : souschaines)
            if(s.getOccurences().isEmpty())
                return;
        
        SousChaine s1 = souschaines.get(0);
        for (int index : s1.getOccurences()) 
            sol.add(new Intervalle(index, index + s1.getLength()));
        for (int i = 1; i < souschaines.size(); i++)
            sol = ajouterIntervalles(souschaines.get(i), sol);

        solution = sol.get(0);
        for (int i = 1; i < sol.size(); i++)
            if (solution.getLength() > sol.get(i).getLength())
                solution = sol.get(i);
    }

    private String format(String raw){
        Pattern p = Pattern.compile("(.{" + 80 + "})", Pattern.DOTALL);
        Matcher m = p.matcher(raw);
        return m.replaceAll("$1" + "\n");
    }

    public void save(String filename){
        StringBuilder builder = new StringBuilder();
        if (this.solution != null) {
            int start = solution.start;
            int length = solution.end - solution.start;
            String chaine = this.principale.substring(solution.start, solution.end);

            builder.append(length);
            builder.append("\n");
            builder.append(start);
            builder.append("\n");
            builder.append(format(chaine));
            builder.append("#");
        }
        else {
            builder.append("0\n#");
        }

        try{
            FileWriter descr=new FileWriter(filename.substring(0, filename.length()-3)+"out");
            descr.write(builder.toString());
            descr.close();
        }
        catch(IOException e){}

    }

}
