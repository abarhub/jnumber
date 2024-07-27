package org.testnumber.jnumber.math;

import java.util.List;
import java.util.Objects;

public class Multiplication implements Expression {
    private final List<Operande> liste;

    public Multiplication(List<Operande> liste) {
        this.liste = Objects.requireNonNull(liste);
    }

    public List<Operande> getListe() {
        return liste;
    }
}
