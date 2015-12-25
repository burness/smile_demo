package com.burness;

import smile.classification.LogisticRegression;
import smile.data.AttributeDataset;
import smile.data.NominalAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.validation.Precision;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by burness on 15/11/10.
 */
public class LRExample {
    public void run(){
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setDelimiter(",");
        parser.setResponseIndex(new NominalAttribute("class"),2);

        try{
            AttributeDataset data;
            data = parser.parse("/Users/burness/git_repository/JAVA_SMILE/src/main/resources/flower.csv");
            double[][] dataX = data.toArray(new double[data.size()][]);
            int[] dataY = data.toArray(new int[data.size()]);
            int[] label = new int[data.size()];
            LogisticRegression.Trainer trainer = new LogisticRegression.Trainer();
            long startTime=System.currentTimeMillis();
            int time =0;
            for(double lambda =0.0;lambda<1;lambda+=0.1){
                trainer.setRegularizationFactor(lambda);
                LogisticRegression logisticRegression = trainer.train(dataX, dataY);
                for(int i =0; i<dataX.length;i++){
                    label[i] = logisticRegression.predict(dataX[i]);
                }
                double trainError = new Precision().measure(dataY,label);
                System.out.println("LR Example precision:" + String.valueOf(trainError));
                time++;
            }
            long stopTime = System.currentTimeMillis();
            System.out.println("each LR Regression takes "+String.valueOf((stopTime-startTime)/(time*1.0))+"ms");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static  void main(String[] args) throws IOException,ParseException{
        LRExample lrExample = new LRExample();
        lrExample.run();
    }
}
