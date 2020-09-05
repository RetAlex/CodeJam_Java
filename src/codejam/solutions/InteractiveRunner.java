package codejam.solutions;

import java.io.BufferedOutputStream;
import java.util.Scanner;

public class InteractiveRunner {
    private static final Scanner reader = new Scanner(System.in);
    private static final BufferedOutputStream writer = new BufferedOutputStream(System.out);

    public static void main(String[] args){
        byte testCases = Byte.parseByte(reader.nextLine().trim());
        for(byte testCase=1; testCase<=testCases; testCase++){
            Condition condition = parseCondition();
            Result solvedCase = solveCase(condition);
            printSolution(testCase, solvedCase);
        }
    }

    private static Condition parseCondition(){
    }

    private static Result solveCase(Condition condition){

    }

    private static void printSolution(byte caseNumber, Result result){
        System.out.printf("Case #%d: \n", caseNumber);
    }

    private static class Condition{
    }

    private static class Result {

    }
}
