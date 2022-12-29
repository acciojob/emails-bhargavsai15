package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }


    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(this.password.equals(oldPassword)){
            if(newPassword.length()>=8 && checkCaseAndDigit(newPassword) && checkSpecialChar(newPassword)){
                this.password=newPassword;
            }
        }
    }

    public static boolean checkCaseAndDigit(String pass){
        int flag1=0,flag2=0,flag3=0;
        for(int i=0;i< pass.length();i++){
            char c=pass.charAt(i);
            if(Character.isUpperCase(c)){
                flag1=1;
            }
            if(Character.isLowerCase(c)){
                flag2=1;
            }
            if(Character.isDigit(c)){
                flag3=1;
            }
            if(flag1==1 && flag2==1 && flag3==1){
                return  true;
            }
        }
        return  false;
    }

    public  static  boolean checkSpecialChar(String pass){
        for(int i=0;i<pass.length();i++){
            char c=pass.charAt(i);
            if(!Character.isDigit(c) && !Character.isLetter(c)){
                return true;
            }
        }
        return  false;
    }


}
