package hantczak.studentDataStorage.average.domain;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeFacade;
import hantczak.studentDataStorage.grade.domain.GradeFacadeConfiguration;
import hantczak.studentDataStorage.grade.domain.GradeScale;
import hantczak.studentDataStorage.student.domain.Gender;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentFacade;
import hantczak.studentDataStorage.student.domain.StudentFacadeConfiguration;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentAverageFacadeTest {
    private StudentFacade studentFacade;
    private GradeFacade gradeFacade;
    private StudentAverageFacade studentAverageFacade;

    @BeforeEach
    void init() {
        studentFacade = new StudentFacadeConfiguration().studentFacade();
        gradeFacade = new GradeFacadeConfiguration().gradeFacade(studentFacade);
        studentAverageFacade = new StudentAverageFacadeConfiguration().studentAverageFacade(gradeFacade, studentFacade);
    }

    @Nested
    @DisplayName("getAllAverages tests")
    class getAllAveragesTests {

        @Test
        @DisplayName("Should return empty list")
        void shouldReturnEmptyList() {
            //given

            //when

            //then
            assertEquals(0, studentAverageFacade.getAllAverages().size());
        }

        @Test
        @DisplayName("Should return two averages")
        void shouldReturnTwoAverages() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);
            Student student2 = new Student(2L, "b", "b@examplemail.com", LocalDate.parse("2008-01-01"), 13, Gender.MALE);
            studentFacade.addStudent(student2);

            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);
            gradeFacade.addGrade(grade1);
            Grade grade2 = new Grade(2, GradeScale.EXCELLENT, 2L, LocalDate.parse("2021-01-01"), 1);
            gradeFacade.addGrade(grade2);
            Grade grade3 = new Grade(3, GradeScale.FAIL, 1L, LocalDate.parse("2019-01-01"), 3);
            gradeFacade.addGrade(grade3);

            //when

            //then
            assertAll(
                    () -> assertEquals(2, studentAverageFacade.getAllAverages().size()),
                    () -> assertEquals(2.2, studentAverageFacade.getAllAverages().get(0).getAverage()),
                    () -> assertEquals(6, studentAverageFacade.getAllAverages().get(1).getAverage())
            );
        }
    }

    @Nested
    @DisplayName("getStudentAverage tests")
    class getStudentAverageTests {

        @Test
        @DisplayName("Should return empty optional")
        void shouldReturnEmptyList() {
            //given

            //when
            Optional<StudentAverage> outputOptional = studentAverageFacade.getStudentAverage(1L);
            //then
            if (outputOptional.isPresent()) {
                fail();
            }
        }

        @Test
        @DisplayName("Should return one average")
        void shouldReturnOneAverage() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 1);
            gradeFacade.addGrade(grade1);
            Grade grade2 = new Grade(2, GradeScale.EXCELLENT, 1L, LocalDate.parse("2021-01-01"), 2);
            gradeFacade.addGrade(grade2);
            Grade grade3 = new Grade(3, GradeScale.FAIL, 1L, LocalDate.parse("2019-01-01"), 2);
            gradeFacade.addGrade(grade3);

            //when

            //then
            assertAll(
                    () -> assertEquals(1, studentAverageFacade.getAllAverages().size()),
                    () -> assertEquals(3.6, studentAverageFacade.getAllAverages().get(0).getAverage())
            );
        }
    }

    @Test
    @DisplayName("deleteAverage test")
    void deleteAverageTest() {
        //given
        Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
        studentFacade.addStudent(student1);

        Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 1);
        gradeFacade.addGrade(grade1);
        Grade grade2 = new Grade(2, GradeScale.EXCELLENT, 1L, LocalDate.parse("2021-01-01"), 2);
        gradeFacade.addGrade(grade2);
        Grade grade3 = new Grade(3, GradeScale.FAIL, 1L, LocalDate.parse("2019-01-01"), 2);
        gradeFacade.addGrade(grade3);

        Assumptions.assumeTrue(studentAverageFacade.getStudentAverage(1L).isPresent());
        //when
        studentAverageFacade.deleteAverage(1);
        Optional<StudentAverage> outputAverage = studentAverageFacade.getStudentAverage(1L);
        //then
        if(outputAverage.isPresent()){
            fail();
        }
    }
}