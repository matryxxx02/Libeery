package com.example.libeery.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

    private static final DataGenerator INSTANCE = new DataGenerator();
    private final List<Beer> beerList;

    private DataGenerator() {
        beerList = new ArrayList<Beer>();
        beerList.add(new Beer("0", "IPA", "North American Ale", "United States", "Description de la biere bla bla bla bla bla bla bla bla bla bla bla"));
        beerList.add(new Beer("1", "Old Elephant Foot IPA", "North American Ale", "United States", "Description de la biere bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla"));
        beerList.add(new Beer("2", "Iris 1996", "Belgian and French Ale", "Belgium", "Description de la biere bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla"));
        beerList.add(new Beer("3", "Original Pils", "", "Germany", "Description de la biere bla bla bla bla biere bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla"));
        beerList.add(new Beer("4", "Maibock", "German Lager", "United States", "Description de la biere bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla"));
        Collections.sort(beerList, Comparator.comparing(Beer::getName));
    }

    public static DataGenerator getInstance(){
        return INSTANCE;
    }

    public List<Beer> getData() {
        return beerList;
    }

    public List<Beer> getData(String filter) {
        Stream<Beer> s = beerList.stream().filter(beer -> beer.getName().toUpperCase().startsWith(filter.toUpperCase()));
        return s.collect(Collectors.toList());
    }
}
