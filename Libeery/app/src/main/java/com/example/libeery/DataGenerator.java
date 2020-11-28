package com.example.libeery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

    public static List<Beer> generateData(String filter) {
        List<Beer> beerList = new ArrayList<>();

        beerList.add(new Beer("IPA", "North American Ale", "United States"));
        beerList.add(new Beer("Old Elephant Foot IPA", "North American Ale", "United States"));
        beerList.add(new Beer("Iris 1996", "Belgian and French Ale", "Belgium"));
        beerList.add(new Beer("Original Pils", "", "Germany"));
        beerList.add(new Beer("Maibock", "German Lager", "United States"));
        Collections.sort(beerList, Comparator.comparing(Beer::getName));
        Stream<Beer> s = beerList.stream().filter(beer -> beer.getName().toUpperCase().startsWith(filter.toUpperCase()));
        return s.collect(Collectors.toList());
    }
}
