package controller;

import ordination.DagligFast;
import ordination.Laegemiddel;
import ordination.Patient;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void getController() {

    }

    @org.junit.jupiter.api.Test
    void getTestController() {
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination() {
    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination_TC1() {
        // Arrange
        Patient patient = new Patient("123494-7890", "Donna Summer", 66.5);
        Laegemiddel laegemiddel = new Laegemiddel("Morfin", 0.1,0.15,0.16,"Styk");

    }

    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination() {
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