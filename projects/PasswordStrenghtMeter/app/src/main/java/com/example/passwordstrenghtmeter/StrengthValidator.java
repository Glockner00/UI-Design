package com.example.passwordstrenghtmeter;
public interface StrengthValidator {
    boolean ValidateLength(String password);
    boolean ValidateSpecialCharacters(String password);
    boolean ValidateCapLetters(String password);
    String ErrorMessage();


}
