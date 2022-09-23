package ordination;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


class DagligFastTest {



//    @BeforeEach
//    void setUp() {
//
//        dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 24), patient, 4, 4, 4, 4);
//    }

    @org.junit.jupiter.api.Test
    void dagligfast_Constructor_TC1() {
        int morgenAntal = 2;
        int middagAntal = 1;
        int aftenAntal = 0;
        int natAntal = 1;
        LocalDate startDato = LocalDate.of(2022,9,22);
        LocalDate slutDato = LocalDate.of(2022,9,27);
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast1 = new DagligFast(startDato,slutDato, patient, morgenAntal, middagAntal, aftenAntal,natAntal);

        assertEquals(morgenAntal,dagligFast1.getDoses()[0].getAntal());
        assertEquals(middagAntal,dagligFast1.getDoses()[1].getAntal());
        assertEquals(aftenAntal,dagligFast1.getDoses()[2].getAntal());
        assertEquals(natAntal,dagligFast1.getDoses()[3].getAntal());
        assertEquals(startDato,dagligFast1.getStartDen());
        assertEquals(slutDato,dagligFast1.getSlutDen());
    }

    @Test
    void dagligfast_Constructor_TC2() {
        int morgenAntal = 1;
        int middagAntal = 1;
        int aftenAntal = 1;
        int natAntal = 1;
        LocalDate startDato = LocalDate.of(2022,9,22);
        LocalDate slutDato = LocalDate.of(2022,9,27);
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast1 = new DagligFast(startDato,slutDato, patient, morgenAntal, middagAntal, aftenAntal,natAntal);
        assertEquals(morgenAntal,dagligFast1.getDoses()[0].getAntal());
        assertEquals(middagAntal,dagligFast1.getDoses()[1].getAntal());
        assertEquals(aftenAntal,dagligFast1.getDoses()[2].getAntal());
        assertEquals(natAntal,dagligFast1.getDoses()[3].getAntal());
        assertEquals(startDato,dagligFast1.getStartDen());
        assertEquals(slutDato,dagligFast1.getSlutDen());
    }

    @Test
    void dagligfast_Constructor_TC3() {
        int morgenAntal = 0;
        int middagAntal = 0;
        int aftenAntal = 0;
        int natAntal = 0;
        LocalDate startDato = LocalDate.of(2022,9,22);
        LocalDate slutDato = LocalDate.of(2022,9,27);
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast1 = new DagligFast(startDato,slutDato, patient, morgenAntal, middagAntal, aftenAntal,natAntal);

        assertEquals(morgenAntal,dagligFast1.getDoses()[0].getAntal());
        assertEquals(middagAntal,dagligFast1.getDoses()[1].getAntal());
        assertEquals(aftenAntal,dagligFast1.getDoses()[2].getAntal());
        assertEquals(natAntal,dagligFast1.getDoses()[3].getAntal());
        assertEquals(startDato,dagligFast1.getStartDen());
        assertEquals(slutDato,dagligFast1.getSlutDen());
    }

    @Test
    void samletDosis_TC1() {
        // Arrange
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 24), patient, 1, 1, 1, 1);
        double expectedResult = 12;

        // Act
        double actual = dagligFast.samletDosis();

        // Assert
        assertEquals(expectedResult, actual);
    }

    @Test
    void samletDosis_TC2() {
        // Arrange
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 22), patient, 0, 0, 0, 0);
        double expectedResult = 0;

        // Act
        double actual = dagligFast.samletDosis();

        // Assert
        assertEquals(expectedResult, actual);
    }

    @Test
    void samletDosis_TC3() {
        // Arrange
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 25), patient, 9, 9, 9, 9);
        double expectedResult = 144;

        // Act
        double actual = dagligFast.samletDosis();

        // Assert
        assertEquals(expectedResult, actual);
    }

    @Test
    void samletDosis_TC4() {
        // Arrange
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 21), patient, 2, 2, 2, 2);
        double expectedResult = 0;

        // Act
        double actual = dagligFast.samletDosis();

        // Assert
        assertEquals(expectedResult, actual);
    }

    //__________________________________________________________

    @Test
    void doegnDosis_ForEnDag_TC1() {
        // Arrange
        double expected = 16;
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 21), patient, 2, 2, 2, 2);

        // Act
        double actual = dagligFast.doegnDosis();

        // Assert
        assertEquals(expected,actual);

    }
    @Test
    void doegnDosis_ForEnDag_TC2(){
        // Arrange
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 24), patient, 0, 0, 0, 0);
        double expected = 0;

        // Act
        double actual = dagligFast.doegnDosis();

        // Arrange
        assertEquals(expected,actual);
    }

    @Test
    void getType() {
        Patient patient = new Patient("201297-0000", "Peter Immersen", 85);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 21), patient, 2, 2, 2, 2);
        assertEquals("Daglig Fast",dagligFast.getType()) ;
    }
}