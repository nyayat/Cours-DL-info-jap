public class Main {
    public static void main(String[]a){
        //Spationef u1=new NavetteSpatiale();  //NavetteSpatiale abstract
        NavetteSpatiale u2=new NavetteDiscovery();  //hérédité
        Propulsion u3=new NavetteDiscovery();  //hérédité
        //NavetteSpatialeRusse u4=new NavetteDiscovery();  //pas d'hérédité
        Spationef u5=new NavetteSpatialeRusse();  //hérédité
        SatelliteMeteo u6=new SatelliteMeteo();
        System.out.println(u2.equipageMax());  //7
        //System.out.println(u3.equipageMax());
            //type déclératif propulsion, qui ne possède pas de champ équipageMax
        System.out.println(u5.equipageMax());  //0 de Russe
        System.out.println(u6.equipageMax());  //0 de Spationef
        System.out.println(u2.typeSpationef());  //Discovery
        System.out.println(((NavetteSpatialeAmericaine)u2).typeSpationef());
                                                    //Discovery (dynamic binding)
        System.out.println(u3.typePropulsion()); //propre
        //System.out.println(u6.typePropulsion());
            //pas de champ Propulsion dans Satellite
        //NavetteSpatiale u7=u5;  //Spationef pas convertissable en NavetteSpatiale
        //SatelliteMeteo u8=(SatelliteMeteo)u5;
            //NavSpRusse ne peut pas être cast en Satellite
        NavetteSpatialeRusse u9=(NavetteSpatialeRusse)u5;  //Russe redéf Spationef
        Spationef u10=u2;  //NavSp redéf Spationef
        //Spationef u11=u3;  //Propulsion ne redéfinit pas méthode de Spationef
        Spationef u12=(NavetteSpatiale)u3;
        //upcasting Propulsion en NavSp possible grâce au type effectif Discovery
        
    }
}
