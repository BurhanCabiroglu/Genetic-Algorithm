package com.cabir;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

public class Population{
    public ArrayList<Chromosome> chromosomes;
    private ArrayList<City> cities;
    private int popSize;
    private double mutateRate;
    private int generation;
    private int eliteSize;

    // bir sürü parametreler
    public Population(ArrayList<City> cities,int popSize,double mutateRate,int generation,int eliteSize){
        super();
        chromosomes = new ArrayList<>();
        this.cities = new ArrayList<>(cities);
        this.popSize = popSize;
        this.generation = generation;
        this.mutateRate = mutateRate;
        this.eliteSize = eliteSize;

        CreatePopulation();


    }


    public Population(ArrayList<City> cities,int popSize,double mutateRate,int generation){
        this(cities,popSize,mutateRate,generation,0);
    }

    public void CreatePopulation(){
        while (chromosomes.size()<popSize){
            Chromosome chromosome=new Chromosome();
            Random random=new Random();
            while (chromosome.size()<cities.size()){
                chromosome.addGen(cities.get(random.nextInt(cities.size())));
            }
            chromosomes.add(chromosome);
        }
    }


    public void sortPopulation(){
        chromosomes.sort(new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
               return Double.compare(o2.getFitness(),o1.getFitness());
            }
        });
    }


    public ArrayList<Chromosome> Elimination(){
        ArrayList<Chromosome> selectionResult = new ArrayList<>();
        for (int i = 0; i < eliteSize; i++) {
            selectionResult.add(chromosomes.get(i));
        }
        Random random = new Random();
        while (selectionResult.size()<(2*eliteSize)){
            int randomInt = random.nextInt(chromosomes.size());
            if(!selectionResult.contains(chromosomes.get(randomInt))){
                selectionResult.add(chromosomes.get(randomInt));
            }
        }
        return  selectionResult;
    }

    public ArrayList<Chromosome> nextGeneration(){

        ArrayList<Chromosome> nextGeneration = new ArrayList<>();
        ArrayList<Chromosome> child=new ArrayList<>();
        ArrayList<Chromosome> selectedChromosomes = Elimination();
        Random random = new Random();

        while (child.size()<eliteSize){
            int randomInt = random.nextInt(selectedChromosomes.size());
            if(!child.contains(selectedChromosomes.get(randomInt))){
                child.add(selectedChromosomes.get(randomInt));
            }
        }
        random = new Random();

        ArrayList<Integer> integers =new ArrayList<>();
        while (integers.size()<selectedChromosomes.size()){
            int randomInt = random.nextInt(selectedChromosomes.size());
            if(!integers.contains(randomInt)){
                integers.add(randomInt);
            }
        }

        for (int i = 0; i < integers.size()-1 ; i++) {
            Chromosome chromosome =
                    Chromosome.crossingOver(selectedChromosomes.get(integers.get(i)),selectedChromosomes.get(integers.get(i+1)));
            child.add(chromosome);
        }

        for (int i = 0; i < child.size()*mutateRate ; i++) {
            Random random1=new Random();
            int randomInt=random1.nextInt(child.size());
            Chromosome mutant = child.get(randomInt).mutate();
            child.set(randomInt,mutant);
        }

        nextGeneration.addAll(child);
        nextGeneration.addAll(selectedChromosomes);

        return nextGeneration;

    }


    public synchronized Chromosome GeneticAlgorihm(){

        for (int i = 0; i < generation; i++) {
            sortPopulation();
            ArrayList<Chromosome> nextGen= nextGeneration();
            chromosomes.clear();
            chromosomes.addAll(nextGen);
        }


        sortPopulation();
        return chromosomes.get(0);
    }



}
