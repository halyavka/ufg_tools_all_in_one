package utils;

import java.util.ArrayList;
import java.util.List;


public class BooleanCombinationsGenerator {
    private static List<char[]> strings=new ArrayList<char[]>();
    private static List<boolean[]> bolValues=new ArrayList<boolean[]>();

    public static void main(String[] args) {
        boolAlgorithm(4);
    }
    public static List boolAlgorithmBinary(int num){
        if(strings!=null) strings.clear();
        for (int i = 0; i < Math.pow(2, num); i++) {
            String bin = Integer.toBinaryString(i);
            while (bin.length() < num)
                bin = "0" + bin;
            char[] chars = bin.toCharArray();
            strings.add(chars);
        }
        return strings;
    }
    public static List boolAlgorithm(int num){
        if(strings!=null) strings.clear();
        for (int i = 0; i < Math.pow(2, num); i++) {
            String bin = Integer.toBinaryString(i);
            while (bin.length() < num)
                bin = "0" + bin;
            char[] chars = bin.toCharArray();
            strings.add(chars);
        }
        if (!strings.isEmpty())bolValues=transformBolCharsToBolValues(strings);
        return bolValues;
    }
    private static List<boolean[]> transformBolCharsToBolValues(List<char[]> strings){
        if (bolValues!=null)bolValues.clear();
        for (int i = 0; i < strings.size(); i++) {
            char[] val=strings.get(i);
            boolean[] allBooleans=new boolean[val.length];
            for (int j = 0; j < val.length; j++) {
                char c=val[j];
                boolean b=c=='1'?true:false;
                allBooleans[j]=b;
            }
            bolValues.add(allBooleans);
        }
        return bolValues;

    }
}
