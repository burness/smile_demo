package com.burness;

import com.burness.randomIndex.RandomIndex;
import smile.classification.DecisionTree;
import smile.data.AttributeDataset;
import smile.data.NominalAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.math.Math;
import smile.validation.Precision;

import java.io.IOException;
import java.text.ParseException;


/**
 * Created by burness on 15/11/11.
 */
public class DTExample {
    public void run() throws IOException, ParseException {
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setDelimiter(",");
        parser.setResponseIndex(new NominalAttribute("class"),4);
        AttributeDataset data;
        try {
            data=parser.parse("/Users/burness/git_repository/JAVA_SMILE/src/main/resources/iris.data");
            double[][] dataX = data.toArray(new double[data.size()][]);
            int[] dataY = data.toArray(new int[data.size()]);
            double precision = 0.0;
            int time =0;
            for(int i =0;i<10;i++) {
                RandomIndex randomIndex = new RandomIndex(data.size(),0.3);
                int[] trainIndex = randomIndex.getTrainIndex();
                int[] testIndex = randomIndex.getTestIndex();
                double[][] trainX = Math.slice(dataX, trainIndex);
                int[] trainY = Math.slice(dataY, trainIndex);

                double[][] testX = Math.slice(dataX, testIndex);
                int[] testY = Math.slice(dataY, testIndex);

                int[] label = new int[randomIndex.getTestSize()];
                long startTime = System.currentTimeMillis();
                DecisionTree.Trainer trainer = new DecisionTree.Trainer(100);
                trainer.setSplitRule(DecisionTree.SplitRule.ENTROPY);
                DecisionTree dtTrained = trainer.train(trainX, trainY);
                for (int j = 0; j < testX.length; j++) {
                    label[j] = dtTrained.predict(testX[j]);
                }
                double eachPrecision = new Precision().measure(testY, label);
                System.out.println("params are: " + "ENTROPY" + " " + String.valueOf(i));
                System.out.println("DT Example precision: " + String.valueOf(eachPrecision));
                time++;
                precision+=eachPrecision;
                long stopTime = System.currentTimeMillis();
                System.out.print("A DT takes " + (stopTime - startTime) / (1.0 * time) + "ms");
            }
            System.out.print("\naver precsion: "+precision/(1.0*time));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static void  main(String[] args) throws IOException, ParseException {
        DTExample dtExample = new DTExample();
        dtExample.run();
    }
}
