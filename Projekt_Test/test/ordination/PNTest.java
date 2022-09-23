package ordination;

import org.junit.jupiter.api.*;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    PN pn;
    Patient patient;

    @BeforeEach
    void setup() {
        this.patient = new Patient("123494-7890", "Donna Summer", 66.5);
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 10);
        int antalEnheder = 10;
        this.pn = new PN(start, slut, patient, antalEnheder);

    }

    @Test
    void TC1_givDosis_dato_2022_02_03() {
        // ARRANGE
        int expectedOutput = 10;

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 03));

        //
        assertEquals(expectedOutput, pn.samletDosis());
    }

    @Test
    void TC2_givDosis_dato_2022_02_08() {
        // ARRANGE
        int expectedOutput = 10;

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 8));

        //
        assertEquals(expectedOutput, pn.samletDosis());
    }

    @Test
    void TC3_givDosis_dato_2022_02_04() {
        // ARRANGE
        int expectedOutput = 10;
        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 4));

        // ASSERT
        assertEquals(expectedOutput, pn.samletDosis());
    }

    @Test
    void TC4_givDosis_dato_2022_02_01() {
        // ARRANGE
        int expectedOutput = 0;
        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 01));

        // ASSERT
        assertEquals(expectedOutput, pn.samletDosis());
    }


    @Test
    void TC5_givDosis_dato_2022_02_12() {
        // ARRANGE
        int expectedOutput = 0;

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 12));

        // ASSERT
        assertEquals(expectedOutput, pn.samletDosis());
    }


    @Test
    void TC1_doegnDosis_2_Gange_1_doegn() {

        // ARRANGE
        double expectedOutput = 20;
        DecimalFormat df = new DecimalFormat("0.00");

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 04));
        pn.givDosis(LocalDate.of(2022, 02, 05));

        // ASSERT
        assertEquals(df.format(expectedOutput), df.format(pn.doegnDosis()));


    }

    @Test
    void TC2_doegnDosis_15_Gange_6_doegn() {

        // ARRANGE
        double expectedOutput = 25;
        DecimalFormat df = new DecimalFormat("0.00");

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 04));
        pn.givDosis(LocalDate.of(2022, 02, 05));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 6));
        pn.givDosis(LocalDate.of(2022, 02, 7));
        pn.givDosis(LocalDate.of(2022, 02, 8));
        pn.givDosis(LocalDate.of(2022, 02, 8));
        pn.givDosis(LocalDate.of(2022, 02, 8));
        pn.givDosis(LocalDate.of(2022, 02, 8));
        pn.givDosis(LocalDate.of(2022, 02, 9));
        pn.givDosis(LocalDate.of(2022, 02, 9));
        pn.givDosis(LocalDate.of(2022, 02, 9));
        pn.givDosis(LocalDate.of(2022, 02, 10));


        // ASSERT
        assertEquals(df.format(expectedOutput), df.format(pn.doegnDosis()));


    }

    @Test
    void TC3_doegnDosis_7_Gange_3_doegn() {

        // ARRANGE
        double expectedOutput = 23.33;
        DecimalFormat df = new DecimalFormat("0.00");

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 04));
        pn.givDosis(LocalDate.of(2022, 02, 05));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 6));
        pn.givDosis(LocalDate.of(2022, 02, 7));

        // ASSERT
        assertEquals(df.format(expectedOutput), df.format(pn.doegnDosis()));


    }

    @Test
    void getType() {
    }

    @Test
    void TC1_samletDosis_2_anvendt_15_enheder() {

        // ARRANGE
        LocalDate datoGivet = LocalDate.of(2022, 02, 05);
        int expectedOutput = 30;
        pn.setAntalEnheder(15);

        // ACT
        pn.givDosis(datoGivet);
        pn.givDosis(datoGivet);

        // ASSERT
        assertEquals(expectedOutput, pn.samletDosis());
    }

    @Test
    void TC2_samletDosis_12_anvendt_4_enheder() {

        // ARRANGE
        LocalDate datoGivet = LocalDate.of(2022, 02, 05);
        int expectedOutput = 48;
        pn.setAntalEnheder(4);

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 6));
        pn.givDosis(LocalDate.of(2022, 02, 7));
        pn.givDosis(LocalDate.of(2022, 02, 7));
        pn.givDosis(LocalDate.of(2022, 02, 7));
        pn.givDosis(LocalDate.of(2022, 02, 8));
        pn.givDosis(LocalDate.of(2022, 02, 8));
        pn.givDosis(LocalDate.of(2022, 02, 8));
        pn.givDosis(LocalDate.of(2022, 02, 9));
        pn.givDosis(LocalDate.of(2022, 02, 10));
        pn.givDosis(LocalDate.of(2022, 02, 10));

        // ASSERT
        assertEquals(expectedOutput, pn.samletDosis());


    }

    @Test
    void TC3_samletDosis_5_anvendt_8_enheder() {

        // ARRANGE
        int expectedOutput = 40;
        pn.setAntalEnheder(8);

        // ACT
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 5));
        pn.givDosis(LocalDate.of(2022, 02, 6));
        pn.givDosis(LocalDate.of(2022, 02, 7));
        pn.givDosis(LocalDate.of(2022, 02, 7));


        // ASSERT
        assertEquals(expectedOutput, pn.samletDosis());

    }


    @Test
    void TC1_antalgivet_0_givet() {

        // ARRANGE
        Patient patient1 = new Patient("123494-7890", "Donna Summer", 66.5);
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 10);
        int antalEnheder = 10;
        PN pn1 = new PN(start, slut, patient1, antalEnheder);


        // ASSERT
        assertEquals(0, pn1.getAntalGangeGivet());
    }

    @Test
    void TC2_antalgivet_2_givet() {

        // ARRANGE
        Patient patient1 = new Patient("123494-7890", "Donna Summer", 66.5);
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 10);
        int antalEnheder = 10;
        PN pn1 = new PN(start, slut, patient1, antalEnheder);

        pn1.givDosis(start);
        pn1.givDosis(slut);
        // ASSERT
        assertEquals(2, pn1.getAntalGangeGivet());
    }

    @Test
    void TC3_antalgivet_7_givet() {

        // ARRANGE
        Patient patient1 = new Patient("123494-7890", "Donna Summer", 66.5);
        LocalDate start = LocalDate.of(2022, 02, 03);
        LocalDate slut = LocalDate.of(2022, 02, 10);
        int antalEnheder = 10;
        PN pn1 = new PN(start, slut, patient1, antalEnheder);

        pn1.givDosis(start);
        pn1.givDosis(start);
        pn1.givDosis(start);
        pn1.givDosis(start);
        pn1.givDosis(slut);
        pn1.givDosis(slut);
        pn1.givDosis(slut);

        // ASSERT
        assertEquals(7, pn1.getAntalGangeGivet());
    }

    @Test
    void getAntalEnheder() {
    }

    @Test
    void setAntalEnheder() {
    }
}