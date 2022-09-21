package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private final ArrayList<Dosis> doses = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Patient patient) {
        super(startDen, slutDen, patient);
    }
    // TODO
    public ArrayList<Dosis> getDoses(){
        return new ArrayList<>(doses);
    }

    public Dosis createDosis (LocalTime tid, double antal){
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
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
}
