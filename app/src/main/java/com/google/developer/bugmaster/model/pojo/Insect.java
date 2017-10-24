package com.google.developer.bugmaster.model.pojo;


import java.util.Comparator;

public final class Insect {

    //--> create method which create bitmap for insect details imgview

    private static final String TAG = Insect.class.getSimpleName();

    //SqLiteID
    int id;
    //Common name
    public String name;
    //Latin scientific name
    public String scientificName;
    //Classification order
    public String classification;
    //Path to image resource
    public String imageAsset;
    //1-10 scale danger to humans
    int dangerLevel;

    public Insect() {
    }

    public Insect(int id, String name, String scientificName, String classification, String imageAsset, int dangerLevel) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.classification = classification;
        this.imageAsset = imageAsset;
        this.dangerLevel = dangerLevel;
    }

    public Insect(String name, String scientificName, String classification, String imageAsset, int dangerLevel) {
        this.name = name;
        this.scientificName = scientificName;
        this.classification = classification;
        this.imageAsset = imageAsset;
        this.dangerLevel = dangerLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getClassification() {
        return classification;
    }

    public String getImageAsset() {
        return imageAsset;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setImageAsset(String imageAsset) {
        this.imageAsset = imageAsset;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public static class CommonNameComparator implements Comparator<Insect> {

        @Override
        public int compare(Insect insectOne, Insect insectTwo) {
            return insectOne.getName().compareTo(insectTwo.getName());
        }
    }

    public static class DangerLevelComparator implements Comparator<Insect> {

        @Override
        public int compare(Insect insectOne, Insect insectTwo) {
            if (insectOne.getDangerLevel() > insectTwo.getDangerLevel())
                return -1;

            else if (insectOne.getDangerLevel() < insectTwo.getDangerLevel())
                return 1;

            else return 0;
        }
    }
}
