package ordination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private double antalEnheder;
    private Ordination ordination;
    private List<LocalDate> dateList = new ArrayList<>();

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
        dateList.add(givesDen);
        return givesDen.isAfter(ordination.getStartDen().minusDays(1))
                && givesDen.isBefore(ordination.getSlutDen().plusDays(1));
    }

    public double doegnDosis() {
        double sum = 0;

        return 0.0;
    }

    @Override
    public String getType() {
        return null;
    }


    public double samletDosis() {
        return 0.0;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        return dateList.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}

