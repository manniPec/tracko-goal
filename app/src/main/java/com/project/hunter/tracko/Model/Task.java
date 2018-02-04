package com.project.hunter.tracko.Model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by mannis on 04-Feb-18.
 */

public class Task {
    private String mTaskName;
    private String mTargetUnitType;
    private long mTargetNumberOfUnits;
    private LocalDateTime mStartTime;
    private LocalDateTime mTargetFinishTime;
    private long mFinishedNumvberOfUnits;

    public Task(String taskName, String targetUnitType, long targetNumberOfUnits, LocalDateTime startTime, LocalDateTime targetFinishTime) {
        mTaskName = taskName;
        mTargetUnitType = targetUnitType;
        mTargetNumberOfUnits = targetNumberOfUnits;
        mStartTime = startTime;
        mTargetFinishTime = targetFinishTime;
        mFinishedNumvberOfUnits = 0;
    }

    public void upDateProgress(long finishedUnits) {
        mFinishedNumvberOfUnits = finishedUnits;
    }

    //returns 0-100; 0 - not critical 100- most critical
    public double getCriticality() {
        double criticallity;
        long totalTime = getTimeDiff(mStartTime, mTargetFinishTime);
        long remainingTime = getTimeDiff(LocalDateTime.now(), mTargetFinishTime);  //edit: check value, if now() is greater than target

        double timeFraction = ((double)remainingTime)/totalTime;
        double taskFraction = ((double)(mTargetNumberOfUnits - mFinishedNumvberOfUnits))/mTargetNumberOfUnits;

        if(taskFraction <= timeFraction) {
            criticallity = 0;
        }
        return criticallity;
    }

    //edit: check if it returns the complete seconds.
    private long getTimeDiff(LocalDateTime startTime, LocalDateTime endTime) {
        long seconds = startTime.until( endTime, ChronoUnit.SECONDS);
        return seconds;
    }
}


