package darshan.thakkar.practical.practicals.practical_1;

import java.util.Scanner;

public class Practical_1 {
    public static void main(String[] args) {
        String userInputString = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String in camel case:");
        userInputString = sc.next();
        System.out.println("---------------  Camel to Snake Example ------------------");
        System.out.println("Entered String : " + userInputString);
        System.out.println("Converted String : " + camelToSnake(userInputString));
        System.out.println("----------------------------------------------------------");
        System.out.println("Enter a String in snake case:");
        userInputString = sc.next();
        System.out.println("---------------  Snake To Camel Example ------------------");
        System.out.println("Entered String : " + userInputString);
        System.out.println("Converted String : " + snakeToCamel(userInputString));
    }

    /**
     * It converts Camel Case String to Snake Case
     *
     * @param camelCaseString There is an assumption that given String will be a camel case String
     * @return Snake Case String
     * @author Darshan Thakkar
     */
    public static String camelToSnake(String camelCaseString) {
        char[] camelStringArray = camelCaseString.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < camelStringArray.length; i++) {
            if (Character.isUpperCase(camelStringArray[i])) {
                String s = String.valueOf(camelStringArray[i]).toLowerCase();
                builder.append("_" + s);
            } else {
                builder.append(camelStringArray[i]);
            }
        }
        return builder.toString();
    }

    /**
     * It converts Snake Case String to Camel Case
     *
     * @param snakeCaseString There is an assumption that given String will be a camel case String
     * @return Camel Case String
     * @author Darshan Thakkar
     */
    public static String snakeToCamel(String snakeCaseString) {
        boolean isPreviousElWasUnderscore = false;
        char[] snakeStringArray = snakeCaseString.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < snakeStringArray.length; i++) {
            if (snakeStringArray[i] == '_') {
                isPreviousElWasUnderscore = true;
            } else if (!isPreviousElWasUnderscore) {
                builder.append(snakeStringArray[i]);
            } else {
                builder.append(String.valueOf(snakeStringArray[i]).toUpperCase());
                isPreviousElWasUnderscore = false;
            }
        }
        return builder.toString();
    }
}
