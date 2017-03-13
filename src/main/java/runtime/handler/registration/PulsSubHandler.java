package runtime.handler.registration;

import data.models.Puls;
import data.models.Økt;

import java.sql.Timestamp;
import java.util.Scanner;

public class PulsSubHandler {
    public static Puls handle(Scanner scanner, Økt økt) {
        int pulsMeasurement = 0;
        double latitude = 0, longitude = 0, height = 0;

        boolean pulsChecked = false;
        while (!pulsChecked) try {
            System.out.println("\nSkriv inn pulsen din");
            System.out.print("> ");
            pulsMeasurement = Integer.valueOf(scanner.nextLine());

            if (pulsMeasurement < 20 || pulsMeasurement > 250) System.out.println("puls bør være mellom 20 og 250 (ellers bør du oppsøke en lege)");
            else pulsChecked = true;

        } catch (NumberFormatException e) {
            System.out.println("Puls må være et heltall");
        }

        boolean latChecked = false;
        while (!latChecked) try {
            System.out.println("\nSkriv inn din breddegrad (-90, 90)");
            System.out.print("> ");
            latitude = Double.valueOf(scanner.nextLine());

            if (latitude < -90 || latitude > 90) System.out.println("breddegrad må være mellom -90 og 90");
            else latChecked = true;

        } catch (NumberFormatException e) {
            System.out.println("Breddegrad må være et decimaltall mellom -90 og 90, der 90 er nordpolen og 0 er ekvator");
        }

        boolean longChecked = false;
        while (!longChecked) try {
            System.out.println("\nSkriv inn din lengdegrad (-180, 180)");
            System.out.print("> ");
            longitude = Double.valueOf(scanner.nextLine());

            if (longitude < -180 || longitude > 180) System.out.println("lengdegrad må være mellom -180 og 180");
            else longChecked = true;

        } catch (NumberFormatException e) {
            System.out.println("Lengdegrad må være et decimaltall mellom -180 og 180, der 0 er Greenwitch, England");
        }

        boolean heightChecked = false;
        while (!heightChecked) try {
            System.out.println("\nSkriv inn din høyde over havet (m.o.h)");
            System.out.print("> ");
            height = Double.valueOf(scanner.nextLine());

            if (height < -1000 || height > 8850) System.out.println("Du kan ikke være under 1km under havet og ikke mer en Mt.Everest");
            else heightChecked = true;

        } catch (NumberFormatException e) {
            System.out.println("M.o.h. Må være mellom -1000 og 8850");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new Puls(timestamp, pulsMeasurement, longitude, height, latitude, økt.getId());
    }
}
