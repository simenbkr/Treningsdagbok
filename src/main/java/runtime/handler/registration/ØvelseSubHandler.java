package runtime.handler.registration;

import data.dao.*;
import data.models.*;

import java.util.Scanner;

public class ØvelseSubHandler {
    public static void handle(Scanner scanner, Økt økt) {
        System.out.println("\nHva heter øvelsen?");
        System.out.print("> ");
        String name = scanner.nextLine();

        Øvelse øvelse = new ØvelseDAO().getByName(name);
        if (øvelse == null) {
            System.out.println("\nØvelsen ligger ikke i vår database, det vil opprettes en ny øvelse.");
            System.out.println("Beskriv øvelsen, (forslag):\n\t-Hvordan utføres den?\n\t-Hvilke muskelgrupper trener den?\n\t-Hvor mange deltagere er det?");
            System.out.print("> ");
            String description = scanner.nextLine();

            //TODO kategori?

            String type;
            while (true) {
                System.out.println("Er det en Styrke eller Utholdenhets øvelse?");
                System.out.print("> ");
                type = scanner.nextLine();

                if (type.toLowerCase().equals("styrke") || type.toLowerCase().equals("utholdenhet")) {
                    øvelse = new Øvelse(name, description, type);
                    new ØvelseDAO().create(øvelse);
                    break;
                } else {
                    System.out.println("Venligst oppgi \"styrke\" eller \"utholdenhet\" som øvelsestype.");
                }
            }
        }

        Miljø miljø = null;

        System.out.println("\nHvordan var miljøet da du utførte øvelsen?");
        String miljøType;
        while (true) {
            System.out.println("Foregår øvelsen ute eller inne?");
            System.out.print("> ");
            miljøType = scanner.nextLine();

            if (miljøType.toLowerCase().equals("inne")) {
                System.out.println("Hvordan er luftkvaliteten?");
                System.out.print("> ");
                String luft = scanner.nextLine();

                int tilskuere = 0;
                while (true) try {
                    System.out.println("Hvor mange tilskuere er det?");
                    System.out.print("> ");
                    tilskuere = Integer.valueOf(scanner.nextLine());

                    if (tilskuere > 0) System.out.println("det kan ikke være et negativt antall tilskuere");
                    else break;

                } catch (NumberFormatException e) {
                    System.out.println("Venligst oppgi et heltall.");
                }
                Inne inne = new Inne(luft, tilskuere);
                new InneDAO().create(inne);
                miljø = new Miljø(inne);
                new MiljøDAO().create(miljø);
                break;
            } else if (miljøType.toLowerCase().equals("ute")) {

                System.out.println("Hvilken værtype er det?");
                System.out.print("> ");
                String værtype = scanner.nextLine();

                System.out.println("Hvordan er værforholdet?");
                System.out.print("> ");
                String værforhold = scanner.nextLine();

                float temperatur = 0;
                while (true) try {
                    System.out.println("Hva er temperaturen?");
                    System.out.print("> ");
                    temperatur = Float.valueOf(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Venligst oppgi et tall (skill decimal med punktum).");
                }
                Ute ute = new Ute(værforhold, værtype, temperatur);
                new UteDAO().create(ute);
                miljø = new Miljø(ute);
                new MiljøDAO().create(miljø);
                break;
            } else {
                System.out.println("Venligst oppgi \"inne\" eller \"ute\"");
            }
        }

        Resultat resultat = null;
        System.out.println("\nDet må registreres en resultat for øvelsen.");
        if (øvelse.getType().toLowerCase().equals("styrke")) {

            float belastning = 0;
            while (true) try {
                System.out.println("Hva var belastningen?");
                System.out.print("> ");
                belastning = Float.valueOf(scanner.nextLine());

                if (belastning < 0) System.out.println("Belastning må være positiv.");
                else break;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et tall (skill decimal med punktum).");
            }

            int sett = 0;
            while (true) try {
                System.out.println("Hvor store var settene?");
                System.out.print("> ");
                sett = Integer.valueOf(scanner.nextLine());

                if (sett < 0) System.out.println("Sett på være positiv.");
                else break;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et heltall.");
            }

            int reps = 0;
            while (true) try {
                System.out.println("Hvor mange repitisjoner var det?");
                System.out.print("> ");
                reps = Integer.valueOf(scanner.nextLine());

                if (reps < 0) System.out.println("Det må være et positivt antall repitisjoner");
                else break;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et heltall.");
            }
            Styrke styrke = new Styrke(belastning, reps, sett);
            new StyrkeDAO().create(styrke);
            resultat = new Resultat(styrke);
            new ResultatDAO().create(resultat);


        } else if (øvelse.getType().toLowerCase().equals("utholdenhet")) {
            System.out.println("Hvilken enhet vil du måle resultatet i?");
            System.out.print("> ");
            String enhet = scanner.nextLine();

            float lengde = 0;
            while (true) try {
                System.out.println("Hva er resultatet?");
                System.out.print("> ");
                lengde = Float.valueOf(scanner.nextLine());
                break;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et tall (skill decimal med punktum).");
            }
            Utholdenhet utholdenhet = new Utholdenhet(lengde, enhet);
            new UtholdenhetDAO().create(utholdenhet);
            resultat = new Resultat(utholdenhet);
            new ResultatDAO().create(resultat);
        }

        ØktTuppel øktTuppel = new ØktTuppel(økt, øvelse, miljø, resultat);
        new ØktTuppelDAO().create(øktTuppel);
    }

    public boolean validCommand(String command) {
        return command.matches("øvelse");
    }
}
