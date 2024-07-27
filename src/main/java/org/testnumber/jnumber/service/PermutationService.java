package org.testnumber.jnumber.service;

import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.PermutationGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PermutationService {

    public <T> Stream<List<T>> getPermutations(List<T> liste) {
        return Generator.permutation(liste)
                .simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL)
                .stream();
    }

}
