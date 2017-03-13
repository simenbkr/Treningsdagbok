package runtime.handler.statistics.typehandlers;

import data.dao.PulsDAO;
import data.dao.ØktDAO;
import data.dao.ØktTuppelDAO;
import data.models.Puls;
import data.models.Økt;
import data.models.ØktTuppel;
import data.models.Øvelse;
import runtime.handler.IHandler;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ØktInfoHandler implements IHandler {
    @Override
    public void handle(Scanner scanner) {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.MONTH, -1);

        List<Økt> økter = new ØktDAO().
                listAll()
                .stream()
                .filter(økt -> økt.getTidspunkt().after(new Timestamp(time.getTimeInMillis())))
                .collect(Collectors.toList());
        økter.sort(Comparator.comparing(Økt::getTidspunkt));

        if (økter.size() == 0) {
            System.out.println("Ingen økter den siste måneden");
            return;
        }

        System.out.println(" Nr. | Tidspunkt");
        System.out.println("------------------------");
        IntStream.range(0, økter.size())
                .mapToObj(index -> String.format("  %2d | ", index + 1) + økter.get(0).getTidspunkt().toString().substring(0, 16))
                .forEach(System.out::println);

        int number = -1;
        while (number == -1) {
            System.out.println("Skriv inn nummeret på økten du ønsker å se mer info om");
            String input = scanner.nextLine();
            try {
                number = Integer.valueOf(input);
            } catch (NumberFormatException nfe) {
                System.out.println("Ikke et gylid nummer!");
                continue;
            }

            if (number <= 0 || number > økter.size()) {
                System.out.println("Ikke et gyldig nummer!");
                number = -1;
            }
        }

        Økt økt = økter.get(--number);

        List<ØktTuppel> øktTuppels = new ØktTuppelDAO().getByØktId(økt.getId());

        System.out.println("Tidspunkt: " + økt.getTidspunkt().toString().substring(0, 16));
        System.out.println("Varighet: " + økt.getVarighet());
        System.out.println("Form: " + økt.getForm());
        System.out.println("Notat: " + økt.getNotat());
        System.out.println("Prestasjon: " + økt.getPrestasjon());

        System.out.println();

        if (øktTuppels.size() == 0) {
            System.out.println("Ingen øvelser registrert for denne økten");
        } else {

            System.out.println("Øvelser:");
            øktTuppels.stream()
                    .map(øktTuppel -> String.format(" %17s | ", øktTuppel.getØvelse().getNavn()) + String.format(" %8s | ", øktTuppel.getØvelse().getType()) +
                            String.format("%35s | ", øktTuppel.getResultat().getStyrke() != null ? øktTuppel.getResultat().getStyrke().toString() : øktTuppel.getResultat().getUtholdenhet().toString()) +
                            String.format("%35s | ", øktTuppel.getMiljø().getInne() != null ? øktTuppel.getMiljø().getInne().toString() : øktTuppel.getMiljø().getUte().toString())
                    )
                    .forEach(System.out::println);
        }

        System.out.println();

        List<Puls> pulss = new ØktDAO().getPulses(økt.getId());
        if (pulss.size() == 0) {
            System.out.println("Ingen puls data registrert for øvelsen");
        } else {
            System.out.println("Puls:");
            System.out.println("Puls |  Bredde |  Lengde | Høyde");
            System.out.println("-------------------------------------");
            pulss.stream()
                    .map(puls -> puls.getTid().toString().substring(0, 16) + String.format(" %3d |  %3.2f |  %3.2f |  %4.2f ", puls.getPuls(), puls.getBredde(), puls.getLengde(), puls.getHøyde()))
                    .forEach(System.out::println);
        }
        
        System.out.println();
    }

    @Override
    public boolean validCommand(String command) {
        return command.matches("økt");
    }
}
