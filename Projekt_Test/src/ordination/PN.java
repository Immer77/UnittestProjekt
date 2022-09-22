package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private double antalEnheder;
    private final List<LocalDate> givetList = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen,
              Patient patient, double antalEnheder) {
        super(startDen, slutDen, patient);
        this.antalEnheder = antalEnheder;

    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean indenforOmraade = givesDen.isAfter(super.getStartDen().minusDays(1))
                && givesDen.isBefore(super.getSlutDen().plusDays(1));
        if (indenforOmraade) {
            givetList.add(givesDen);
        }
        return indenforOmraade;
    }

    public double doegnDosis() {
        LocalDate foerste = givetList.get(0);
        LocalDate sidste = givetList.get(givetList.size() - 1);
        int between = (int) ChronoUnit.DAYS.between(foerste, sidste);
        return (givetList.size() * antalEnheder) / between;
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return givetList.size() * antalEnheder;
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

    public void setAntalEnheder(double antalEnheder) {
        this.antalEnheder = antalEnheder;
    }
}

