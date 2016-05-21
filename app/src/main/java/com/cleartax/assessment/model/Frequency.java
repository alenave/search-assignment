package com.cleartax.assessment.model;

/**
 * Created by alenave on 22/05/16.
 */
public class Frequency implements Comparable<Frequency> {
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    String word;
    int frequency;

    public int compareTo(Frequency compareFruit) {

        int compareQuantity = ((Frequency) compareFruit).getFrequency();
        return compareQuantity - this.frequency;

    }
}
