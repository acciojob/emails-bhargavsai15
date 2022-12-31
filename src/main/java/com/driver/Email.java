package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }


    public String getEmailId() {
        return this.emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals(newPassword)){
            if(newPassword.length()>=8 && isValid(newPassword)){
                this.password=newPassword;
            }
        }
    }

    public static boolean isValid(String pass){
        int flag1=0,flag2=0,flag3=0,flag4=0;
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
            if(!Character.isDigit(c) && !Character.isLetter(c)){
                flag4=1;
            }
        }
        return  flag1==1 && flag2==1 && flag3==1 && flag4==1;
    }


}
