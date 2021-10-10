package hantczak.studentDataStorage.grade.domain;

import hantczak.studentDataStorage.student.domain.Gender;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentFacade;
import hantczak.studentDataStorage.student.domain.StudentFacadeConfiguration;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GradeFacadeTest {
    GradeFacade gradeFacade;
    StudentFacade studentFacade;

    @BeforeEach
    void init() {
        studentFacade = new StudentFacadeConfiguration().buildOnInMemoryRepo();
        gradeFacade = new GradeFacadeConfiguration().buildOnInMemoryRepo(studentFacade);
    }

    @Nested
    @DisplayName("Get all grades sorted tests")
    class getAllGradesSortedTests {
        private Grade grade1;
        private Grade grade2;
        private Grade grade3;

        @BeforeEach
        void init() {
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);
            Student student2 = new Student(2L, "b", "b@examplemail.com", LocalDate.parse("2008-01-01"), 13, Gender.MALE);
            studentFacade.addStudent(student2);

            grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);
            gradeFacade.addGrade(grade1);
            grade2 = new Grade(2, GradeScale.EXCELLENT, 2L, LocalDate.parse("2021-01-01"), 1);
            gradeFacade.addGrade(grade2);
            grade3 = new Grade(3, GradeScale.FAIL, 1L, LocalDate.parse("2019-01-01"), 1);
            gradeFacade.addGrade(grade3);
        }

        @Test
        @DisplayName("Should throw invalidGradeSortTypeException")
        void shouldThrowExceptionWithInvalidSortType() {
            //given

            //when

            //then
            assertThrows(InvalidGradeSortTypeException.class, () -> gradeFacade.getSortedGradesForOneStudent(1L, "ABC", 0, 20));
        }

        @Test
        @DisplayName("Should return empty list of grades")
        void shouldReturnEmptyListOfGrades() {
            //given
            StudentFacade studentFacade = new StudentFacadeConfiguration().buildOnInMemoryRepo();
            GradeFacade gradeFacade = new GradeFacadeConfiguration().buildOnInMemoryRepo(studentFacade);
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            //when

            //then
            assertEquals(0, gradeFacade.getStudentGrades(1).size());
        }

        @Test
        @DisplayName("Should return grades sorted ascending by value")
        void shouldReturnGradesSortedAscendingByValue() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade3);
            sortedGrades.add(grade1);
            sortedGrades.add(grade2);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getAllGradesSorted("VALUE_ASC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }

        @Test
        @DisplayName("Should return grades sorted descending by value")
        void shouldReturnGradesSortedDescendingByValue() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade2);
            sortedGrades.add(grade1);
            sortedGrades.add(grade3);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getAllGradesSorted("VALUE_DSC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }

        @Test
        @DisplayName("Should return grades sorted ascending by insertion date")
        void shouldReturnGradesSortedAscendingByInsertionDate() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade3);
            sortedGrades.add(grade1);
            sortedGrades.add(grade2);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getAllGradesSorted("INSERTION_DATE_ASC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }

        @Test
        @DisplayName("Should return grades sorted descending by insertion date")
        void shouldReturnGradesSortedDescendingByInsertionDate() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade2);
            sortedGrades.add(grade1);
            sortedGrades.add(grade3);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getAllGradesSorted("INSERTION_DATE_DSC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }

        @Test
        @DisplayName("Should return middle grade sorted by insertion date with offset 1 and limit 1.")
        void shouldReturnMiddleGradeSortedByInsertionDateWithOffset1AndLimit1() {
            //given

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getAllGradesSorted("INSERTION_DATE_DSC", 1, 1);

            //then
            assertEquals(4, sortedGradesOutput.get(0).getGradeValue());
        }
    }

    @Nested
    @DisplayName("Get sorted grades for one student tests")
    class getSortedGradesForOneStudentTests {
        private Grade grade1;
        private Grade grade2;
        private Grade grade3;

        @BeforeEach
        void init() {
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);
            gradeFacade.addGrade(grade1);
            grade2 = new Grade(2, GradeScale.EXCELLENT, 1L, LocalDate.parse("2021-01-01"), 1);
            gradeFacade.addGrade(grade2);
            grade3 = new Grade(3, GradeScale.FAIL, 1L, LocalDate.parse("2019-01-01"), 1);
            gradeFacade.addGrade(grade3);
        }


        @Test
        @DisplayName("Should throw invalidGradeSortTypeException")
        void shouldThrowExceptionWithInvalidSortType() {
            //given

            //when

            //then
            assertThrows(InvalidGradeSortTypeException.class, () -> gradeFacade.getSortedGradesForOneStudent(1L, "ABC", 0, 20));
        }

        @Test
        @DisplayName("Should return empty list of grades")
        void shouldReturnEmptyListOfGrades() {
            //given
            StudentFacade studentFacade = new StudentFacadeConfiguration().buildOnInMemoryRepo();
            GradeFacade gradeFacade = new GradeFacadeConfiguration().buildOnInMemoryRepo(studentFacade);
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            //when

            //then
            assertEquals(0, gradeFacade.getStudentGrades(1).size());
        }

        @Test
        @DisplayName("Should return grades sorted ascending by value")
        void shouldReturnGradesSortedAscendingByValue() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade3);
            sortedGrades.add(grade1);
            sortedGrades.add(grade2);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getSortedGradesForOneStudent(1L, "VALUE_ASC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }

        @Test
        @DisplayName("Should return grades sorted descending by value")
        void shouldReturnGradesSortedDescendingByValue() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade2);
            sortedGrades.add(grade1);
            sortedGrades.add(grade3);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getSortedGradesForOneStudent(1L, "VALUE_DSC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }

        @Test
        @DisplayName("Should return grades sorted ascending by insertion date")
        void shouldReturnGradesSortedAscendingByInsertionDate() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade3);
            sortedGrades.add(grade1);
            sortedGrades.add(grade2);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getSortedGradesForOneStudent(1L, "INSERTION_DATE_ASC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }

        @Test
        @DisplayName("Should return grades sorted descending by insertion date")
        void shouldReturnGradesSortedDescendingByInsertionDate() {
            //given
            List<Grade> sortedGrades = new ArrayList<>();
            sortedGrades.add(grade2);
            sortedGrades.add(grade1);
            sortedGrades.add(grade3);

            //when
            List<Grade> sortedGradesOutput = gradeFacade.getSortedGradesForOneStudent(1L, "INSERTION_DATE_DSC", 0, 20);

            //then
            assertIterableEquals(sortedGrades, sortedGradesOutput);
        }
    }

    @Nested
    @DisplayName("getStudentGrades tests")
    class getStudentGradesTests {

        @Test
        @DisplayName("Should return grade list")
        void shouldReturnGradeList() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);
            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);
            Grade grade2 = new Grade(2, GradeScale.EXCELLENT, 1L, LocalDate.parse("2021-01-01"), 1);
            gradeFacade.addGrade(grade1);
            gradeFacade.addGrade(grade2);
            List<Grade> inputGradeList = new ArrayList<>();
            inputGradeList.add(grade1);
            inputGradeList.add(grade2);

            //when
            List<Grade> outputGradeList = gradeFacade.getStudentGrades(1L);

            //then
            assertIterableEquals(inputGradeList, outputGradeList);
        }

        @Test
        @DisplayName("Should return empty optional if student does not exist.")
        void shouldReturnEmptyOptionalIfStudentDoesNotExist() {
            //given

            //when
            List<Grade> output = gradeFacade.getStudentGrades(1L);

            //then
            if (!(output.isEmpty())) {
                fail();
            }
        }
    }

    @Nested
    @DisplayName("addGrade tests")
    class addGradeTests {

        @Test
        @DisplayName("Should add grade.")
        void shouldAddGrade() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);
            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);

            //when
            gradeFacade.addGrade(grade1);

            //then
            assertEquals(grade1, gradeFacade.getStudentGrades(1).get(0));
        }

        @Test
        @DisplayName("Should add grade using argument captor")
        void shouldAddGradeCaptor() {
            //given
            GradeRepository gradeRepository = mock(GradeRepository.class);
            GradeFacade gradeFacade = new GradeFacadeConfiguration().gradeFacade(studentFacade, gradeRepository);
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);
            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);

            //when
            gradeFacade.addGrade(grade1);
            ArgumentCaptor<Grade> argumentCaptor = ArgumentCaptor.forClass(Grade.class);

            //then
            verify(gradeRepository, times(1)).addGrade(argumentCaptor.capture());
            assertEquals(grade1, argumentCaptor.getValue());
        }
    }

    @Nested
    @DisplayName("Exception should be thrown when")
    class InvalidStudentExceptionTest {

        @Test
        @DisplayName("Student with given ID does not exist.")
        void shouldThrowExceptionWhenStudentDoesNotExist() {
            //given
            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);

            //when

            //then
            assertThrows(InvalidGradeException.class, () -> gradeFacade.addGrade(grade1));
        }

        @Test
        @DisplayName("Value of 'weight' field is greater than 3.")
        void shouldThrowExceptionWhenWeightIsGraterThanThree() {
            //given
            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 5);
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            //when

            //then
            assertThrows(InvalidGradeException.class, () -> gradeFacade.addGrade(grade1));
        }

        @Test
        @DisplayName("Insertion date year value is lower than 2015.")
        void shouldThrowExceptionWhenYearValueIsLowerThan2015() {
            //given
            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2001-01-01"), 2);
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            //when

            //then
            assertThrows(InvalidGradeException.class, () -> gradeFacade.addGrade(grade1));
        }

        @Test
        @DisplayName("Grade ID is lower than 0.")
        void shouldThrowExceptionWhenGradeIdIsLowerThanZero() {
            //given
            Grade grade1 = new Grade(-3, GradeScale.GOOD, 1L, LocalDate.parse("2018-01-01"), 2);
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            //when

            //then
            assertThrows(InvalidGradeException.class, () -> gradeFacade.addGrade(grade1));
        }
    }

    @Test
    @DisplayName("deleteGrade test")
    void shouldDeleteGrade() {
        //given
        Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
        studentFacade.addStudent(student1);
        Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2018-01-01"), 2);
        gradeFacade.addGrade(grade1);


        //when
        gradeFacade.deleteGrade(1L, 1);

        //then
        List<Grade> outputList = gradeFacade.getStudentGrades(1L);
        assertEquals(0, outputList.size());
    }

    @Test
    @DisplayName("updateGrade test")
    void shouldUpdateGrade() {
        //given
        Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
        studentFacade.addStudent(student1);
        Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2018-01-01"), 2);
        gradeFacade.addGrade(grade1);

        Grade updatedGrade = new Grade(1, GradeScale.EXCELLENT, 1L, LocalDate.parse("2020-01-01"), 3);
        Assumptions.assumeFalse(grade1.equals(updatedGrade));

        //when
        gradeFacade.updateGrade(updatedGrade, 1);

        //then
        List<Grade> outputList = gradeFacade.getStudentGrades(1L);
        assertEquals(updatedGrade, outputList.get(0));
    }
}