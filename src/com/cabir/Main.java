package com.cabir;



import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {



        ArrayList<City> tourmanager= new ArrayList<City>();
        City city = new City("a",60, 200);
        tourmanager.add(city);
        City city2 = new City("b",180, 200);
        tourmanager.add(city2);
        City city3 = new City("c",80, 180);
        tourmanager.add(city3);
        City city4 =new City("d",140, 180);
        tourmanager.add(city4);
        City city5 =new City("e",30, 160);
        tourmanager.add(city5);
        City city6 = new City("f",100, 160);
        tourmanager.add(city6);
        City city7 =new City("g",200, 160);
        tourmanager.add(city7);
        City city8 =new City("h",140, 140);
        tourmanager.add(city8);
        City city9 =new City("i",40, 120);
        tourmanager.add(city9);
        City city10 =new City("j",100, 120);
        tourmanager.add(city10);
        City city11 =new City("k",180, 100);
        tourmanager.add(city11);
        City city12 =new City("l",60, 80);
        tourmanager.add(city12);
        City city13 =new City("m",120, 80);
        tourmanager.add(city13);
        City city14 =new City("n",180, 60);
        tourmanager.add(city14);
        City city15 =new City("o",20, 40);
        tourmanager.add(city15);
        City city16 =new City("p",100, 40);
        tourmanager.add(city16);
        City city17 = new City("q",200, 40);
        tourmanager.add(city17);
        City city18 = new City("r",20, 20);
        tourmanager.add(city18);
        City city19 =new  City("s",60, 20);
        tourmanager.add(city19);
        City city20 =new City("t",160, 20);


        Population population=new Population(tourmanager,1000,0.3,10000,tourmanager.size());

        System.out.println("First State : "+ population.chromosomes.get(0).showGenome());

        Chromosome best=population.GeneticAlgorihm();

        System.out.println("latest : "+best.showGenome());



    }
}
