package codejam.solutions._2020.qualifications.esab_atad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private static final Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        byte testCases = reader.nextByte();
        for(byte testCase=1; testCase<=testCases; testCase++){
            Condition condition = parseCondition();
            Result solvedCase = solveCase(condition);
            printSolution(solvedCase);
        }
    }

    private static Condition parseCondition(){
        return new Condition(reader.nextByte());
    }

    private static Result solveCase(Condition condition) {
        byte[] array = new byte[condition.arrayLength];
        List<Byte> asymmetricPositions = new ArrayList<>(condition.arrayLength);
        List<Byte> symmetricPositions = new ArrayList<>(condition.arrayLength);

        byte iterator = 1;
        skipRequest();

        while(true){
            for(int i=0; i<4; i++) {
                if (isPairSymmetric(iterator, (byte) (condition.arrayLength-iterator+1), array)) symmetricPositions.add(iterator);
                else asymmetricPositions.add(iterator);
                if(iterator++==condition.arrayLength/2) return new Result(array);
            }

            processPositions(asymmetricPositions, array);
            processPositions(symmetricPositions, array);
        }
    }

    private static void printSolution(Result result) {
        for(byte b: result.array){
            System.out.print(b);
        }
        System.out.println();
        char response=reader.next().charAt(0);
        if(response=='N') System.exit(0);
    }

    private static class Condition{
        byte arrayLength;

        public Condition(byte arrayLength) {
            this.arrayLength = arrayLength;
        }
    }

    private static class Result {
        byte[] array;

        public Result(byte[] array) {
            this.array = array;
        }
    }

    private static void sendMessage(String message) {
        System.out.println(message);
    }

    private static boolean isPairSymmetric(byte firstPosition, byte secondPosition, byte[] arrayToStoreValues) {
        sendMessage(String.valueOf(firstPosition));
        byte value1=reader.nextByte();
        sendMessage(String.valueOf(secondPosition));
        byte value2=reader.nextByte();

        arrayToStoreValues[firstPosition-1]=value1;
        arrayToStoreValues[secondPosition-1]=value2;

        return value1==value2;
    }

    private static void processPositions(List<Byte> positions, byte[] array) {
        if(positions.size() == 0){
            //Skip the request to make sure we don't mess up iterations
            skipRequest();
            return;
        }

        sendMessage(String.valueOf(positions.get(0)));
        byte newValue=reader.nextByte();

        if(array[positions.get(0)-1]==newValue) return;

        for(Byte position: positions){
            array[position-1]= (byte) ((array[position-1]+1)%2);
            array[array.length-position]=(byte) ((array[array.length-position]+1)%2);
        }
    }

    private static void skipRequest(){
        sendMessage("1");
        reader.nextByte();
    }
}
