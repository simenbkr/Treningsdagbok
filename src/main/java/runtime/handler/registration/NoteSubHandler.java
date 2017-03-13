package runtime.handler.registration;

import data.models.Økt;

import java.util.Scanner;

public class NoteSubHandler {
    public static void appendToNote(Scanner scanner, Økt økt) {
        System.out.println("Skriv det du vil legge til i notatet");
        System.out.print("> ");
        String input = scanner.nextLine();
        økt.setNotat(økt.getNotat().concat(input));
        System.out.println("Lagt til i notat.");
    }
}
