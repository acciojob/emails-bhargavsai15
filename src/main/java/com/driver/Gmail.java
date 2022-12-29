package com.driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {
    public Gmail(String emailId) {
        super(emailId);
    }

    class EmailId{
        public  String message;
        public  Date date;
        public  String senderId;

        public EmailId(String message, Date date, String senderId) {
            this.message = message;
            this.date = date;
            this.senderId = senderId;
        }
    }

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    int inboxSize=0,trashSize=0;
    ArrayList<EmailId> Inbox =new ArrayList<>();
    ArrayList<EmailId> trash =new ArrayList<>();

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inboxSize>inboxCapacity) {
            trash.add(Inbox.remove(0));
            inboxSize--;
            trashSize++;
        }
        Inbox.add(new EmailId(message,date,sender));
        inboxSize++;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<Inbox.size();i++){
            if(Inbox.get(i).message.equals(message)){
                trash.add(Inbox.remove(i));
                trashSize++;
                inboxSize--;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.size()==0){
            return  null;
        }else{
            return Inbox.get(Inbox.size()-1).message;
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.size()==0){
            return null;
        }else{
            return Inbox.get(0).message;
        }
    }





    public int findMailsBetweenDates(Date start, Date end) throws ParseException {
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = sdf.parse(String.valueOf(start));
        Date date2 = sdf.parse(String.valueOf(end));

        for(int i=0;i<Inbox.size();i++){
            Date date3=Inbox.get(i).date;

            //date3>date1 and date3<date2
            if(date3.after(date1) || date3.equals(date1) && date3.before(date2)  || date3.equals(date2)){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return  this.inboxSize;
    }
}
