package com.example.libeery.data;

import com.example.libeery.model.Beer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

    private static final DataGenerator INSTANCE = new DataGenerator();
    private List<Beer> beerList;

    private DataGenerator() {
        beerList = new ArrayList<Beer>();
        /*beerList.add(new Beer("IPA", "North American Ale", "United States"));
        beerList.add(new Beer("Old Elephant Foot IPA", "North American Ale", "United States"));
        beerList.add(new Beer("Iris 1996", "Belgian and French Ale", "Belgium"));
        beerList.add(new Beer("Original Pils", "", "Germany"));
        beerList.add(new Beer("Maibock", "German Lager", "United States"));*/
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
