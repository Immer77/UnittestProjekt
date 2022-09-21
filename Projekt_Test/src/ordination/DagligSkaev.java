package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private final ArrayList<Dosis> doses = new ArrayList<>();

    private Ordination ordination;


    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Patient patient, double morgenAntal, double middagAntal, ) {
        super(startDen, slutDen, patient);
    }

    // TODO
    public ArrayList<Dosis> getDoses() {
        return new ArrayList<>(doses);
    }

    public Dosis createDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        doses.add(dosis);
        return dosis;
    }


    public void opretDosis(LocalTime tid, double antal) {
        // TODO
        Dosis dosis = new Dosis(tid, antal);
        doses.add(dosis);
    }


    @Override
    public double samletDosis() {
        return doegnDosis() / antalDage();
    }

    @Override
    public double doegnDosis() {
        double sum = 0;
        for (Dosis d : doses) {
            sum += d.getAntal();
        }
        return sum;
    }

    @Override
    public String getType() {
        return ordination.getType();
    }
}
