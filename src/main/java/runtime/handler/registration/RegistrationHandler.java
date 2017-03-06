package runtime.handler.registration;

import data.models.Økt;
import runtime.handler.IHandler;

import java.sql.Timestamp;
import java.util.Scanner;

public class RegistrationHandler implements IHandler {

    public void handle(Scanner scanner) {
        boolean run = true;
        System.out.println("Det må registreres litt informasjon om økten.");
        System.out.println("Hvilken kondisjon er du i?");
        System.out.print("> ");
        String kondisjon = scanner.nextLine();
        Økt økt = new Økt(new Timestamp(System.currentTimeMillis()), kondisjon);
        økt.setNotat(""); //init for å kunne appende

        System.out.println("kommandoer her");
        while (run) {
            String input = scanner.nextLine();

            if (input.matches("note")) {
                NoteSubHandler.appendToNote(scanner, økt);
            } else if(input.matches("økt")) {
                //TODO
            } else if(input.matches("puls")) {
                //TODO
            } else if (input.matches("ferdig")) {
                run = false;
            } else {
                System.out.println("hva kan vi gjøre her?");
            }
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        økt.setVarighet((int)((now.getTime() - økt.getTidspunkt().getTime())/60000));
        System.out.println("Hvordan presterte du denne treningsøkten?");
        System.out.print("> ");
        String prestasjon = scanner.nextLine();

    }

    public boolean validCommand(String command) {
        return command.matches("registrer");
    }
}
