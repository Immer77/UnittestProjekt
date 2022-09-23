package controller;

import net.bytebuddy.asm.Advice;
import ordination.DagligFast;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import ordination.DagligSkaev;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = new Controller();

    Patient donnaSummer;
    Patient vladimirPutin;
    Patient johnHammond;
    Patient larsLarsen;
    Laegemiddel morfin;
    Laegemiddel fentanyl;

    @BeforeEach
    void setUp() {

        // PATIENT
        this.donnaSummer = controller.opretPatient("123494-7890", "Donna Summer", 26);
        this.vladimirPutin = controller.opretPatient("121260-3434", "Vladimir Putin", 80);
        this.johnHammond = controller.opretPatient("010150-4056", "John Hammond", 100);
        this.larsLarsen = controller.opretPatient("060848-2222", "Lars Larsen", 12);

        // LAEGEMIDDEL
        this.morfin = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        this.fentanyl = controller.opretLaegemiddel("Fentanyl", 0.1, 0.15, 0.16, "Styk");


    }

    @Test
    void TC1_opretPNOrdination() {
        // ARRANGE
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 4);
        int antalEnheder = 10;

        // ACT
        PN pn = controller.opretPNOrdination(start, slut, donnaSummer, laegemiddel, antalEnheder);

        // ASSERT
        assertEquals(start, pn.getStartDen());
        assertEquals(slut, pn.getSlutDen());
        assertEquals(antalEnheder, pn.getAntalEnheder());
        assertTrue(controller.getAllLaegemidler().contains(laegemiddel));
        assertTrue(controller.getAllPatienter().contains(donnaSummer));

    }

    @Test
    void TC2_opretPNOrdination_medFejl() {
        // ARRANGE
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 01, 31);
        int antalEnheder = 10;

        // ACT
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.opretPNOrdination(start, slut, donnaSummer, laegemiddel, antalEnheder));

        // ASSERT
        assertEquals("Startdato kan ikke være efter slutdato. ", exception.getMessage());
    }


    @Test
    void opretDagligFastOrdination_TC1() {
        // Arrange
        int morgenAntal = 1;
        int middagAntal = 1;
        int aftenAntal = 1;
        int natAntal = 1;
        Patient patient = new Patient("123494-7890", "Donna Summer", 66.5);
        Laegemiddel laegemiddel = new Laegemiddel("Morfin", 0.1,0.15,0.16, "Styk");
        LocalDate startdato = LocalDate.of(2022,2,1);
        LocalDate slutdato = LocalDate.of(2022,2,5);
        DagligFast dagligFast = new DagligFast(startdato,slutdato,patient,morgenAntal,middagAntal,aftenAntal,natAntal);

        // Act
        dagligFast.setLaegemiddel(laegemiddel);

        // Assert
        assertEquals(morgenAntal,dagligFast.getDoses()[0].getAntal());
        assertEquals(middagAntal,dagligFast.getDoses()[1].getAntal());
        assertEquals(aftenAntal,dagligFast.getDoses()[2].getAntal());
        assertEquals(natAntal,dagligFast.getDoses()[3].getAntal());
        assertEquals(startdato,dagligFast.getStartDen());
        assertEquals(slutdato,dagligFast.getSlutDen());
        assertEquals(laegemiddel,dagligFast.getLaegemiddel());

    }

    @Test
    void opretDagligFastOrdination_TC2_Error() {
        // Arrange
        int morgenAntal = 1;
        int middagAntal = 1;
        int aftenAntal = 1;
        int natAntal = 1;
        Patient patient = new Patient("123494-7890", "Donna Summer", 66.5);
        Laegemiddel laegemiddel = new Laegemiddel("Morfin", 0.1,0.15,0.16, "Styk");
        LocalDate startdato = LocalDate.of(2022,2,1);
        LocalDate slutdato = LocalDate.of(2022,1,1);

        // Act
        Exception exception = assertThrows(RuntimeException.class,() -> {
           Controller.getController().opretDagligFastOrdination(startdato,slutdato,patient,laegemiddel,morgenAntal,middagAntal,aftenAntal,natAntal);
        });

        // Assert
       assertEquals("Startdato er efter slutdato",exception.getMessage());

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



    @Test
    void TC1_ordination_PNanvendt() {

        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 6);
        int antalEnheder = 10;

        // ACT
        PN pn = new PN(start, slut, donnaSummer, antalEnheder);
        controller.ordinationPNAnvendt(pn, start);

        // ASSERT
        assertTrue(pn.givDosis(start));

    }

    @Test
    void TC2_ordination_PN_fejl() {

        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 01, 31);
        int antalEnheder = 10;

        // ACT
        PN pn = new PN(start, slut, donnaSummer, antalEnheder);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                controller.ordinationPNAnvendt(pn, start));

        // ASSERT
        assertEquals("Datoen ikke er indenfor ordinationens gyldighedsperiode", exception.getMessage());

    }

    @Test
    void TC1_anbefaletDosisPrDoegn_25_vægt() {

        // ARRANGE
        DecimalFormat df = new DecimalFormat("0.00");
        donnaSummer.setVaegt(25);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        double anbefalet = controller.anbefaletDosisPrDoegn(donnaSummer, laegemiddel);

        // ACT & ASSERT
        assertEquals(df.format(2.5), df.format(anbefalet));

    }

    @Test
    void TC2_anbefaletDosisPrDoegn_26_vægt() {

        // ARRANGE
        DecimalFormat df = new DecimalFormat("0.00");
        donnaSummer.setVaegt(26);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        double anbefalet = controller.anbefaletDosisPrDoegn(donnaSummer, laegemiddel);

        // ACT & ASSERT
        assertEquals(df.format(3.9), df.format(anbefalet));
    }

    @Test
    void TC3_anbefaletDosisPrDoegn_70_vægt() {

        // ARRANGE
        DecimalFormat df = new DecimalFormat("0.00");
        donnaSummer.setVaegt(70);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        double anbefalet = controller.anbefaletDosisPrDoegn(donnaSummer, laegemiddel);

        // ACT & ASSERT
        assertEquals(df.format(10.5), df.format(anbefalet));

    }

    @Test
    void TC4_anbefaletDosisPrDoegn_119_vægt() {

        // ARRANGE
        DecimalFormat df = new DecimalFormat("0.00");
        donnaSummer.setVaegt(119);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        double anbefalet = controller.anbefaletDosisPrDoegn(donnaSummer, laegemiddel);

        // ACT & ASSERT
        assertEquals(df.format(17.85), df.format(anbefalet));

    }

    @Test
    void TC5_anbefaletDosisPrDoegn_120_vægt() {

        // ARRANGE
        DecimalFormat df = new DecimalFormat("0.00");
        donnaSummer.setVaegt(120);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Morfin", 0.1, 0.15, 0.16, "Styk");
        double anbefalet = controller.anbefaletDosisPrDoegn(donnaSummer, laegemiddel);

        // ACT & ASSERT
        assertEquals(df.format(18.00), df.format(anbefalet));

    }


    @Test
    void TC1_VAEGT_10_til_70_morfin() {


        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 4);
        int antalEnheder = 10;
        PN PN_1 = controller.opretPNOrdination(start, slut, vladimirPutin, morfin, antalEnheder);
        PN PN_2 = controller.opretPNOrdination(start, slut.plusDays(3), johnHammond, morfin, antalEnheder);
        PN PN_3 = controller.opretPNOrdination(start, slut.plusDays(10), larsLarsen, morfin, antalEnheder);
        PN PN_4 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, morfin, antalEnheder);
        PN PN_5 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, fentanyl, antalEnheder);

        // ACT
        vladimirPutin.addOrdination(PN_1);
        johnHammond.addOrdination(PN_2);
        larsLarsen.addOrdination(PN_3);
        donnaSummer.addOrdination(PN_4);
        donnaSummer.addOrdination(PN_5);

        // ASSERT

        assertEquals(2, controller.antalOrdinationerPrVægtPrLægemiddel(10, 70, morfin));

    }

    @Test
    void TC2_VAEGT_70_til_80_morfin() {


        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 4);
        int antalEnheder = 10;
        PN PN_1 = controller.opretPNOrdination(start, slut, vladimirPutin, morfin, antalEnheder);
        PN PN_2 = controller.opretPNOrdination(start, slut.plusDays(3), johnHammond, morfin, antalEnheder);
        PN PN_3 = controller.opretPNOrdination(start, slut.plusDays(10), larsLarsen, morfin, antalEnheder);
        PN PN_4 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, morfin, antalEnheder);
        PN PN_5 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, fentanyl, antalEnheder);

        // ACT
        vladimirPutin.addOrdination(PN_1);
        johnHammond.addOrdination(PN_2);
        larsLarsen.addOrdination(PN_3);
        donnaSummer.addOrdination(PN_4);
        donnaSummer.addOrdination(PN_5);

        // ASSERT
        assertEquals(1, controller.antalOrdinationerPrVægtPrLægemiddel(70, 80, morfin));

    }

    @Test
    void TC3_VAEGT_80_til_70_fejl() {


        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 4);
        int antalEnheder = 10;
        PN PN_1 = controller.opretPNOrdination(start, slut, vladimirPutin, morfin, antalEnheder);
        PN PN_2 = controller.opretPNOrdination(start, slut.plusDays(3), johnHammond, morfin, antalEnheder);
        PN PN_3 = controller.opretPNOrdination(start, slut.plusDays(10), larsLarsen, morfin, antalEnheder);
        PN PN_4 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, morfin, antalEnheder);
        PN PN_5 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, fentanyl, antalEnheder);

        // ACT
        vladimirPutin.addOrdination(PN_1);
        johnHammond.addOrdination(PN_2);
        larsLarsen.addOrdination(PN_3);
        donnaSummer.addOrdination(PN_4);
        donnaSummer.addOrdination(PN_5);

        // ASSERT
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.antalOrdinationerPrVægtPrLægemiddel(80, 70, morfin);
        });

        assertEquals("Slutvægten kan ikke være lavere end startvægten", exception.getMessage());

    }

    @Test
    void TC4_VAEGT_0_til_50_fejl() {


        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 4);
        int antalEnheder = 10;
        PN PN_1 = controller.opretPNOrdination(start, slut, vladimirPutin, morfin, antalEnheder);
        PN PN_2 = controller.opretPNOrdination(start, slut.plusDays(3), johnHammond, morfin, antalEnheder);
        PN PN_3 = controller.opretPNOrdination(start, slut.plusDays(10), larsLarsen, morfin, antalEnheder);
        PN PN_4 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, morfin, antalEnheder);
        PN PN_5 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, fentanyl, antalEnheder);

        // ACT
        vladimirPutin.addOrdination(PN_1);
        johnHammond.addOrdination(PN_2);
        larsLarsen.addOrdination(PN_3);
        donnaSummer.addOrdination(PN_4);
        donnaSummer.addOrdination(PN_5);

        // ASSERT

        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            controller.antalOrdinationerPrVægtPrLægemiddel(0, 50, morfin);
        });

        assertEquals("Vægt kan ikke være 0", exception1.getMessage());

    }

    @Test
    void TC5_VAEGT_1_til_100() {


        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 4);
        int antalEnheder = 10;
        PN PN_1 = controller.opretPNOrdination(start, slut, vladimirPutin, morfin, antalEnheder);
        PN PN_2 = controller.opretPNOrdination(start, slut.plusDays(3), johnHammond, morfin, antalEnheder);
        PN PN_3 = controller.opretPNOrdination(start, slut.plusDays(10), larsLarsen, morfin, antalEnheder);
        PN PN_4 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, morfin, antalEnheder);
        PN PN_5 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, fentanyl, antalEnheder);

        // ACT
        vladimirPutin.addOrdination(PN_1);
        johnHammond.addOrdination(PN_2);
        larsLarsen.addOrdination(PN_3);
        donnaSummer.addOrdination(PN_4);
        donnaSummer.addOrdination(PN_5);

        // ASSERT
        assertEquals(1, controller.antalOrdinationerPrVægtPrLægemiddel(1, 100, fentanyl));

    }

    @Test
    void TC6_VAEGT_130_til_135() {


        // ARRANGE
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 4);
        int antalEnheder = 10;
        PN PN_1 = controller.opretPNOrdination(start, slut, vladimirPutin, morfin, antalEnheder);
        PN PN_2 = controller.opretPNOrdination(start, slut.plusDays(3), johnHammond, morfin, antalEnheder);
        PN PN_3 = controller.opretPNOrdination(start, slut.plusDays(10), larsLarsen, morfin, antalEnheder);
        PN PN_4 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, morfin, antalEnheder);
        PN PN_5 = controller.opretPNOrdination(start, slut.plusDays(2), donnaSummer, fentanyl, antalEnheder);

        // ACT
        vladimirPutin.addOrdination(PN_1);
        johnHammond.addOrdination(PN_2);
        larsLarsen.addOrdination(PN_3);
        donnaSummer.addOrdination(PN_4);
        donnaSummer.addOrdination(PN_5);

        // ASSERT
        assertEquals(0, controller.antalOrdinationerPrVægtPrLægemiddel(130, 135, morfin));
    }


}