package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    /**
     CreateDosis
     */

    @org.junit.jupiter.api.Test
    void TC1_CreateDosis_kl6_antal_2 () {

        // Arrange
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,1,1), LocalDate.of(2022,1,5), p1);
        LocalTime tid = LocalTime.of(6,00);
        double antal = 2;

        // Act
        Dosis dosis = d1.createDosis(tid, antal);

        // Assert
        assertTrue(d1.getDoses().contains(dosis));
    }

    @org.junit.jupiter.api.Test
    void TC2_CreateDosis_kl9_antal_2 () {

        // Arrange
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,1,1), LocalDate.of(2022,1,5), p1);
        LocalTime tid = LocalTime.of(9,00);
        double antal = 2;

        // Act
        Dosis dosis = d1.createDosis(tid, antal);

        // Assert
        assertTrue(d1.getDoses().contains(dosis));
    }

    @org.junit.jupiter.api.Test
    void TC3_CreateDosis_kl15_antal_2 () {

        // Arrange
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,1,1), LocalDate.of(2022,1,5), p1);
        LocalTime tid = LocalTime.of(15,00);
        double antal = 2;

        // Act
        Dosis dosis = d1.createDosis(tid, antal);

        // Assert
        assertTrue(d1.getDoses().contains(dosis));
    }

    @org.junit.jupiter.api.Test
    void TC4_CreateDosis_kl15_antal_200 () {

        // Arrange
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,1,1), LocalDate.of(2022,1,5), p1);
        LocalTime tid = LocalTime.of(15,00);
        double antal = 200;

        // Act
        Dosis dosis = d1.createDosis(tid, antal);

        // Assert
        assertTrue(d1.getDoses().contains(dosis));
    }

    @org.junit.jupiter.api.Test
    void TC5_CreateDosis_kl00_antal_2 () {

        // Arrange
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,1,1), LocalDate.of(2022,1,5), p1);
        LocalTime tid = LocalTime.of(0,00);
        double antal = 2;

        // Act
        Dosis dosis = d1.createDosis(tid, antal);

        // Assert
        assertTrue(d1.getDoses().contains(dosis));
    }

    @org.junit.jupiter.api.Test
    void TC6_CreateDosis_kl9_antal_minus () {

        // Arrange
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,1,1), LocalDate.of(2022,1,5), p1);
        LocalTime tid = LocalTime.of(9,00);
        double antal = -2;

        // Act
        Dosis dosis = d1.createDosis(tid, antal);

        // Assert
        assertTrue(d1.getDoses().contains(dosis));
    }

    @org.junit.jupiter.api.Test
    void TC7_CreateDosis_kl09_antal_ligeNul () {

        // Arrange
        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new DagligSkaev(LocalDate.of(2022,1,1), LocalDate.of(2022,1,5), p1);
        LocalTime tid = LocalTime.of(9,00);
        double antal = 0;

        // Act
        Dosis dosis = d1.createDosis(tid, antal);

        // Assert
        assertTrue(d1.getDoses().contains(dosis));
    }

/**
SamletDoisis test
*/
    @org.junit.jupiter.api.Test
    void TC1_samletDosis5_dage5 () {

        //Arrange

        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new  DagligSkaev(LocalDate.of(2022, 01,01),LocalDate.of(2022,01,05), p1);
        d1.createDosis(LocalTime.of(9,00),2);
        d1.createDosis(LocalTime.of(12,00),3);

        //Act
        double actual = d1.samletDosis();
        double expected = 25;

        //Assert
        assertEquals(expected,actual);

    }

/**
DoegnDoisis test
*/

    @org.junit.jupiter.api.Test
    void TC1_doegnDosis_5 () {

        //Arrange

        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new  DagligSkaev(LocalDate.of(2022, 01,01),LocalDate.of(2022,01,05), p1);
        d1.createDosis(LocalTime.of(9,00),2);
        d1.createDosis(LocalTime.of(12,00),3);

        //Act
        double actual = d1.doegnDosis();
        double expected = 5;

        //Assert
        assertEquals(expected,actual);
    }

    @org.junit.jupiter.api.Test
    void TC2_doegnDosis_0Dosis () {

        //Arrange

        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new  DagligSkaev(LocalDate.of(2022, 01,01),LocalDate.of(2022,01,05), p1);
        d1.createDosis(LocalTime.of(9,00),0);
        d1.createDosis(LocalTime.of(12,00),0);

        //Act
        double actual = d1.doegnDosis();
        double expected = 0;

        //Assert
        assertEquals(expected,actual);
    }

    @org.junit.jupiter.api.Test
    void TC3_doegnDosis_MindreEndnul_Dosis () {

        //Arrange

        Patient p1 = new Patient("111111-1111", "Lars Larsen",  85);
        DagligSkaev d1 = new  DagligSkaev(LocalDate.of(2022, 01,01),LocalDate.of(2022,01,05), p1);
        d1.createDosis(LocalTime.of(9,00),0);
        d1.createDosis(LocalTime.of(12,00),-2);

        //Act
        double actual = d1.doegnDosis();
        double expected = -2;

        //Assert
        assertEquals(expected,actual);
    }
}