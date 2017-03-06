package runtime.handler.registration;

import data.dao.ØvelseDAO;
import data.models.Øvelse;
import runtime.handler.IHandler;

import java.util.Scanner;

public class ØvelseSubHandler implements IHandler {
    public void handle(Scanner scanner) {
        System.out.println("Hva heter øvelsen?");
        System.out.print("> ");
        String input = scanner.nextLine();

        Øvelse øvelse = new ØvelseDAO().getByName(input);
        if ( øvelse == null) {
            //lag ny
        }

        //sett miljø

        //sett resultat

    }

    public boolean validCommand(String command) {
        return command.matches("øvelse");
    }
}
