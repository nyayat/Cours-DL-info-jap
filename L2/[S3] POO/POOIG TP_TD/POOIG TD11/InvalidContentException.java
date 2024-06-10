import java.io.*;

//1.3
public class InvalidContentException extends IOException{
    InvalidContentException(){};
    InvalidContentException(String m){
        super(m);
    }
}