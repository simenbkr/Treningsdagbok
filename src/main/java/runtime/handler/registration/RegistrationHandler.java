package runtime.handler.registration;

import data.models.Økt;
import runtime.handler.IHandler;

import java.sql.Timestamp;
import java.util.Scanner;

public class RegistrationHandler implements IHandler {

    public void handle(Scanner scanner) {
        boolean run = true;
        System.out.println("\nDet må registreres litt informasjon om økten.");
        System.out.println("Hvilken kondisjon er du i?");
        System.out.print("> ");
        String kondisjon = scanner.nextLine();
        Økt økt = new Økt(new Timestamp(System.currentTimeMillis()), kondisjon);
        økt.setNotat(""); //init for å kunne appende

        while (run) {
            System.out.println("\nKommandoer:");
            System.out.println("------------");
            System.out.println("øvelse - Registrer en ny øvelse");
            System.out.println("puls - Registrer en ny pulsmåling");
            System.out.println("notat - Legg til notat");
            System.out.println("ferdig - Si deg ferdig med registreringen\n");
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.matches("notat")) {
                NoteSubHandler.appendToNote(scanner, økt);
            } else if(input.matches("øvelse")) {
                ØvelseSubHandler.handle(scanner, økt);
            } else if(input.matches("puls")) {
                PulsSubHandler.handle(scanner, økt);
            } else if (input.matches("ferdig")) {
                run = false;
            } else {
                System.out.println("hva kan vi gjøre her?");
            }
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        økt.setVarighet((int)((now.getTime() - økt.getTidspunkt().getTime())/60000));
        System.out.println("\nHvordan presterte du denne treningsøkten?");
        System.out.print("> ");
        String prestasjon = scanner.nextLine();

    }

    public boolean validCommand(String command) {
        return command.matches("registrer");
    }
}
