package com.example.passwordstrenghtmeter;
public class DefaultValidator implements StrengthValidator {
    @Override
    public int Validate(String password) {
        if(password.isEmpty()){
            return 0;
        } else if(2<password.length() && password.length()<5){
            return 1;
        } else if (5<=password.length() && password.length()<9) {
            return 2;
        }else if (9<=password.length()) {
            return 3;
        }else{
            return 0;
        }
    }
}
