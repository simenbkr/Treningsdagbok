package runtime.handler.registration;

import data.dao.ØktDAO;
import data.models.Økt;
import runtime.handler.IHandler;

import java.sql.Timestamp;
import java.util.Scanner;

public class RegistrationHandler implements IHandler {

    public void handle(Scanner scanner) {
        System.out.println("\nDet må registreres litt informasjon om økten.");
        System.out.println("Hvilken kondisjon er du i?");
        System.out.print("> ");
        String kondisjon = scanner.nextLine();
        Økt økt = Økt.createAndPersist(new Timestamp(System.currentTimeMillis()), kondisjon);

        while (true) {
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
                break;
            }
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        økt.setVarighet((int)((now.getTime() - økt.getTidspunkt().getTime())/60000));
        System.out.println("\nHvordan presterte du denne treningsøkten?");
        System.out.print("> ");
        økt.setPrestasjon(scanner.nextLine());
        new ØktDAO().update(økt);
    }

    public boolean validCommand(String command) {
        return command.matches("registrer");
    }
}
