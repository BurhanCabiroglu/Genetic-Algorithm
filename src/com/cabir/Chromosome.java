package com.cabir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Chromosome {

    private final ArrayList<City> genArrayList;
    private double fitness;

    public Chromosome(City... gens){
        genArrayList=new ArrayList<>();
        genArrayList.addAll(Arrays.asList(gens));
    }


    public ArrayList<City> Gen(){
        return genArrayList;
    }

    public City getGen(int index){
        return genArrayList.get(index);
    }

    public void addGen(City city){
        if(!this.genArrayList.contains(city)){
            genArrayList.add(city);
        }
    }

    public double totalDistance(){
        double total=0;
        for (int i = 0; i < size()-1 ; i++) {
            total+=genArrayList.get(i).distance(genArrayList.get(i+1));
        }
        return total;
    }


    public int size(){
        return this.genArrayList.size();
    }



    public Chromosome mutate(){
        Random random=new Random();
        int rand1=random.nextInt(size());
        int rand2=random.nextInt(size());

        City swapper1 = genArrayList.get(rand1);
        City swapper2 = genArrayList.get(rand2);


        ArrayList<City> copyList = new ArrayList<>(genArrayList);
        copyList.set(rand1,swapper2);
        copyList.set(rand2,swapper1);



        return new Chromosome(copyList.toArray(City[]::new));
    }



    public String showGenome(){
        StringBuilder stringBuilder=new StringBuilder();
        for (City c : genArrayList) {
            stringBuilder.append(c).append(" - ");
        }
        return stringBuilder.substring(0,stringBuilder.length()-2) + "FitnessVal= "+  getFitness() +"  TotalVal = "+totalDistance();
    }



    public static Chromosome crossingOver(Chromosome ch1,Chromosome ch2){

        int rand1 = (new Random()).nextInt(ch1.size());
        int rand2 = (new Random()).nextInt(ch2.size());

        ArrayList<City> ar = new ArrayList<>();
        //System.out.println(rand1 + " değeri," + rand2+" değeri");

        if(rand1 < rand2){
            for (int i = rand1; i < rand2+1 ; i++) {
                ar.add(ch1.getGen(i));
            }
            for (int i = 0; i < ch1.size() ; i++) {
                if(!ar.contains(ch2.getGen(i))){
                    ar.add(ch2.getGen(i));
                }
            }

        }
        else{
            for (int i = rand2; i < rand1+1 ; i++) {
                ar.add(ch1.getGen(i));
            }
            for (int i = 0; i < ch1.size() ; i++) {
                if(!ar.contains(ch2.getGen(i))){
                    ar.add(ch2.getGen(i));
                }
            }
        }


        return new Chromosome(ar.toArray(City[]::new));

    }


    public boolean isSame(Chromosome chn){
        boolean val=true;

        for (int i = 0; i < size() ; i++) {
            if(!getGen(i).getName().equals(chn.getGen(i).getName())){
                val=false;
                break;
            }
        }

        return val;
    }


    public double getFitness(){
        return (1/totalDistance());
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
