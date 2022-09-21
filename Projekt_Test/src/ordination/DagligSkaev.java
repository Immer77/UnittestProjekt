package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private final ArrayList<Dosis> doses = new ArrayList<>();

    private Ordination ordination;


    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Patient patient) {
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
        int samlet;

        samlet = doses.size() - 1;

        return samlet;
    }

    @Override
    public double doegnDosis() {

        double sum;

        double days = ChronoUnit.DAYS.between(ordination.getStartDen(), ordination.getSlutDen());
        double samletDosis = doses.size() - 1;

        sum = samletDosis / days;

        return sum;
    }

    @Override
    public String getType() {
        return ordination.getType();
    }
}
