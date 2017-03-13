package runtime;


import runtime.handler.BestHandler;
import runtime.handler.IHandler;
import runtime.handler.statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private List<IHandler> handlers = new ArrayList<IHandler>(Arrays.asList(new IHandler[]{
            new StatisticsHandler(),
            new BestHandler()
    }));

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Kommandoer:");
            System.out.println("------------");
            System.out.println("statistikk - Viser statistikk for siste måned");
            System.out.println("beste - Viser beste økter");
            System.out.println("");
            System.out.print("> ");
            String input = scanner.nextLine();
            for (IHandler handler : handlers) {
                if (handler.validCommand(input)) handler.handle(scanner);
            }
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
