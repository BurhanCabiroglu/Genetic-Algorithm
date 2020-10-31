package com.cabir;
import static java.lang.Math.*;

public class City{
    private String name;
    public double x;
    public double y;


    public City(String name, double x, double y){
        this.x=x;
        this.y=y;
        this.name=name;
    }

    public double distance(City city) {
         return  sqrt(pow((this.x-city.x),2)+pow((this.y-city.y),2));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toStringName(){
        return this.name+"("+"x"+","+y+")";
    }

    public String toString(){
        return this.name;
    }



}
