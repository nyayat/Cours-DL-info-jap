import java.awt.Color;
import java.util.LinkedList;

//2.
public class Card{
    private Card(){}
    
    enum Suit{
        HEART(Color.RED), SPADE(Color.BLACK), CLUB(Color.BLACK), DIAMOND(Color.RED);
        
        private final Color color;
        
        private Suit(Color c){
            color=c;
        }

        Color getColor(){
            return this.color;
        }
    }

    enum Value{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING;

        boolean isSpecialValue(){
            return this==JACK || this==QUEEN || this==KING;
        }
    }

    enum JokerColor{
        RED, BLACK
    }

    public static final class NormalCard extends Card{
        final Suit suit;
        final Value value;
        
        NormalCard(Suit s, Value v){
            suit=s;
            value=v;
        }
        
        Color getColor(){
            return suit.getColor();
        }

        boolean isSpecialValue(){
            return value.isSpecialValue();
        }
        
        public String toString(){
            return value+" de "+suit;
        }
    }
    
    public static final class Joker extends Card{
        final JokerColor color;
        
        Joker(JokerColor c){
            color=c;
        }
    }
    
    public static LinkedList<NormalCard> deck52(){
        LinkedList<NormalCard> res=new LinkedList<NormalCard>();
        for(Suit s: Suit.values()){
            for(Value v : Value.values())
                res.add(new NormalCard(s, v));
        }
        return res;
    }
    
    public static LinkedList<Card> deck54(){
        LinkedList<Card> res=new LinkedList<Card>();
        res.addAll(deck52());
        res.add(new Joker(JokerColor.BLACK));
        res.add(new Joker(JokerColor.RED));
        return res;
    }
    
    public static LinkedList<Card> deck108(){
        LinkedList<Card> res=new LinkedList<Card>();
        res.addAll(deck54());
        res.addAll(deck54());
        return res;
    }
}