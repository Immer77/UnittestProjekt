package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligFast extends Ordination{
    // TODO
    // Composition 0..4 til dosis
    private int counter = 0;
    private Dosis[] doses = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Patient patient) {
        super(startDen, slutDen, patient);
    }

    public Dosis[] getDoses() {
        return doses;
    }

    public Dosis createDosis(LocalTime tid, double antal){
        if(doses.length < 4){
            Dosis dosis = new Dosis(tid,antal);
            doses[counter] = dosis;
            return dosis;
        }else{
            throw new RuntimeException("Antal dosis må ikke overstige 4");
        }
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * antalDage();
    }

    @Override
    public double doegnDosis() {
        double result = 0;
        for (Dosis d : doses){
            result += d.getAntal();
        }
        return result;
    }

    @Override
    public String getType() {
        return "Daglig Fast";
    }
}
