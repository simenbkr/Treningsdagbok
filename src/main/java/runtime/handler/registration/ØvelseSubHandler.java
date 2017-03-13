package runtime.handler.registration;

import data.dao.ØvelseDAO;
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
            System.out.println("Beskriv øvelsen:\nHvordan utføres den?\nHvilke muskelgrupper trener den?\nHvor mange deltagere er det?");
            System.out.print("> ");
            String description = scanner.nextLine();

            //TODO kategori?

            String type;
            boolean typeChecked = false;
            while (!typeChecked) {
                System.out.println("Er det en Styrke eller Utholdenhets øvelse?");
                System.out.print("> ");
                type = scanner.nextLine();

                if (type.equals("Styrke") || type.equals("Utholdenhet")) {
                    øvelse = new Øvelse(name, description, type);
                } else {
                    System.out.println("Venligst oppgi \"Styrke\" eller \"Utholdenhet\" som øvelsestype.");
                }
            }
        }

        Miljø miljø = null;

        System.out.println("\nMiljøet for øvelsen må registreres.");
        String miljøType;
        boolean miljøChecked = false;
        while (!miljøChecked) {
            System.out.println("Foregår øvelsen ute eller inne?");
            System.out.print("> ");
            miljøType = scanner.nextLine();

            if (miljøType.equals("inne")) {
                miljøChecked = true;

                System.out.println("Hvordan er luftkvaliteten?");
                System.out.print("> ");
                String luft = scanner.nextLine();

                boolean tilskuerChecked = false;
                int tilskuere = 0;
                while (!tilskuerChecked) try {
                    System.out.println("Hvor mange tilskuere er det?");
                    System.out.print("> ");
                    tilskuere = Integer.valueOf(scanner.nextLine());

                    if (tilskuere > 0) System.out.println("det kan ikke være et negativt antall tilskuere");
                    else tilskuerChecked = true;

                } catch (NumberFormatException e) {
                    System.out.println("Venligst oppgi et tall.");
                }

                miljø = new Miljø(new Inne(luft, tilskuere));

            } else if (miljøType.equals("ute")) {
                miljøChecked = true;

                System.out.println("Hvilken værtype er det?");
                System.out.print("> ");
                String værtype = scanner.nextLine();

                System.out.println("Hvordan er værforholdet?");
                System.out.print("> ");
                String værforhold = scanner.nextLine();

                boolean tempChecked = false;
                float temperatur = 0;
                while (!tempChecked) try {
                    System.out.println("Hva er temperaturen?");
                    System.out.print("> ");
                    temperatur = Float.valueOf(scanner.nextLine());

                    tempChecked = true;

                } catch (NumberFormatException e) {
                    System.out.println("Venligst oppgi et tall.");
                }

                miljø = new Miljø(new Ute(værforhold, værtype, temperatur));

            } else {
                System.out.println("Venligst oppgi \"inne\" eller \"ute\"");
            }
        }

        Resultat resultat = null;
        System.out.println("\nDet må registreres en resultat for øvelsen.");
        if (øvelse.getType().equals("Styrke")) {

            float belastning = 0;
            boolean belastningChecked = false;
            while (!belastningChecked) try {
                System.out.println("Hva var belastningen?");
                System.out.print("> ");
                belastning = Float.valueOf(scanner.nextLine());

                if (belastning < 0) System.out.println("Belastning må være positiv.");
                else belastningChecked = true;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et tall.");
            }

            int sett = 0;
            boolean settChecked = false;
            while (!settChecked) try {
                System.out.println("Hvor store var settene?");
                System.out.print("> ");
                sett = Integer.valueOf(scanner.nextLine());

                if (sett < 0) System.out.println("Sett på være positiv.");
                else settChecked = true;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et heltall.");
            }

            int reps = 0;
            boolean repsChecked = false;
            while (!repsChecked) try {
                System.out.println("Hvor mange repitisjoner var det?");
                System.out.print("> ");
                reps = Integer.valueOf(scanner.nextLine());

                if (reps < 0) System.out.println("Det må være et positivt antall repitisjoner");
                else repsChecked = true;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et heltall.");
            }

            resultat = new Resultat(new Styrke(belastning, reps, sett));


        } else if (øvelse.getType().equals("Utholdenhet")) {
            System.out.println("Hvilken enhet vil du måle resultatet i?");
            System.out.print("> ");
            String enhet = scanner.nextLine();

            float lengde = 0;
            boolean lengdeChecked = false;
            while (!lengdeChecked) try {
                System.out.println("Hva er resultatet?");
                System.out.print("> ");
                lengde = Float.valueOf(scanner.nextLine());

                lengdeChecked = true;

            } catch (NumberFormatException e) {
                System.out.println("Venligst oppgi et tall.");
            }

            resultat = new Resultat(new Utholdenhet(lengde, enhet));
        }

        ØktTuppel øktTuppel = new ØktTuppel(økt, øvelse, miljø, resultat);
    }

    public boolean validCommand(String command) {
        return command.matches("øvelse");
    }
}
