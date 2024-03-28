import java.util.*;
public class Input {
    private static Input singletonInput;

    private Scanner input;
    private boolean quit;
    private Input() {
        input = new Scanner(System.in);
        quit = false;
    }

    public static Input getSingletonInput() {
        if (singletonInput == null) {
            singletonInput = new Input();
        }
        return singletonInput;
    }

    private void exit() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    public void setQuit(boolean b) {
        quit = b;
    }

    public boolean isQuit() {
        return quit;
    }
    
    // Quit isn't implemented yet as only scenario needed is if user wants to quit
    // Therefore, no point to account for a quit input
    public boolean getBoolean() {
        String s = "";
        boolean valid = false;
        while (!valid) {
            s = input.nextLine();
            if (s.toLowerCase().equals("t") || s.toLowerCase().equals("true")) {
                valid = true;
                return true;
            } else if (s.toLowerCase().equals("f") || s.toLowerCase().equals("false")) {
                valid = true;
                return false;
            } else {
                printTypeError();
            }
        }
        return false;
    }

    public int getInt() {
        int i = -1;
        boolean valid = false;
        while (!valid) {
            try {
                i = input.nextInt();
                // consume newline character
                input.nextLine();
                valid = true;
            } catch (Exception e) {
                String s = input.nextLine();
                if (s.toLowerCase().equals("q")) {
                    exit();
                }
                printTypeError();
                valid = false;
            }
        }
        return i;
    }

    //Grabs int with restriction on range input
    public int getInt(int min, int max) {
        int i = -1;
        boolean valid = false;
        while (!valid) {
            try {
                i = input.nextInt();
                // consume newline character
                input.nextLine();
                if (i < min || i > max) {
                    printOutOfRangeError();
                } else {
                    valid = true;
                }
            } catch (Exception e) {
                String s = input.nextLine();
                if (s.toLowerCase().equals("q")) {
                    exit();
                }
                printTypeError();
            }
        }
        return i;
        
    }

    //Grabs int that can be picked only from a set of numbers
    public int getInt(Set<Integer> hs) {
        int i = -1;
        boolean valid = false;
        while (!valid) {
            try {
                i = input.nextInt();
                // consume newline character
                input.nextLine();
                if (hs.contains(i)) {
                    valid = true;
                } else {
                    printNotInValidError();
                }
            } catch (Exception e) {
                String s = input.nextLine();
                if (s.toLowerCase().equals("q")) {
                    exit();
                }
                printTypeError();
            }
        }
        return i;
    }

    public String getString() {
        String s = input.nextLine();
        if (s.toLowerCase().equals("q")) {
            exit();
        }
        return s;
    }

    public String getString(Set<String> hs) {
        String s = "";
        boolean valid = false;
        while (!valid) {
            s = input.nextLine().toLowerCase();
            // consume newline character
            if (hs.contains(s)) {
                valid = true;
            } else {
                printNotInValidError();
            }
        }
        if (s.equals("q")) {
            exit();
        }
        return s;
    }

    private void printTypeError() {
        System.out.println("Error: Invalid Input Type");
    }

    private void printNotInValidError() {
        System.out.println("Error: Input Not One of Valid Inputs");
    }

    private void printOutOfRangeError() {
        System.out.println("Error: Input Out of Range");
    }

    private void printNoDuplicatesError() {
        System.out.println("Error: Duplicate Inputs Not Taken");
    }
}