package com.burness.randomIndex;

import java.util.Random;

/**
 * Created by burness on 15/11/11.
 */
public class RandomIndex {
    int dataSize;
    int trainSize;
    int testSize;
    int[] data;
    int[] trainIndex;
    int[] testIndex;
    public RandomIndex(){
    }
    public void setTrainSize(){
        this.trainSize = trainSize;
        int[] trainIndex = new int[trainSize];
    }

    public void setTestSize(){
        this.testSize = testSize;
        this.testIndex = new int[testSize];
    }

    public RandomIndex(int size,int trainSize,int testSize) {
        this.dataSize = size;
        this.trainSize = trainSize;
        this.testSize = testSize;
        this.data = new int[size];
        this.trainIndex = new int[trainSize];
        this.testIndex = new int[size - trainSize];
        int count = 0;
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        while (count < size - 1) {
            long seed2 = System.currentTimeMillis();
            random.setSeed(seed2);
            int num = random.nextInt(size);// [0,size-1]
            boolean flag = true;
            for (int j = 0; j < size; j++) {
                if (num == this.data[j]) {
                    flag = false;
                    break;
                }

            }
            if (flag) {
                this.data[count] = num;
                count++;
            }

        }
    }
    public int getTestSize(){
        return this.testSize;
    }
    public  int getTrainSize(){
        return this.trainSize;
    }
    public RandomIndex(int size, double testRatio) {
        this.dataSize = size;
        this.testSize = (int) (testRatio * size);
        this.trainSize = size - testSize;
        this.data = new int[size];
        this.trainIndex = new int[trainSize];
        this.testIndex = new int[testSize];
        int count = 0;
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        while (count < size - 1) {
            long seed2 = System.currentTimeMillis();
            random.setSeed(seed2);
            int num = random.nextInt(size);// [0,size-1]
            boolean flag = true;
            for (int j = 0; j < size; j++) {
                if (num == this.data[j]) {
                    flag = false;
                    break;
                }

            }
            if (flag) {
                this.data[count] = num;
                count++;
            }
        }
    }
    public int[] getRandomIndex(int size){
        if(size <=0){
            return null;
        }
        int count = 0;
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        while(count < size-1){
            long seed2 = System.currentTimeMillis();
            random.setSeed(seed2);
            int num = random.nextInt(size);// [0,size-1]
            boolean flag = true;
            for (int j = 0;j<size;j++){
                if(num == this.data[j]){
                    flag = false;
                    break;
                }

            }
            if(flag){
                this.data[count] = num;
                count++;
            }
        }
        return this.data;
    }
    public int[] getTrainIndex(){
        for(int i =0 ;i <this.trainIndex.length;i++){
            this.trainIndex[i] = data[i];
        }
        return this.trainIndex;
    }
    public int[] getTestIndex(){
        for(int i =this.trainSize;i<this.dataSize;i++){
            this.testIndex[i-trainSize] = this.data[i];
        }
        return this.testIndex;
    }
}
