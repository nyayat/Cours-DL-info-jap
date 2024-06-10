public class Test {
    public static void main(String[]a){
        //1.
        /*String[]t0=new String[0];
        TestIter<String> ti0=new TestIter<String>(t0);
        System.out.println(ti0.hasNext());  //false
        System.out.println(ti0.next());  //null
        System.out.println("----------");
        
        String[]t2={"0","1"};
        TestIter<String> ti2=new TestIter<String>(t2);
        System.out.println(ti2.hasNext());  //true
        System.out.println(ti2.next());  //1
        System.out.println("----------");*/
        
        //7.
        //3.-4.
        /*TabSet<Integer> tabS=new TabSet<Integer>(2);
        //System.out.println(tabS.size());  //2
        //System.out.println(tabS.isEmpty());  //true
        
        System.out.println(tabS.add(0));  //true
        //System.out.println(tabS.isEmpty());  //false
        //System.out.println(tabS.contains(0));  //true
        //System.out.println(tabS.contains(1));  //false
        System.out.println("----------");
        
        System.out.println(tabS.add(1));  //true
        //System.out.println(tabS.add(2));  //IllegalState --> false
        //System.out.println(tabS.add(null));  //NullPointer --> false
        System.out.println("----------");
        
        //5.
        //System.out.println(tabS.remove(2));  //false
        System.out.println(tabS.contains(0));  //true
        System.out.println(tabS.contains(1));  //true
        System.out.println("----------");
        /*System.out.println(tabS.remove(0));  //true
        System.out.println(tabS.remove(1));  //true
        System.out.println("----------");
        System.out.println(tabS.contains(0));  //false
        System.out.println(tabS.contains(1));  //false
        System.out.println("----------");*/
        
        /*tabS.clear();
        System.out.println(tabS.contains(1));  //false
        System.out.println(tabS.contains(0));  //false*/
        
        //6.
        TabSet<Integer> tabS1=new TabSet<Integer>(2);
        tabS1.add(0);
        tabS1.add(1);
                
        TabSet<Integer> tabS2=new TabSet<Integer>(3);
        tabS2.add(1);
        tabS2.add(2);
        
        TabSet<Integer> tabS3=new TabSet<Integer>(2);
        tabS3.add(0);
        
        /*System.out.println(tabS1.containsAll(tabS3));  //true : {0}C{0,1}
        System.out.println(tabS1.containsAll(tabS2));  //false : {1,2}!C{0,1}
        System.out.println("----------");*/
        /*System.out.println(tabS1.addAll(tabS3));  //false : 0 déjà dans {0,1}
        System.out.println(tabS2.addAll(tabS3));  //true : {0}!C{1,2} et |tabS2|>2
        System.out.println("----------");*/
        //System.out.println(tabS1.removeAll(tabS3));  //true : {0}C{0,1}
        //System.out.println(tabS2.removeAll(tabS3));  //false : {0}!C{1,2}
        //System.out.println(tabS2.removeAll(tabS1));  //false : {0,1}!C{1,2}
        
        /*tabS1.retainAll(tabS3);
        System.out.println(tabS1.contains(0));  //true
        System.out.println(tabS1.contains(1));  //false*/
        
        /*tabS2.retainAll(tabS3);
        System.out.println(tabS2.contains(1));  //false
        System.out.println(tabS2.contains(2));  //false*/
    }
}