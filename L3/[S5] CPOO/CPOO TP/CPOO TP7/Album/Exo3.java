import java.util.*;
import static java.util.Comparator.comparing;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
class Album{
    public String name;
    public List<Track> tracks;

    public Album(String name, List<Track> tracks){
	this.name = name;
	this.tracks = tracks;
    }

    public String toString(){
	return name;
    }
}

class Track{
    public String name;
    public int rating; //entre 0 et 5
    
    public Track(String name, int rating){
	this.name = name;
	this.rating = rating;
    }
}

public class Exo3{
    public static void main(String[] args){
	List<Album> albums = new ArrayList<>();
	
	List<Track> t1 = new ArrayList<>();
	t1.add(new Track("lorem", 2));
	t1.add(new Track("ipsum", 2));
	t1.add(new Track("sit", 3));
	albums.add(new Album("Latin Music 1", t1));

	List<Track> t2 = new ArrayList<>();
	t2.add(new Track("dolor", 4));
	t2.add(new Track("amet", 5));
	t2.add(new Track("adispising", 3));
	albums.add(new Album("Latin Music 2", t2));     
	
	List<Track> t3 = new ArrayList<>();
	t3.add(new Track("elit", 2));
	t3.add(new Track("lorem", 0));
	t3.add(new Track("tempor", 3));
	t3.add(new Track("aliqua", 2));
	albums.add(new Album("More Latin Music", t3));
	
	List<Track> t4 = new ArrayList<>();
	t4.add(new Track("elit", 2));
	t4.add(new Track("enim", 0));
	t4.add(new Track("amet", 3));
	t4.add(new Track("aliqua", 2));
	albums.add(new Album("Latin Music For Ever", t4));

	List<Track> t5 = new ArrayList<>();
	t5.add(new Track("minim", 4));
	t5.add(new Track("veniam", 5));
	t5.add(new Track("laboris", 3));
	albums.add(new Album("Common Latin Music", t5));
        
        //3.1
        System.out.println(albums.stream()
                .map(album -> album.name)
                .reduce("", (s1, s2) -> s1.equals("")?s2:s1+" ; "+s2));
         
        //3.2
        albums.stream()
                .filter(album -> album.tracks.size()==4)
                .map(album -> album)
                .collect(toList())
                .forEach(System.out::println);
         
        //3.3
        System.out.println(albums.stream()
                .map(album -> album.name+" : "+album.tracks.stream()
                        .map(track -> track.name)
                        .reduce("", (s1, s2) -> s1.equals("")?s2:s1+"_"+s2))
                .reduce("", (s1, s2) -> s1.equals("")?s2:s1+"\n"+s2));
         
        //3.4
        List<Album> favs=new ArrayList<>();
        for(Album a:albums) {
                boolean hasFav=false;
                for(Track t:a.tracks) {
                        if(t.rating>=4) hasFav=true; break;
                }
                if(hasFav) favs.add(a);
        }
        Collections.sort(favs, Comparator.comparing(a->a.name));
        favs.forEach(System.out::println);
        
        System.out.println();
        
        albums.stream()
                .filter(album -> album.tracks.stream()
                        .anyMatch(track -> track.rating>=4))
                .sorted(comparing(album -> album.name))
                .collect(toList())
                .forEach(System.out::println);
    }
}
