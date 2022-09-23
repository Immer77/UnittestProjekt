package ordination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient patient;
    private DagligFast dagligFast;
    private DagligFast dagligFast1;

    @BeforeEach
    void setUp() {
        patient = new Patient("201298-2133", "Peter Immersen", 85);
        dagligFast = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 24), patient, 4, 4, 4, 4);
        dagligFast1 = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 24), patient, 4, 4, 4, 4);
    }

    @Test
    void patient_AddOrdination_TC1() {
        // Act
        patient.addOrdination(dagligFast);
        patient.addOrdination(dagligFast1);

        // Assert
        assertTrue(patient.getOrdinationer().contains(dagligFast));
        assertTrue(patient.getOrdinationer().contains(dagligFast1));
    }

    @Test
    void getOrdinationer_TC1() {
        // Arrange
        int expected = 2;

        // Act
        patient.addOrdination(dagligFast);
        patient.addOrdination(dagligFast1);

        // Assert
        assertEquals(expected,patient.getOrdinationer().size());

    }

    @Test
    void getOrdinationer_TC2() {
        // Arrange
        Patient patient1 = new Patient("192121-2121", "Lars Larsen", 67);
        int expected = 0;

        // Assert
        assertEquals(expected,patient1.getOrdinationer().size());

    }

}