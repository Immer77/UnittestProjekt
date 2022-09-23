package controller;

import ordination.DagligFast;
import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = new Controller();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void getController() {
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination() {
    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination() {
    }

    @org.junit.jupiter.api.Test
    void TC1_opretDagligSkaevOrdination_DatoGyldig_KlokkeSlætIndtastet_antalEnheder_positiv() {

        //Arrange - opretDagligSkaevOrdination
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,01,01), LocalDate.of(2022,01,05), p1);
        Laegemiddel l1 = new Laegemiddel("Fucidin", 0.025,0.025,0.025,"Styk");
        d1.createDosis(LocalTime.of(9,00),2);
        d1.createDosis(LocalTime.of(12,00),3);


        ArrayList<LocalTime> tid = new ArrayList<>();
        ArrayList<Double> antal = new ArrayList<>();

        for (int i = 0; i < d1.getDoses().size(); i++) {
            tid.add(d1.getDoses().get(i).getTid());
            antal.add(d1.getDoses().get(i).getAntal());
        }

        LocalTime[] tidsListe = tid.toArray(new LocalTime[0]);
        double[] antalEnheder = Arrays.stream(antal.stream().mapToDouble(d -> d).toArray()).toArray();


        //Act

        DagligSkaev expected = controller.opretDagligSkaevOrdination(d1.getStartDen(),d1.getSlutDen(),p1,l1, tidsListe,antalEnheder);

        //Assert

        assertTrue(p1.getOrdinationer().contains(expected));
    }

    @org.junit.jupiter.api.Test
    void TC2_opretDagligSkaevOrdination_DatoGyldig_IngenKlokketslæt_AntalenhederPositiv() {

        //Arrange - opretDagligSkaevOrdination
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,01,01), LocalDate.of(2022,01,05), p1);
        Laegemiddel l1 = new Laegemiddel("Fucidin", 0.025,0.025,0.025,"Styk");
        d1.createDosis(LocalTime.of(9,00),2);


        ArrayList<LocalTime> tid = new ArrayList<>();
        ArrayList<Double> antal = new ArrayList<>();


        for (int i = 0; i < d1.getDoses().size(); i++) {
            tid.add(d1.getDoses().get(i).getTid());
            antal.add(d1.getDoses().get(i).getAntal());
        }

        antal.add(3.0);

        LocalTime[] tidsListe = tid.toArray(new LocalTime[0]);
        double[] antalEnheder = Arrays.stream(antal.stream().mapToDouble(d -> d).toArray()).toArray();


        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class,() -> {
            controller.opretDagligSkaevOrdination(d1.getStartDen(),d1.getSlutDen(),p1,l1, tidsListe,antalEnheder);
        });

        assertEquals(exception.getMessage(), "Klokkeslet og antal enheder er forskellige");

    }


    @org.junit.jupiter.api.Test
    void TC3_opretDagligSkaevOrdination_DatoGyldig_klokkelæstGyldig_IngenAntalEnheder() {

        //Arrange - opretDagligSkaevOrdination
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,01,01), LocalDate.of(2022,01,05), p1);
        Laegemiddel l1 = new Laegemiddel("Fucidin", 0.025,0.025,0.025,"Styk");
        d1.createDosis(LocalTime.of(9,00),2);


        ArrayList<LocalTime> tid = new ArrayList<>();
        ArrayList<Double> antal = new ArrayList<>();


        for (int i = 0; i < d1.getDoses().size(); i++) {
            tid.add(d1.getDoses().get(i).getTid());
            antal.add(d1.getDoses().get(i).getAntal());
        }

        tid.add(LocalTime.of(12,00));

        LocalTime[] tidsListe = tid.toArray(new LocalTime[0]);
        double[] antalEnheder = Arrays.stream(antal.stream().mapToDouble(d -> d).toArray()).toArray();


        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class,() -> {
            controller.opretDagligSkaevOrdination(d1.getStartDen(),d1.getSlutDen(),p1,l1, tidsListe,antalEnheder);
        });

        assertEquals(exception.getMessage(), "Klokkeslet og antal enheder er forskellige");
    }


    @org.junit.jupiter.api.Test
    void TC4_opretDagligSkaevOrdination_DatoErUgyldig_klokkeslæt_indtastet_AntalEnheder() {

        //Arrange - opretDagligSkaevOrdination
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,01,05), LocalDate.of(2022,01,01), p1);
        Laegemiddel l1 = new Laegemiddel("Fucidin", 0.025,0.025,0.025,"Styk");
        d1.createDosis(LocalTime.of(9,00),2);
        d1.createDosis(LocalTime.of(12,00),3);


        ArrayList<LocalTime> tid = new ArrayList<>();
        ArrayList<Double> antal = new ArrayList<>();


        for (int i = 0; i < d1.getDoses().size(); i++) {
            tid.add(d1.getDoses().get(i).getTid());
            antal.add(d1.getDoses().get(i).getAntal());
        }

        LocalTime[] tidsListe = tid.toArray(new LocalTime[0]);
        double[] antalEnheder = Arrays.stream(antal.stream().mapToDouble(d -> d).toArray()).toArray();


        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class,() -> {
            controller.opretDagligSkaevOrdination(d1.getStartDen(),d1.getSlutDen(),p1,l1, tidsListe,antalEnheder);
        });

        assertEquals(exception.getMessage(), "Slutdatoen er før startdatoen");
    }



    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt() {
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn() {
    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }

    @org.junit.jupiter.api.Test
    void getAllPatienter() {
    }

    @org.junit.jupiter.api.Test
    void getAllLaegemidler() {
    }

    @org.junit.jupiter.api.Test
    void opretPatient() {
    }

    @org.junit.jupiter.api.Test
    void opretLaegemiddel() {
    }

    @org.junit.jupiter.api.Test
    void createSomeObjects() {
    }
}