import java.lang.*;
import java.util.*;

public class TestAssignment {
    static HashMap<String,Integer[][]> map = new HashMap<>();
    static String formula;
    static Integer[][] megaResult = null;
    static ArrayList<String> sing = new ArrayList<>();
    static ArrayList<String> nameMatrix = new ArrayList<>();
    public static void main(String[] args) {

        createMatrixAndFormula();
        fillListFromFormula();
        useFormula();
        String result = findResult();

    }

    private static String findResult(){
        String string = "";
        for(Map.Entry<String,Integer[][]> map : map.entrySet()){
            if(string.length()<map.getKey().length()){
                string=map.getKey();
            }
        }

        return string;
    }
    private static void createMatrixAndFormula(){
        Scanner input = new Scanner(System.in);
        boolean emptyLine = true;
        String line;
        String keyMatrix = null;
        Integer[][] integersMatrices = null;

        while (emptyLine){

            line = input.nextLine();
            if(line.equals("")){
                emptyLine = false;
            }
            else if(line.substring(0,1).matches("[A-Z]")){


                try {
                    keyMatrix = line.substring(0, 1);
                    if(map.containsKey(keyMatrix)){
                        throw new IllegalArgumentException("this matrix same name");
                    }
                    line=line.substring(3,line.length()-1);
                    String[] semicolon = line.split(";");
                    String[][] space = new String[semicolon.length][semicolon[0].split(" ").length];
                    for(int i=0;i<semicolon.length;i++){
                        space[i] = semicolon[i].trim().split(" ");
                    }
                    integersMatrices = new Integer[space.length][space[0].length];
                    for(int y = 0;y<space.length;y++){
                        for (int x = 0;x<space[0].length;x++){
                            integersMatrices[y][x] = Integer.parseInt(space[y][x]);
                        }
                    }
                    map.put(keyMatrix,integersMatrices);

                }catch (Exception e) {
                    throw new IllegalArgumentException ("Can't read matrix.");
                }


            }
            else {
                throw new IllegalArgumentException ("Name need Uppercase english letter");
            }



        }
        formula = input.nextLine();

    }
    private static void fillListFromFormula(){
        for(int i =0;i<formula.length();i++){
            if(i%2==0){
                nameMatrix.add(formula.substring(i,i+1));
            }
            else {
                sing.add(formula.substring(i,i+1));
            }
        }
    }
    private static void useFormula(){
            while (sing.contains("*")) {
                int i = sing.indexOf("*");
                Integer[][] result = null;
                Integer[][] test1 =  copy(map.get(nameMatrix.get(i)));
                Integer[][] test2 =  copy(map.get(nameMatrix.get(i+1)));
                multiplication(test1,test2);
                result = copy(megaResult);
                map.put(nameMatrix.get(i)+"*"+nameMatrix.get(i+1),result);
                sing.remove(i);
                nameMatrix.set(i,nameMatrix.get(i)+"*"+nameMatrix.get(i+1));
                nameMatrix.remove(i+1);
            }
            while (sing.contains("+")) {
                int i = sing.indexOf("+");
                Integer[][] result = copy(summing(copy(map.get(nameMatrix.get(i))),copy(map.get(nameMatrix.get(i+1)))));
                map.put(nameMatrix.get(i)+"+"+nameMatrix.get(i+1),result);
                sing.remove(i);
                nameMatrix.set(i,nameMatrix.get(i)+"+"+nameMatrix.get(i+1));
                nameMatrix.remove(i+1);
            }
            while (sing.contains("-")) {
                int i = sing.indexOf("-");
                Integer[][] result = copy(minus(copy(map.get(nameMatrix.get(i))),copy(map.get(nameMatrix.get(i+1)))));
                map.put(nameMatrix.get(i)+"-"+nameMatrix.get(i+1),result);
                sing.remove(i);
                nameMatrix.set(i,nameMatrix.get(i)+"-"+nameMatrix.get(i+1));
                nameMatrix.remove(i+1);
            }
    }
    public static Integer[][] multiplication(Integer[][] matrix1, Integer[][] matrix2){
        Integer[][] result = null;
        int resultSumming= 0;
        try {

            if(matrix1.length==matrix2[0].length||matrix1[0].length==matrix2.length)
            {
                result = new Integer[matrix1.length][matrix2[0].length] ;
                    for (int y = 0; y < matrix1.length; y++) {
                        for (int x = 0; x < matrix2[0].length; x++) {
                            resultSumming = 0;
                            for (int i=0;i<matrix1[0].length;i++) {
                                resultSumming = resultSumming + (matrix1[y][i] * matrix2[i][x]);

                            }
                            result[y][x] = resultSumming;
                        }

                    }

            }

            else {
                throw new IllegalArgumentException ("Can't multiply this");
            }
        }catch (Exception e){
            throw new IllegalArgumentException ("Can't perform multiplication.");
        }
        megaResult = copy(result);
        return result;
    }
    public static Integer[][] summing (Integer[][] matrix1, Integer[][] matrix2) {
        Integer[][] result = null;
        try {

            if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
                result = new Integer[matrix1.length][matrix1[0].length] ;
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        result[y][x] = new Integer(matrix1[y][x]);
                    }
                }
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        result[y][x] = matrix1[y][x]+matrix2[y][x];
                    }
                }

            }
            else{
                Integer[][] newMatrix;
                newMatrix = new Integer[matrix1.length][matrix1[0].length] ;
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        newMatrix[y][x] = new Integer(matrix2[x][y]);
                    }
                }
                matrix2 = new Integer[newMatrix.length][newMatrix[0].length];
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        matrix2[y][x] = new Integer(newMatrix[y][x]);
                    }
                }
                    return summing(matrix1,matrix2);
                }

        }catch (Exception e){

        }

        return result;
    }
    public static Integer[][] minus (Integer[][] matrix1, Integer[][] matrix2) {
        Integer[][] result = null;
        try {

            if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
                result = new Integer[matrix1.length][matrix1[0].length] ;
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        result[y][x] = new Integer(matrix1[y][x]);
                    }
                }
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        result[y][x] = matrix1[y][x]-matrix2[y][x];
                    }
                }

            }
            else{
                Integer[][] newMatrix;
                newMatrix = new Integer[matrix1.length][matrix1[0].length] ;
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        newMatrix[y][x] = new Integer(matrix2[x][y]);
                    }
                }
                matrix2 = new Integer[newMatrix.length][newMatrix[0].length];
                for (int y = 0; y < matrix1.length; y++) {
                    for (int x = 0; x < matrix1[0].length; x++) {
                        matrix2[y][x] = new Integer(newMatrix[y][x]);
                    }
                }
                return summing(matrix1,matrix2);
            }

        }catch (Exception e){

        }

        return result;
    }
     static Integer[][] copy(Integer[][] matrix){
        Integer[][] result = new Integer[matrix.length][matrix[0].length] ;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                result[y][x] = new Integer(matrix[y][x]);
            }
        }
        return  result;
    }
}
