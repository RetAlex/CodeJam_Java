package codejam.solutions._2020.qualifications.parenting_partnering;

import java.util.*;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        byte testCases = scanner.nextByte();
        for(byte testCase=1; testCase<=testCases; testCase++){
            Condition condition = parseCondition();
            Result solvedCase = solveCase(condition);
            printSolution(testCase, solvedCase);
        }
    }

    private static Condition parseCondition(){
        Condition condition = new Condition(scanner.nextShort());
        for(short i=0; i<condition.activitiesAmount; i++)
            condition.addActivity(i, scanner.nextShort(), scanner.nextShort());
        return condition;
    }

    private static Result solveCase(Condition condition){
        Result result = new Result(condition.activitiesAmount);
        short cameronFinish = 0, jamieFinish = 0;
        for(Condition.ActivityTiming timing: condition.activities){
            if(cameronFinish > timing.startTime && jamieFinish > timing.startTime){
                result.setImpossible();
                return result;
            }
            if(cameronFinish <= timing.startTime){
                result.addAssignee(timing.id, "C");
                cameronFinish=timing.endTime;
                continue;
            }
            result.addAssignee(timing.id, "J");
            jamieFinish= timing.endTime;
        }
        return result;
    }

    private static void printSolution(byte caseNumber, Result result){
        StringBuilder sb = new StringBuilder(result.assignees.size());
        for(short i=0; i<result.assignees.size(); i++){
            sb.append(result.assignees.get(i));
        }
        System.out.printf("Case #%d: %s\n", caseNumber, sb.toString());
    }

    private static class Condition{
        short activitiesAmount;
        List<ActivityTiming> activities;

        public Condition(short activitiesAmount) {
            this.activitiesAmount = activitiesAmount;
            activities = new ArrayList<>(activitiesAmount);
        }

        public void addActivity(short id, short startTime, short endTime){
            ActivityTiming timing = new ActivityTiming(id, startTime, endTime);
            for(int i=0; i<activities.size();i++){
                if(activities.get(i).startTime>timing.startTime){
                    activities.add(i, timing);
                    return;
                }
            }
            activities.add(timing);
        }

        static class ActivityTiming{
            short startTime;
            short endTime;
            short id;

            public ActivityTiming(short id, short startTime, short endTime) {
                this.id = id;
                this.startTime = startTime;
                this.endTime = endTime;
            }
        }
    }

    private static class Result {
        Map<Short, String> assignees;

        public Result(short size) {
            this.assignees = new HashMap<>(size);
        }

        public void addAssignee(Short activityNumber, String assignee){
            assignees.put(activityNumber, assignee);
        }

        public void setImpossible(){
            assignees = new HashMap<>();
            assignees.put((short) 0, "IMPOSSIBLE");
        }
    }
}
