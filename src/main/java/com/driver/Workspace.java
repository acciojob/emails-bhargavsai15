package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar=new ArrayList<>(); // Stores all the meetings

    private ArrayList<LocalTime> startTime=new ArrayList<>();

    private ArrayList<LocalTime> endTime=new ArrayList<>();


    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId);
        super.inboxCapacity=Integer.MAX_VALUE;
    }

    @Override
    public int getInboxCapacity() {
        return super.inboxCapacity;
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(new Meeting(meeting.getStartTime(),meeting.getEndTime()));
    }


    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        for(int i=0;i<calendar.size();i++){
            startTime.add(calendar.get(i).getStartTime());
            endTime.add(calendar.get(i).getEndTime());
;        }
        Collections.sort(startTime);
        Collections.sort(endTime);

        int max=0;

        for(int i=0;i<startTime.size()-1;i++){
            LocalTime end=endTime.get(i);
            LocalTime start=startTime.get(i);
            if(end.equals(start) || end.compareTo(start)>0 || end.compareTo(start)<0){
                max++;
            }
        }
        if(endTime.get(endTime.size()-2).compareTo(startTime.get(startTime.size()-1))<0 || endTime.get(endTime.size()-2).equals(startTime.size()-1)){
            max++;
        }
        return max;
    }
}

