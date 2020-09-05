import java.util.Scanner;

public class Template {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        byte testCases = Byte.parseByte(scanner.nextLine().trim());
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
