import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SousChaine {
    private final String content;
    private final ArrayList<Integer> occurences;
    private final int length;

    public SousChaine(String content) {
        this.content = content;
        this.occurences = new ArrayList<Integer>();
        this.length = content.length();
    }

    public String getContent() {
        return content;
    }

    public void init(String main){
        Pattern pattern = Pattern.compile(content);
        Matcher matcher = pattern.matcher(main);
        while (matcher.find()){
            this.occurences.add(matcher.start());
        }
    }

    public int getLength() {
        return length;
    }

    public ArrayList<Integer> getOccurences() {
        return occurences;
    }
}
