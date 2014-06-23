import java.util.Random;

public class Cipher {
    private String inText;
    private String outText;

    private char[] inChars;
    private char[] outChars;

    private int numChars;
    private int seed;
    private int seedPosition;

    private int[] seedArray;
    private int[] keyArray;

    /* Takes a string argument representing the plaintext */
    public Cipher(String text) {
        // By default the seed is in the middle of the plaintext
        this(text, text.length() / 2);
    }

    /*
     * Takes a string argument representing the plaintext and an int argument
     * representing the seed position
     */
    public Cipher(String text, int seedPos) {
        inText = text;
        inChars = inText.toCharArray();
        numChars = inChars.length;

        initCipher(seedPos);
    }

    /* Runs various cipher initialization tasks */
    private void initCipher(int seedPos) {
        // Default seed position
        seedPosition = seedPos;
        // The "seed" is the most important part of the cipher
        // and NEVER changes when encoded / decoded
        seed = (int) inChars[seedPosition];
        initSeedArray();
        initKeyArray();
    }

    /* Creates an array based on the "seed" */
    private void initSeedArray() {
        // The random generator is based on the seed and the number of characters
        Random randGen = new Random(seed + numChars);
        seedArray = new int[numChars];

        // Generate an array of random numbers
        for (int i = 0; i < seedArray.length; i++) {
            seedArray[i] = randGen.nextInt(125) - 25;
        }
    }

    /* Builds the key array from the plaintext and the seed array */
    private void initKeyArray() {
        keyArray = new int[numChars];
        for (int i = 0; i < numChars; i++) {
            keyArray[i] = (inChars[i] + seedArray[i]);
        }
    }

    /* Rebuilds the key array from the ciphertext */
    private void rebuildKeyArray() {
        keyArray = new int[numChars];
        for (int i = 0; i < numChars; i++) {
            keyArray[i] = (char) inChars[i];
        }
    }

    /* Runs the encoding operation on the input text */
    public void encode() {
        outChars = new char[numChars];
        outText = "";

        for (int i = 0; i < numChars; i++) {
            // NEVER encode the seed
            if (i != seedPosition)
                outChars[i] = (char) keyArray[i];
            else
                outChars[i] = inChars[i];
        }

        // Build the output text string
        for (int i = 0; i < numChars; i++) {
            outText += outChars[i];
        }
    }

    /* Runs the decoding operation on the input text */
    public void decode() {
        rebuildKeyArray();
        
        outChars = new char[numChars];
        outText = "";

        for (int i = 0; i < numChars; i++) {
            if (i != seedPosition)
                // Deconstructs the key array by removing the seed
                // and leaving the plaintext
                outChars[i] = (char) (keyArray[i] - seedArray[i]);
            else
                outChars[i] = inChars[i];
        }

        // Build the output text string
        for (int i = 0; i < numChars; i++) {
            outText += outChars[i];
        }
    }

    /* Gets the output text */
    public String getOutText() {
        return outText;
    }

    /* Prints the output text */
    public void printOutText() {
        System.out.print(outText);
    }
}