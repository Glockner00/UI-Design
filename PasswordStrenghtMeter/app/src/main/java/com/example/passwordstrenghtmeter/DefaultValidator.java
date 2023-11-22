package com.example.passwordstrenghtmeter;
public class DefaultValidator implements StrengthValidator {
    @Override
    public int Validate(String password) {
        if(0<password.length() && password.length()<4){
            return -1;
        } else if (4<password.length() && password.length()<8) {
            return 0;
        }else{
            return 1;
        }
    }
}
