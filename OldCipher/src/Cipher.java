import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cipher {
    private static final long[] randLongArray = { 192239503, 405756287,
        139640912, 531425448, 285373676, 471509940, 192727754, 426488242,
        536290454, 677170977, 558000953, 286329485, 822269005, 652711388,
        899307460, 227400118, 584848767, 525739559, 294007699, 719148160,
        779537911, 729759335, 684173501, 315529727, 658508957, 675021147,
        487827395, 789020807, 475118167, 655300943, 831489360, 940720241,
        786824943, 617909065, 687964188, 833600919, 365154123, 963885453,
        468168348, 541979384, 484878918, 338246973, 936442006, 766880870,
        881486694, 876051309, 297004127, 123827649, 399505963, 688804046,
        347119719, 352730898, 489845785, 625687020, 640325036, 997108022,
        553114861, 177645149, 329927573, 662800381, 956202029, 387091561,
        417768494, 322247097, 369814526, 318933259, 214459260, 563142480,
        371651036, 225350490, 611813816, 798532621, 837063223, 417261854,
        144875532, 731903253, 304470296, 698440790, 785659568, 638295857,
        711887664, 920995880, 666979986, 248482635, 476559723, 942165850,
        426635435, 437421193, 771487099, 842907714, 863616834, 705262224,
        510251387, 381598950, 529090873, 592751868, 713695761, 545808150,
        727102560, 275589233, 634184281, 868710350, 900143367, 494977271,
        996758276, 119159696, 746121493, 782371868, 184423511, 962103392,
        449692719, 931368097, 419277567, 887414840, 941903439, 999477253,
        306435940, 851692585, 117360711, 568914227, 656514374, 133524936,
        781982872, 936759589, 846828822, 913186385, 430300795, 872769051,
        648178717, 409517845, 340648987, 201972450, 500716632, 811941487,
        304197855, 764519596, 565687325, 525984292, 879643165, 937393168,
        597150457, 404180765, 561333020, 705193623, 181320954, 727816784,
        161832921, 337352133, 774104312, 685086692, 555658391, 784581120,
        619876871, 513126008, 421447537, 737728744, 755920856, 379140924,
        545364566, 812614214, 783612609, 153739848, 114592668, 316890840,
        772215584, 778133586, 791770692, 855588994, 470708410, 984356356,
        934483945, 171912975, 132581304, 845144723, 218194000, 360024289,
        676735964, 238229601, 294591651, 945084253, 372728295, 232978926,
        340877919, 445752487, 417888565, 426281779, 749376617, 621695388,
        604127246, 296488145, 830836945, 346109595, 569680009, 196871263,
        357857958, 609931021, 504316358, 636376095, 905766967, 834016070,
        502384905, 979360054, 973862788, 873774351, 475008455, 219909671,
        810069405, 977196646, 544120903, 623712272, 553340715, 460523353,
        946687437, 246027601, 454038869, 207511889, 154126716, 853845697,
        936732337, 331657108 };

    private static final String BASE_ALPHABET_UC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String BASE_ALPHABET_LC = BASE_ALPHABET_UC.toLowerCase();
    private static final ArrayList<String> ALPHABETS_UC = initializeAlphabets();
    private static final ArrayList<String> ALPHABETS_LC = stringArrayListToLowercase(ALPHABETS_UC);

    private ArrayList<String> inputLines = new ArrayList<String>();

    private ArrayList<String> words = new ArrayList<String>();

    private ArrayList<Integer> wordsNumbers = new ArrayList<Integer>();
    private ArrayList<Integer> wordsNumberOfLetters = new ArrayList<Integer>();

    private ArrayList<Integer> wordsIntermediateVariable = new ArrayList<Integer>();
    private ArrayList<Integer> wordsOrderNumbers = new ArrayList<Integer>();

    private ArrayList<String> outputLines = new ArrayList<String>();

    public Cipher(String filePath) {
        // Initialize our instance variables
        initializeCipher(filePath);
    }

    private static ArrayList<String> initializeAlphabets() {
        ArrayList<String> alphabets = new ArrayList<String>();
        String alphabet = "";
        char[] charAr;
        Random randGen;
        int[] randomIntArray;

        // Add the first four alphabets
        alphabets.add("OYDCTPWLNXVHUIAFZSREMKGJBQ");
        alphabets.add("EWLHAUYDSKJCPRTMQNIOFXBVGZ");
        alphabets.add("NXHROWMCTVYSGAEUQDLIPJFBKZ");
        alphabets.add("SJPFIDVUEBWMLTRCQOANHGKYXZ");

        // Add the remaining alphabets
        for (int i = 0; i < randLongArray.length; i++) {
            alphabet = "";
            charAr = new char[BASE_ALPHABET_UC.length()];
            randGen = new Random(randLongArray[i]);
            randomIntArray = new int[BASE_ALPHABET_UC.length()];

            // Generate an array of random numbers
            for (int j = 0; j < randomIntArray.length; j++) {
                randomIntArray[j] = randGen.nextInt(BASE_ALPHABET_UC.length());
            }

            // Eliminate any duplicate numbers so that the array contains only
            // unique values
            for (int j = 0; j < randomIntArray.length; j++) {
                for (int k = 0; k < randomIntArray.length; k++) {
                    if (randomIntArray[j] == randomIntArray[k] && j != k) {
                        randomIntArray[k] = randGen.nextInt(BASE_ALPHABET_UC.length());
                        j = 0;
                    }
                }
            }

            // Use the randomIntArray to randomly "shuffle" the base alphabet
            // into charAr
            for (int j = 0; j < randomIntArray.length; j++) {
                charAr[j] = BASE_ALPHABET_UC.charAt(randomIntArray[j]);
            }

            // Merge the contents of charAr into alphabet
            for (int j = 0; j < charAr.length; j++) {
                alphabet += charAr[j];
            }

            alphabets.add(alphabet);
        }

        return alphabets;
    }

    private static ArrayList<String> stringArrayListToLowercase(
        ArrayList<String> stringArrayList) {
        ArrayList<String> stringArrayListLC = new ArrayList<String>();
        String tempString = "";

        for (String string : stringArrayList) {
            tempString = string.toLowerCase();
            stringArrayListLC.add(tempString);
        }

        return stringArrayListLC;
    }

    private void initializeCipher(String filePath) {

        // Read the file
        Scanner sc = readFile(filePath);

        // Initialize lines
        if (sc == null) {
            inputLines.add(filePath);
        } else {
            while (sc.hasNextLine()) {
                inputLines.add(sc.nextLine());
            }
        }

        // Initialize words
        for (String s : inputLines) {
            words.addAll(splitIntoWords(s));
        }

        // Initialize wordsNumbers
        for (int i = 0; i < words.size(); i++) {
            wordsNumbers.add(i + 1);
        }

        // Initialize wordsNumberOfLetters
        for (String word : words) {
            wordsNumberOfLetters.add(word.length());
        }

        // Initialize wordsIntermediateVariable
        for (int i = 0; i < words.size(); i++) {
            wordsIntermediateVariable.add((((wordsNumbers.get(i) % ALPHABETS_UC.size()) 
                * inputLines.size()) + (wordsNumberOfLetters.get(i)) % words.size()));
        }

        // Initialize wordsOrderNumbers
        for (Integer i : wordsIntermediateVariable) {
            wordsOrderNumbers.add(i % ALPHABETS_UC.size());
        }
    }

    private Scanner readFile(String fileLocation) {

        Scanner myScanner = null;

        // Initialize myFile
        File myFile = new File(fileLocation);

        try {
            myScanner = new Scanner(myFile);
        }
        // If reading the file fails, exit the program
        catch (Exception exception) {
            myScanner = null;
        }

        return myScanner;
    }

    private ArrayList<String> splitIntoWords(String line) {
        ArrayList<String> arListWords = new ArrayList<String>();

        line = removePunctuation(line);

        for (String str : line.split(" ")) {
            arListWords.add(str);
        }

        return arListWords;
    }

    private String removePunctuation(String inputString) {
        // Replace any characters that aren't letters or spaces with "|"
        for (int i = 0; i < inputString.length(); i++) {
            if (!isLetter(inputString.charAt(i)) && inputString.charAt(i) != ' ')
                inputString = inputString.replace(inputString.charAt(i), '|');
        }
        inputString = inputString.replace("|", "");

        return inputString;
    }

    private boolean isUpperCase(char c) {
        if (c >= 'A' && c <= 'Z')
            return true;
        else
            return false;
    }

    private boolean isLowerCase(char c) {
        if (c >= 'a' && c <= 'z')
            return true;
        else
            return false;
    }

    private boolean isLetter(char c) {
        if (isUpperCase(c) || isLowerCase(c))
            return true;
        else
            return false;
    }

    public void encrypt() {

        String currentLine = "";
        String tempString = "";
        char nextChar = ' ';

        int j = 0;
        // Loop through each line in inputLines
        for (int i = 0; i < inputLines.size(); i++) {
            currentLine = inputLines.get(i);

            // Loop through each character in s
            for (int k = 0; k < currentLine.length(); k++) {
                if (k < currentLine.length() - 1) {
                    nextChar = currentLine.charAt(k + 1);
                }

                // Process the current character and add it to tempString
                if (isUpperCase(currentLine.charAt(k)))
                    tempString += ALPHABETS_UC.get(wordsOrderNumbers.get(j))
                            .charAt(
                                    BASE_ALPHABET_UC.indexOf(currentLine
                                            .charAt(k)));
                else if (isLowerCase(currentLine.charAt(k)))
                    tempString += ALPHABETS_LC.get(wordsOrderNumbers.get(j))
                            .charAt(
                                    BASE_ALPHABET_LC.indexOf(currentLine
                                            .charAt(k)));
                else
                    tempString += currentLine.charAt(k);

                if (currentLine.charAt(k) == ' ' && isLetter(nextChar)) {
                    j++;
                }
            }

            // Add each tempString to outputLines
            outputLines.add(tempString);
            tempString = "";
        }
    }

    public void decrypt() {

        String currentLine = "";
        String tempString = "";
        char nextChar = ' ';

        int j = 0;
        // Loop through each line in inputLines
        for (int i = 0; i < inputLines.size(); i++) {
            currentLine = inputLines.get(i);

            // Loop through each character in s
            for (int k = 0; k < currentLine.length(); k++) {
                if (k < currentLine.length() - 1) {
                    nextChar = currentLine.charAt(k + 1);
                }

                // Process the current character and add it to tempString
                if (isUpperCase(currentLine.charAt(k)))
                    tempString += BASE_ALPHABET_UC.charAt(ALPHABETS_UC.get(
                        wordsOrderNumbers.get(j)).indexOf(currentLine.charAt(k)));
                else if (isLowerCase(currentLine.charAt(k)))
                    tempString += BASE_ALPHABET_LC.charAt(ALPHABETS_LC.get(
                        wordsOrderNumbers.get(j)).indexOf(currentLine.charAt(k)));
                else
                    tempString += currentLine.charAt(k);

                if (currentLine.charAt(k) == ' ' && isLetter(nextChar)) {
                    j++;
                }
            }

            // Add each tempString to outputLines
            outputLines.add(tempString);
            tempString = "";
        }
    }

    public void printResults() {
        for (String s : outputLines) {
            System.out.println(s + " ");
        }
    }

    public void printResults(String filePath) {
        File f = new File(filePath);

        PrintWriter out = null;

        try {
            out = new PrintWriter(f);
        }
        // Exit the program if the file cannot be created
        catch (FileNotFoundException ex) {
            System.out.println("Cannot create file " + filePath + ".");
            System.exit(1);
        }

        // Print each line of outputLines in the text file
        for (String s : outputLines) {
            out.println(s);
        }

        out.close();

        // Tell the user the output has been successfully printed
        System.out.println("Output successfully saved to " + filePath + ".");
    }

    public String getResults() {
        String results = "";
        for (String s : outputLines) {
            results += s;
        }
        return results;
    }
}
