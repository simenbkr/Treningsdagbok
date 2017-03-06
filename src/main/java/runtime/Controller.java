package runtime;


import runtime.handler.IHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private List<IHandler> handlers = new ArrayList<IHandler>(Arrays.asList(new IHandler[]{

    }));

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
