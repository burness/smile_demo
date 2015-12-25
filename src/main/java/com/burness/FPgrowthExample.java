package com.burness;

import smile.association.FPGrowth;
import smile.association.ItemSet;

import java.util.List;

/**
 * Created by burness on 15/10/27.
 */
public class FPgrowthExample {
    public void run(){
        int[] itemsets = new int[7];
        //1-6分别代表牛奶、鸡蛋、面包、爆米花、薯片、啤酒、黄油
        for (int i =0;i<itemsets.length;i++){
            itemsets[i]=i+1;
        }
        int[][] transactions ={{1,2,3,5},{2,4,5,6},{2,3,5},{1,2,3,4,5,6},{1,3,6},{2,3,6},{1,3,5},{1,2,3,5,7},{1,2,5,7}};
        FPGrowth fpgrowth = new FPGrowth(transactions,3);
        List<ItemSet> aR=fpgrowth.learn();
        for(ItemSet tempItem:aR){
            String itemStr=new String();
            for(int i : tempItem.items){
                itemStr += "=>"+i;
            }
            System.out.println(itemStr);
        }
    }
    public static void main(String[] args){
        FPgrowthExample fPgrowthExample = new FPgrowthExample();
        fPgrowthExample.run();
    }
}
