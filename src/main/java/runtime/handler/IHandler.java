package runtime.handler;

import java.util.Scanner;

public interface IHandler {

    void handle(Scanner scanner);
    boolean validCommand(String command);

}
