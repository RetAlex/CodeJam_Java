package codejam.solutions._2020.qualifications;

import java.io.IOException;
import java.util.Scanner;

class Vestigium {
    public static void main(String[] args) throws IOException {
        parseCondition();
    }

    private static void parseCondition() throws IOException {
        Scanner scanner = new Scanner(System.in);
        byte testCases = scanner.nextByte();

        for(byte testCase = 0; testCase<testCases; testCase++){
            byte size = scanner.nextByte();
            Condition condition = new Condition(size);
            for(byte i=0; i<size; i++){
                for(byte j=0; j<size; j++)
                    condition.array[i][j]=scanner.nextByte();
            }

            Result result = processTask(condition);
            System.out.printf("#%d: %d %d %d\n", testCase+1, result.trace, result.invalidRows, result.invalidColumns);
        }
    }

    private static Result processTask(Condition condition){
        byte invalidRows=0, invalidColumns = 0;
        int trace = 0;
        for(byte iterator1 = 0; iterator1 <condition.size; iterator1++){
            byte[] support = new byte[condition.size+1];
            byte[] support2 = new byte[condition.size+1];
            boolean rowIncremented = false, columnIncremented = false;
            for(byte iterator2 = 0; iterator2 <condition.size; iterator2++){
                byte element = condition.array[iterator1][iterator2];
                byte element2 = condition.array[iterator2][iterator1];
                if(!rowIncremented) {
                    if (support[element] != 0) {
                        invalidRows++;
                        rowIncremented=true;
                    }
                    support[element] = 1;
                }
                if(!columnIncremented) {
                    if (support2[element2]!=0) {
                        invalidColumns++;
                        columnIncremented=true;
                    }
                    support2[element2] = 1;
                }
                if(iterator1==iterator2) trace+=element;
            }
        }
        return new Result(invalidRows, invalidColumns, trace);
    }

    private static class Result{
        byte invalidRows, invalidColumns;
        int trace;

        public Result(byte invalidRows, byte invalidColumns, int trace) {
            this.invalidRows = invalidRows;
            this.invalidColumns = invalidColumns;
            this.trace = trace;
        }
    }

    private static class Condition{
        byte size;
        Byte[][] array;

        public Condition(byte size){
            this.size = size;
            this.array = new Byte[size][size];
        }

        public void addRow(byte number, Byte[] row){
            this.array[number] = row;
        }
    }
}
