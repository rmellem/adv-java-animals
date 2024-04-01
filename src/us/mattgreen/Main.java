package us.mattgreen;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private final static FileOutput outFile = new FileOutput("animals.txt");
    private final static FileInput inFile = new FileInput("animals.txt");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Talkable> talkables = new ArrayList<>();

        // Lines to Replace Begin Here
        InputGathering gathering = new InputGathering(talkables);
        gathering.addTalkables();
        // End Lines to Replace

        for (Talkable thing : gathering.getTalkables()) {
            printOut(thing);
        }
        outFile.fileClose();
        inFile.fileRead();
        inFile.fileClose();

        FileInput indata = new FileInput("animals.txt");
        String line;
        while ((line = indata.fileReadLine()) != null) {
            System.out.println(line);
        }
    }

    public static void printOut(Talkable p) {
        System.out.println(p.getName() + " says = " + p.talk());
        outFile.fileWrite(p.getName() + " | " + p.talk());
    }
}
