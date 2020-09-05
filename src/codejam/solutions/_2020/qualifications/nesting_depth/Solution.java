package codejam.solutions._2020.qualifications.nesting_depth;

import java.util.Scanner;

public class Solution {
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
        return new Condition(scanner.nextLine().trim());
    }

    private static Result solveCase(Condition condition){
        byte leftParentheses = 0;
        byte prevNumber = 0;
        StringBuilder result = new StringBuilder(condition.numbers.length());

        for(byte position=0; position<condition.numbers.length(); position++){
            byte number = Byte.parseByte(String.valueOf(condition.numbers.charAt(position)));
            if(prevNumber==number){
                result.append(prevNumber);
                continue;
            }
            while(leftParentheses<number){
                result.append("(");
                leftParentheses++;
            }
            while(leftParentheses>number){
                result.append(")");
                leftParentheses--;
            }
            result.append(number);
            prevNumber=number;
        }
        while (leftParentheses>0){
            result.append(")");
            leftParentheses--;
        }
        return new Result(result.toString());
    }

    private static void printSolution(byte caseNumber, Result result){
        System.out.printf("Case #%d: %s\n", caseNumber, result.result);
    }

    private static class Condition{
        String numbers;

        public Condition(String numbers) {
            this.numbers = numbers;
        }
    }

    private static class Result {
        String result;

        public Result(String result) {
            this.result = result;
        }
    }
}
