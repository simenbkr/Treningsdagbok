package runtime.handler;

import data.dao.StyrkeDAO;
import data.dao.ØktTuppelDAO;
import data.models.Styrke;
import data.models.ØktTuppel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BestHandler implements IHandler{
    public void handle(Scanner scanner) {
        while(true){
            System.out.println("(1) Styrke eller (2) utholdenhet?");
            System.out.print("> ");
            String input = scanner.nextLine();
            if ( input.toLowerCase().matches("^avslutt")) break;
            else if (input.toLowerCase().equals("^styrke") || input.matches("^1")){
                this.styrkeHandler(scanner);
            }
        }
    }

    private void styrkeHandler(Scanner scanner){
        Date startDatoen;
        Date sluttDatoen;
        while(true){
            System.out.println("Velg startdato (YYYY-MM-DD)");
            System.out.print("> ");
            String datoString = scanner.nextLine();
            DateFormat formatør = new SimpleDateFormat("yyyy-mm-dd");
            try {
                startDatoen = formatør.parse(datoString);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        while(true){
            System.out.println("Velg sluttdato (YYYY-MM-DD)");
            System.out.print("> ");
            String datoString = scanner.nextLine();
            DateFormat formatør = new SimpleDateFormat("yyyy-mm-dd");
            try {
                sluttDatoen = formatør.parse(datoString);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //List<Styrke> styrkeØktene = new StyrkeDAO().listAll();
        List<ØktTuppel> styrkeØktTupplene = hentStyrkeØkter();

        final Date dummy1 = startDatoen;
        final Date dummy2 = sluttDatoen;

        //styrkeØktTupplene.stream().filter(s1 -> s1.getØkt().getTidspunkt().compareTo(dummy1) >= 0 &&
        //s1.getØkt().getTidspunkt().compareTo(dummy2) <= 0);

        //Collections.sort(styrkeØktTupplene, (s1,s2) -> s1.getResultat().getStyrke().compareTo(s2.getResultat().getStyrke()));

        String output = "";
        for(ØktTuppel t : styrkeØktTupplene){
            Styrke styrken = t.getResultat().getStyrke();
            output += styrken.getBelastning()*styrken.getSett()*styrken.getReps();
        }
        System.out.println(output);
        System.out.println("you w0t m8");
    }

    public static List<ØktTuppel> hentStyrkeØkter(){
        List<ØktTuppel> øktTupplene = new ArrayList<ØktTuppel>();
        List<ØktTuppel> alleØkter = new ØktTuppelDAO().listAll();

        for(ØktTuppel tuppel : alleØkter){
            if(tuppel.getResultat().getStyrke() != null){
                øktTupplene.add(tuppel);
            }
        }
        return øktTupplene;
    }

    public boolean validCommand(String command) {
        return command.matches("^beste");
    }

    public static void main(String[] args) {
        BestHandler hand = new BestHandler();
        Scanner scanner = new Scanner(System.in);
        hand.handle(scanner);
    }

}
