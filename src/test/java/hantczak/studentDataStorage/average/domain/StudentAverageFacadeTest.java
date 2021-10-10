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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentAverageFacadeTest {
    private StudentFacade studentFacade;
    private GradeFacade gradeFacade;
    private StudentAverageFacade studentAverageFacade;

    @BeforeEach
    void init() {
        studentFacade = new StudentFacadeConfiguration().buildOnInMemoryRepo();
        gradeFacade = new GradeFacadeConfiguration().buildOnInMemoryRepo(studentFacade);
        studentAverageFacade = new StudentAverageFacadeConfiguration().studentAverageFacade(gradeFacade, studentFacade);
    }

    @Nested
    @DisplayName("getAllAveragesSorted tests")
    class getAllAveragesSortedTests {

        @Test
        @DisplayName("Should return empty list")
        void shouldReturnEmptyList() {
            //given

            //when

            //then
            assertEquals(0, studentAverageFacade.getAllAveragesSorted("STUDENT_ID_ASC", 0, 20).size());
        }

        @Test
        @DisplayName("Should return two averages sorted by student ID")
        void shouldReturnTwoAveragesSortedByStudentId() {
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
                    () -> assertEquals(2, studentAverageFacade.getAllAveragesSorted("STUDENT_ID_ASC", 0, 20).size()),
                    () -> assertEquals(2.2, studentAverageFacade.getAllAveragesSorted("STUDENT_ID_ASC", 0, 20).get(0).getAverage()),
                    () -> assertEquals(6, studentAverageFacade.getAllAveragesSorted("STUDENT_ID_ASC", 0, 20).get(1).getAverage())
            );
        }

        @Test
        @DisplayName("Should return two averages sorted by value descending")
        void shouldReturnTwoAveragesSortedByValueDSC() {
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
            List<StudentAverage> outcomeList = studentAverageFacade.getAllAveragesSorted("VALUE_DSC", 0, 20);
            assertAll(
                    () -> assertEquals(2, outcomeList.size()),
                    () -> assertEquals(2.2, outcomeList.get(1).getAverage()),
                    () -> assertEquals(6, outcomeList.get(0).getAverage())
            );
        }

        @Test
        @DisplayName("Should return one, middle average for offset 1 and limit 1")
        void shouldReturnOneMiddleAverageWithOffset1AndLimit1() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);
            Student student2 = new Student(2L, "b", "b@examplemail.com", LocalDate.parse("2008-01-01"), 13, Gender.MALE);
            studentFacade.addStudent(student2);
            Student student3 = new Student(3L, "c", "c@examplemail.com", LocalDate.parse("2009-01-01"), 12, Gender.MALE);
            studentFacade.addStudent(student3);

            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);
            gradeFacade.addGrade(grade1);
            Grade grade2 = new Grade(2, GradeScale.EXCELLENT, 2L, LocalDate.parse("2021-01-01"), 1);
            gradeFacade.addGrade(grade2);
            Grade grade3 = new Grade(3, GradeScale.FAIL, 3L, LocalDate.parse("2019-01-01"), 3);
            gradeFacade.addGrade(grade3);

            //when

            //then
            List<StudentAverage> outcomeList = studentAverageFacade.getAllAveragesSorted("STUDENT_ID_ASC", 1, 1);
            assertAll(
                    () -> assertEquals(1, outcomeList.size()),
                    () -> assertEquals(6, outcomeList.get(0).getAverage())
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
                    () -> assertEquals(1, studentAverageFacade.getAllAveragesSorted("VALUE_ASC",0,5).size()),
                    () -> assertEquals(3.6, studentAverageFacade.getAllAveragesSorted("VALUE_ASC",0,5).get(0).getAverage())
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
        if (outputAverage.isPresent()) {
            fail();
        }
    }
}