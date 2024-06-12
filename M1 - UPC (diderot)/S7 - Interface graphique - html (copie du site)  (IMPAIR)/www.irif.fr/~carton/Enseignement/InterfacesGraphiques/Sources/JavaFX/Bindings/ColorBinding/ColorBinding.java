// -*- coding: utf-8 -*-
// Time-stamp: <ColorBinding.java  11 nov 2019 15:36:13>

/**
 * Binding pour une couleur à partir de trois flottants
 * Le binding hérite de ObjectBinding<Paint> et non pas de
 * ObjectBinding<Color> car la propriété fill est de type
 * ObjectBinding<Paint>.
 * @author O. Carton
 * @version 1.0
 */

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.binding.ObjectBinding;

class ColorBinding extends ObjectBinding<Paint> {
    // Dépendances
    ObservableDoubleValue red;
    ObservableDoubleValue green;
    ObservableDoubleValue blue;
    // Constructeur
    public ColorBinding(ObservableDoubleValue red,
                        ObservableDoubleValue green,
                        ObservableDoubleValue blue) {
        // Appel au constructeur du parent absolument nécessaire
        super.bind(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    // Optionnel mais libère des ressources
    @Override
    public void dispose() {
	super.unbind(red, blue, green);
    }
    // Calcul de la valeur
    @Override
    protected Paint computeValue() {
        return Color.color(red.get(), green.get(), blue.get());
    }
}
