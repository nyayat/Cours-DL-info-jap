// pour l'hierachie qui concerne le type de production de son on
// utilise une hierarchie de classes :
abstract class Instrument {
  protected String name;
  abstract public void play();
}

// ces deux dernieres classes ne sont  necessaires que si elles ont des proprietés
// et methodes (donc pas dans notre modelisation simple)
abstract class InstrumentMecanique extends Instrument {}
abstract class InstrumentElectronique extends Instrument {} // Les synthetiseurs


abstract class Cordes extends InstrumentMecanique {
  protected int numberOfStrings;
}


abstract class CordesFrappees extends Cordes {}
abstract class CordesPincees extends Cordes {}
abstract class CordesFrottees extends Cordes {}


abstract class Vent extends InstrumentMecanique {}
abstract class Cuivre extends Vent {}
abstract class Bois extends Vent {}

abstract class Percussion extends InstrumentMecanique {}
abstract class PercussionPeau extends Percussion {}
abstract class PercussionBois extends Percussion {}
abstract class PercussionMetal extends Percussion {}

// Pour l'aplification on utilise une hierarchie d'Interfaces :
// il ne serait pas possible d'heriter d'Amplification et d'Instrument
// si Amplification etait une classe

// utile seulement dans une modélisation plus complexe, s'il y a des methodes
interface Amplification {}

interface AmplificationElectrique extends Amplification {
  String typeDePrise();
}

// utile seulement dans une modélisation plus complexe, s'il y a des methodes
interface AmplificationMecanique extends Amplification {}

interface Clavier {
  // il serait aussi possible de déclarer comme public static final int,
  // mais le compilateur ne nous forcait pas à remplacer la valeur dans
  // une classe qui implemente Clavier.
  int nbTouches();
}


class Orgue extends Vent implements AmplificationMecanique, Clavier {
  public void play() {
    System.out.println("mmm");
  }
  public int nbTouches() {
    return 200;
  }
}
class Saxophone extends Bois implements AmplificationMecanique {
  // play()
}
class PianoSynthetiseur extends InstrumentElectronique implements AmplificationElectrique, Clavier {
  // play()
  // typeDePrise()
  // nbTouches()
}
class GuitareElectrique extends CordesPincees implements AmplificationElectrique {
  // play()
  // typeDePrise()
}
class Piano extends CordesFrappees implements AmplificationMecanique, Clavier {
  // play()
  // nbTouches()
}
