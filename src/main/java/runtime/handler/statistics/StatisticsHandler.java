package runtime.handler.statistics;

import runtime.handler.IHandler;
import runtime.handler.statistics.typehandlers.ØktInfoHandler;
import runtime.handler.statistics.typehandlers.ØkterHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StatisticsHandler implements IHandler {

    private List<IHandler> handlers = new ArrayList<>(Arrays.asList(new IHandler[]{
            new ØkterHandler(),
            new ØktInfoHandler()
    }));

    public void handle(Scanner scanner) {
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("Viser statistikk for siste måned");
            System.out.println("Kommandoer:");
            System.out.println("-----------");
            System.out.println("økter - Viser oversikt over økter");
            System.out.println("økt - Viser informasjon om en økt");
            System.out.print("> ");
            String input = scanner.nextLine();
            for (IHandler handler : handlers) {
                if (handler.validCommand(input)) {
                    handler.handle(scanner);
                    return;
                }
            }
            System.out.println();
        }
    }

    public boolean validCommand(String command) {
        return command.matches("statistikk");
    }
}
