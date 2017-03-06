package runtime.handler.statistics;

import runtime.handler.IHandler;

import java.util.Scanner;

public class StatisticsHandler implements IHandler {
    public void handle(Scanner scanner) {
        // TODO: Implement 
    }

    public boolean validCommand(String command) {
        return command.matches("statistikk");
    }
}
