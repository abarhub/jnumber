package org.testnumber.jnumber.math;

import java.util.List;

public class Polynome {

    private final List<Multiplication> liste;

    public Polynome(List<Multiplication> liste) {
        this.liste = liste;
    }

    public List<Multiplication> getListe() {
        return liste;
    }
}
