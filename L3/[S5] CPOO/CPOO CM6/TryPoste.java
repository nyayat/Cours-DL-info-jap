package concur;
//Oracle
//Alice et Bob communiquent via une boite de type Box (faite à la main)
//Claire et David communiquent via une boite de type SynchronousQueue (de l'API)

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TryPoste {

	public static void main(String[] args) {
		Box boite=new Box();
		Producer1 Alice=new Producer1(boite,
				List.of("bonjour","mon","cher","ami"));
		Consumer1 Bob=new Consumer1(boite);
		new Thread(Alice).start();
		new Thread(Bob).start();
		
		
//		BlockingQueue<String> boite2 = new SynchronousQueue<String>();
//		Producer2 Claire = new Producer2(boite2, 
//				List.of("bonjour", "mon", "cher", "ami"));
//		Consumer2 David = new Consumer2(boite2);
//		new Thread(Claire).start();
//		new Thread(David).start();
	}
}
