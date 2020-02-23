import java.util.Scanner;

public class FiniteAutomaton {

    public static int numStates;
    public static String[] language;
    public static int[][] state;
    public static boolean[] isFinal;

    public static int currentState;

    public static void main(String[] args){
        getAutomatonInfo();
        System.out.println();

        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter word to be checked by automaton: ");
        String word = scnr.next();
        scnr.nextLine();

        if (checkWord(word))
            System.out.println("Word accepted");
        else
            System.out.println("Word not accepted");
    }

    // Ask the user for the details of the finite automaton they wish to emulate
    public static void getAutomatonInfo(){
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter the number of states: ");
        numStates = scnr.nextInt();
        isFinal = new boolean[numStates];
        scnr.nextLine();

        System.out.print("Enter symbols of language separated by spaces: ");
        String inputLine = scnr.nextLine();
        language = inputLine.split(" ");

        state = new int[numStates][language.length];

        for (int i = 0; i < state.length; i++){
            for (int j = 0; j < state[0].length; j++){
                System.out.print("From state "+i+", "+"where does the symbol '"+ language[j]+"' take you?: ");

                int nextState = scnr.nextInt();
                scnr.nextLine();

                state[i][j] = nextState;
            }
        }

        for (int i = 0; i < isFinal.length; i++){
            System.out.print("Is state "+i+" a final state? (y/n): ");
            String answer = scnr.next();
            scnr.nextLine();

            isFinal[i] = answer.equals("y");
        }
    }

    // Method that uses information from finite automaton to determine is word is accepted
    public static boolean checkWord(String word){
        String[] wordSplit = word.split("");
        for (int i = 0; i < word.length(); i++){
            currentState = state[currentState][findIndex(wordSplit[i])];
        }

        return isFinal[currentState];
    }

    // method to find the index of a symbol in the given language
    public static int findIndex(String symbol){
        for (int i = 0; i < language.length; i++){
            if (language[i].equals(symbol))
                return i;
        }
        return -1;
    }
}