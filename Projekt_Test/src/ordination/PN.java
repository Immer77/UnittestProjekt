package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private double antalEnheder;
    private Ordination ordination;
    private int dosis;
    private final List<LocalDate> givetList = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, Patient patient) {
        super(startDen, slutDen, patient);

    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean indenforOmraade = givesDen.isAfter(ordination.getStartDen().minusDays(1))
                && givesDen.isBefore(ordination.getSlutDen().plusDays(1));
        if (indenforOmraade) {
            givetList.add(givesDen);
        }
        return indenforOmraade;

    }

    public double doegnDosis() {
        long between = ChronoUnit.DAYS.between(ordination.getStartDen(), ordination.getSlutDen());
        return (givetList.size() * antalEnheder) / (between);
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return ordination.samletDosis();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        return givetList.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}

