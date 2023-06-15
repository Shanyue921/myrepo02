package edu.gatech.seclass;

public class MyString implements MyStringInterface{

    private String string;
    private boolean isValidArg(int arg){
        return arg>0 && arg <62 && isCoprime(arg,62);
    }

    private boolean isCoprime(int a,int b){     // Check if 'a' and 'b' are coprime
        int temp;
        while(b!=0){
            temp = b;
            b = a%b;
            a = temp;
        }
        return a == 1;
    }

    private char getCharacter(int value){
        // Convert numeric values back to characters based on their positions in the alphabet
        if (value >= 0 && value <= 9) {
            return (char) (value + '0'); // Numeric characters '0' to '9' have consecutive Unicode values
        } else if (value >= 10 && value <= 35) {
            return (char) (value + 'A' - 10); // Uppercase letters 'A' to 'Z' have consecutive Unicode values starting from 65
        } else if (value >= 36 && value <= 61) {
            return (char) (value + 'a' - 36); // Lowercase letters 'a' to 'z' have consecutive Unicode values starting from 97
        } else {
            return '?'; // Invalid values
        }
    }

    private String getDigName(char digit){
        switch (digit){
            case '0': return "Zero";
            case '1': return "One";
            case '2': return "Two";
            case '3': return "Three";
            case '4': return "Four";
            case '5': return "Five";
            case '6': return "Six";
            case '7': return "Seven";
            case '8': return "Eight";
            case '9': return "Nine";
            default: return "";

        }
    }

    @Override
    public String getString() {
        if(string == null)  return null;
        return string;
    }

    @Override
    public void setString(String string) {
        if(string.equals(easterEgg) || string.isEmpty() ){
            throw new IllegalArgumentException();
        }

        // 使用正则表达式匹配字母或数字
        boolean containsNumberOrLetter = string.matches(".*[a-zA-Z0-9].*");
        if(!containsNumberOrLetter) throw new IllegalArgumentException();

        this.string = string;
    }

    @Override
    public int countAlphabeticWords() {
        String currentString = getString();

        if (currentString == null) {
            throw new NullPointerException("Current string is null");
        }

        String[] words = currentString.split("[^a-zA-Z]+");   // 使用除字母之外的任意字符组合分隔字符串获取单词数组
        int count = 0;

        for (String word : words) {
            if (word.matches("^[a-zA-Z]+$")) {  // 使用正则表达式匹配只包含字母的单词
                count++;
            }
        }

        return count;
    }

    @Override
    public String encrypt(int arg1, int arg2) {
        String originalString = getString();
        StringBuilder encryptedString = new StringBuilder();
        int x = 0;

        if (originalString == null) throw new NullPointerException("Original string is null");
        if (!isValidArg(arg1) || arg2 < 1 || arg2 > 61){
            throw new IllegalArgumentException("Illegal argument(s)");
        }

        for(int i = 0 ; i < originalString.length() ; i++){
            char c = originalString.charAt(i);
            if(Character.isLetterOrDigit(c)){
                if(Character.isDigit(c)){
                    x = Character.getNumericValue(c);
                }else if(Character.isUpperCase(c)){
                    x = (int)c - 55;
                }else if(Character.isLowerCase(c)){
                    x = (int)c - 61;
                }
                int encryptedValue = (arg1 * x + arg2) % 62;
                char encryptedChar = getCharacter(encryptedValue);
                encryptedString.append(encryptedChar);
            }else{
                encryptedString.append(c);
            }
        }
        return encryptedString.toString();
    }

    @Override
    public void convertDigitsToNamesInSubstring(int firstPosition, int finalPosition) {
        StringBuilder newString = new StringBuilder(getString());

        if(getString() == null)  throw new NullPointerException("Original string is null");
        if(firstPosition < 1 || firstPosition > finalPosition)  throw new IllegalArgumentException("Invalid positions");
        if(finalPosition > getString().length())        throw new MyIndexOutOfBoundsException("FinalPosition is out of bounds");

        for(int i = firstPosition-1;i < finalPosition;i++){
            char c = newString.charAt(i);

            if(Character.isDigit(c)){
                String digitName = getDigName(c);
                newString.replace(i,i+1,digitName);
                finalPosition += digitName.length() - 1;  // Update finalPosition based on replacement length
                i += digitName.length() - 1;  // Adjust index to account for replacement
            }
        }

        setString(newString.toString());

    }
}
