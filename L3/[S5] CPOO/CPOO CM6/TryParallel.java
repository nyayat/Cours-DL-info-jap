package concur;
//on remplit une table de booleéns
// results[i] est true si i est premier


//technique 1: on utilise un workStealingPool pour gérer le paralelisme, 
//et on exécute le test pour chaque i séparemment dans un objet 
// PrimeTester tout simple

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TryParallel {

	public static void main(String[] args) throws InterruptedException {

		var exec = Executors.newWorkStealingPool(16); 
//		essayez de changer le degré de parallelisme (1,2,4 etc) 
//		et voir lequel est le plus rapide. Ou appeler 
//		newWorkStealingPool() – parallelisme par défaut
		
		var results = new boolean[1000000];
//		long startTime = System.nanoTime(); //si on veut mesurer le temps d'exécution
		for (int i=0;i<1000000;i++) 
				exec.execute(new PrimeTester(i,results));
		
		exec.shutdown();	
		var ok=exec.awaitTermination(100000,TimeUnit.MILLISECONDS);
//		long endTime = System.nanoTime();
//		System.out.println((endTime - startTime) / 1000000);
		System.out.println(ok?"Finished":"Timeout");
		
		System.out.println(results[4643]); //devrait etre true
		System.out.println(results[4645]); //devrait etre false
		}	

	}
