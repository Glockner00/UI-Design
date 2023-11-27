package com.example.passwordstrenghtmeter;

/**
 * A class for the default validation logic.
 */
public class DefaultValidator implements StrengthValidator {
    @Override
    public boolean ValidateLength(String password) {
        return (password.length()>10);
    }
    @Override
    public boolean ValidateSpecialCharacters(String password){
        String specialChars = "/@#$%&.,;:)(=?!";
        int requiredCount = 2;
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (specialChars.contains(String.valueOf(password.charAt(i)))) {
                count++;
                if (count >= requiredCount) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean ValidateCapLetters(String password) {
        for (int i=0; i<password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String ErrorMessage() {
        return "Password should at least ten characters long with two special characters and one capital character.";
    }
}
