package runtime.handler.statistics.typehandlers;

import data.dao.ØktDAO;
import data.models.Økt;
import runtime.handler.IHandler;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class ØkterHandler implements IHandler {
    public void handle(Scanner scanner) {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.MONTH, -1);

        List<Økt> økter = new ØktDAO().
                listAll()
                .stream()
                .filter(økt -> økt.getTidspunkt().after(new Timestamp(time.getTimeInMillis())))
                .collect(Collectors.toList());
        økter.sort(Comparator.comparing(Økt::getTidspunkt));

        System.out.println("Antall økter: " + økter.size());
        System.out.println("Total varighet: " + økter.stream().mapToInt(Økt::getVarighet).sum() + " min");
        System.out.println();
        System.out.println("Økter:");
        System.out.println(" Tidspunkt            | Varighet | Form");
        System.out.println("----------------------|----------|-----------");
        økter.stream()
                .map(økt -> økt.getTidspunkt().toString().substring(0, 16) + "      | " + String.format("%3d min", økt.getVarighet()) + "  | " + økt.getForm())
                .forEach(System.out::println);
    }

    public boolean validCommand(String command) {
        return command.matches("økter");
    }
}
