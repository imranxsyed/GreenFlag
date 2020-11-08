package com.isyed.greenflagv2.utilities;

public class EmailPasswordValidations {
    //cannot be declared
    private void EmailPasswordValidations(){}


    /**
     * Check if input contains at least one upper case character
     * @param input
     * @return false if input is null or empty,
     *          true if the password contains at least one upper case character
     */
    public static boolean checkForUppercase(String input){

        if (null == input || input.isEmpty()){
            return false;
        }

        for (int i =0; i< input.length(); i++){

            if (Character.isUpperCase(input.charAt(i))){
                return true;
            }
        }
        return false;

    }

    /**
     * Check if input contains at least one upper lower character
     * @param input
     * @return false if input is null or empty,
     *          true if the password contains at least one upper lower character
     */
    public static boolean checkForLowercase(String input){

        if (null == input || input.isEmpty()){
            return false;
        }

        for (int i =0; i< input.length(); i++){

            if (Character.isLowerCase(input.charAt(i))){
                return true;
            }
        }
        return false;

    }

    /**
     * Check if input contains at least one number
     * @param input
     * @return false if input is null or empty,
     *          true if the password contains at least one number
     */
    public static boolean checkForNumber(String input){

        if (null == input || input.isEmpty()){
            return false;
        }

        for (int i =0; i< input.length(); i++){

            if (Character.isDigit(input.charAt(i))){
                return true;
            }
        }
        return false;

    }

    /**
     * Check is password has at least 8 character
     * @param password
     * @return
     */
    public static boolean hasAtleastEightLetter(String password){
        if (null == password || password.isEmpty()){
            return false;
        }
        return password.length() >= 8 ? true : false;
    }

    /**
     * Check password criteria
     * 1) Has atleast one upper case
     * 2) Has atleast one lower case
     * 3) Has atleast one number
     * 4) Has atleast 8 characters
     * @param password
     * @return
     */
    public static boolean checkPasswordCritria(String password){
        if (null == password || password.isEmpty()){
            return false;
        }
        boolean hasUppercase  = checkForUppercase(password);
        boolean hasLowercase  = checkForLowercase(password);
        boolean hasEightLetter = hasAtleastEightLetter(password);
        boolean hasNumber = checkForNumber(password);

       return  hasUppercase && hasLowercase && hasEightLetter && hasNumber;
    }

    /**
     * check if email is valid
     * @param email
     * @return false if email is empty or null
     */
    public static boolean isEmailValid(String email){

        if (null == email || email.isEmpty()){
            return false;
        }

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



}
