package runtime.handler;

import data.dao.StyrkeDAO;
import data.dao.ØktTuppelDAO;
import data.models.Styrke;
import data.models.Utholdenhet;
import data.models.ØktTuppel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BestHandler implements IHandler {
    public void handle(Scanner scanner) {
        while (true) {
            System.out.println("(1) Styrke eller (2) utholdenhet?");
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.toLowerCase().matches("^avslutt")) break;
            else if (input.toLowerCase().matches("^styrke") || input.matches("^1")) {
                this.styrkeHandler(scanner);
            }
            else if(input.toLowerCase().matches("^utholdenhet") || input.matches("^2")){
                this.utholdenhetHandler(scanner);
            }
        }
    }

    private void styrkeHandler(Scanner scanner) {
        ArrayList<Date> datoene = hentStartOgSluttDatoer(scanner);
        final Date startDatoen = datoene.get(0);
        final Date sluttDatoen = datoene.get(1);
        List<ØktTuppel> styrkeØktTupplene = hentStyrkeØkter();

        //System.out.println("Dato1: " + startDatoen.toString() + "\n" + "Dato2: " + sluttDatoen.toString() + "\n");
        //Denne lambda-funksjonen as, topkek
        styrkeØktTupplene = styrkeØktTupplene
                .stream()
                .filter(s1 -> s1.getØkt().getTidspunkt().compareTo(startDatoen) >= 0 && s1.getØkt().getTidspunkt().compareTo(sluttDatoen) <= 0)
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.sort(styrkeØktTupplene, (s1,s2) -> s2.getResultat().getStyrke().compareTo(s1.getResultat().getStyrke()));

        String output = "";
        for (ØktTuppel t : styrkeØktTupplene) {
            Styrke styrken = t.getResultat().getStyrke();
            output += styrken.getBelastning() * styrken.getSett() * styrken.getReps();
            output += "\n";
        }
        output += "Du har i denne perioden trent styrke " + styrkeØktTupplene.size() + " ganger.";
        System.out.println(output);
    }

    private static List<ØktTuppel> hentStyrkeØkter() {
        List<ØktTuppel> øktTupplene = new ArrayList<ØktTuppel>();
        List<ØktTuppel> alleØkter = new ØktTuppelDAO().listAll();

        for (ØktTuppel tuppel : alleØkter) {
            try {
                if(tuppel.getResultat().getStyrke() != null) {
                    øktTupplene.add(tuppel);
                }
            } catch(NullPointerException e){
                e.printStackTrace();
            }
        }
        return øktTupplene;
    }

    private static List<ØktTuppel> hentUtholdenhetsØkter(){
        List<ØktTuppel> øktTupplene = new ArrayList<ØktTuppel>();
        List<ØktTuppel> alleØkter = new ØktTuppelDAO().listAll();

        for (ØktTuppel tuppel : alleØkter) {
            try {
                if(tuppel.getResultat().getUtholdenhet() != null) {
                    øktTupplene.add(tuppel);
                }
            } catch(NullPointerException e){
                e.printStackTrace();
            }
        }
        return øktTupplene;
    }

    private void utholdenhetHandler(Scanner scanner){
        ArrayList<Date> datoene = hentStartOgSluttDatoer(scanner);
        final Date startDato = datoene.get(0);
        final Date sluttDato = datoene.get(1);

        List<ØktTuppel> øktTupplene = hentUtholdenhetsØkter();
        øktTupplene = øktTupplene
                .stream()
                .filter(s1 -> s1.getØkt().getTidspunkt().compareTo(startDato) >= 0
                        && s1.getØkt().getTidspunkt().compareTo(sluttDato) <= 0)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(øktTupplene, (s1,s2) -> s1.getResultat().getUtholdenhet().compareTo(s2.getResultat().getUtholdenhet()));

        String output = "";
        for (ØktTuppel t : øktTupplene) {
            Utholdenhet uth = t.getResultat().getUtholdenhet();
            output += uth.getLengde() + " " + uth.getEnhet();
            output += "\n";
        }
        output += "Du har i denne perioden trent utholdenhet " + øktTupplene.size() + " ganger.";
        System.out.println(output);
    }

    private ArrayList<Date> hentStartOgSluttDatoer(Scanner scanner){
        Date startDatoen;
        Date sluttDatoen;
        ArrayList<Date> dates = new ArrayList<Date>();
        while (true) {
            System.out.println("Velg startdato (YYYY-MM-DD)");
            System.out.print("> ");
            String datoString = scanner.nextLine();
            DateFormat formatør = new SimpleDateFormat("yyyy-MM-dd");
            try {
                startDatoen = formatør.parse(datoString);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            System.out.println("Velg sluttdato (YYYY-MM-DD)");
            System.out.print("> ");
            String datoString = scanner.nextLine();
            DateFormat formatør = new SimpleDateFormat("yyyy-MM-dd");
            try {
                sluttDatoen = formatør.parse(datoString);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dates.add(startDatoen); dates.add(sluttDatoen);
        return dates;
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