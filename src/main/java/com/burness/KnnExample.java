package com.burness;


import java.io.IOException;
import java.text.ParseException;
import smile.classification.KNN;
import smile.data.AttributeDataset;
import smile.data.NominalAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.validation.Precision;


/**
 * Created by burness on 15/11/10.
 */
public class KnnExample {
    public void run(){
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setDelimiter(",");
        parser.setResponseIndex(new NominalAttribute("class"),2);

        try {
            AttributeDataset data;
            data = parser.parse("/Users/burness/git_repository/JAVA_SMILE/src/main/resources/flower.csv");
            double[][] dataX = data.toArray(new double[data.size()][]);
            int[] dataY = data.toArray(new int[data.size()]);
            int[] label = new int[data.size()];
            int time=0;
            long startTime = System.currentTimeMillis();
            for(int k =8;k>=2;k--) {
                KNN<double[]> knn = KNN.learn(dataX, dataY,k);
                for (int i = 0; i < dataX.length; i++) {
                    label[i] = knn.predict(dataX[i]);
                }
                double trainError = new Precision().measure(dataY, label);
                System.out.println("Knn example precision:" + String.valueOf(trainError));
                time++;
            }
            long stopTime = System.currentTimeMillis();
            System.out.println("Each KNN take "+String.valueOf((stopTime-startTime)/(time*1.0)));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void  main(String[] args) throws IOException, ParseException {
        KnnExample knnExample = new KnnExample();
        knnExample.run();
    }
}
