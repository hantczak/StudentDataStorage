package com.example.demo.student.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import com.example.demo.average.domain.StudentAverageFacadeConfiguration;
import com.example.demo.grade.domain.*;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentFacadeTest {
    private StudentFacade studentFacade;

    @BeforeEach
    void init() {
        studentFacade = new StudentFacadeConfiguration().studentFacade();
    }

    @Nested
    @DisplayName("Get all students sorted tests")
    class getAllStudentsSortedTests {
        private Student student1;
        private Student student2;
        private Student student3;

        @BeforeEach
        void init(){
            student1 = new Student(1L, "b", "b@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);
            student2 = new Student(2L, "a", "a@examplemail.com", LocalDate.parse("2008-01-01"), 13, Gender.MALE);
            studentFacade.addStudent(student2);
            student3 = new Student(3L, "c", "c@examplemail.com", LocalDate.parse("2009-01-01"), 12, Gender.MALE);
            studentFacade.addStudent(student3);
        }

        @Test
        @DisplayName("Should throw invalidStudentSortTypeException")
        void shouldThrowExceptionWithInvalidSortType() {
            //given

            //when

            //then
            assertThrows(InvalidStudentSortTypeException.class, () -> studentFacade.getSortedStudents("ABC"));
        }

        @Test
        @DisplayName("Should return empty list of students")
        void shouldReturnEmptyListOfStudents() {
            //given
            StudentFacade studentFacade = new StudentFacadeConfiguration().studentFacade();

            //when

            //then
            assertEquals(0, studentFacade.getAllStudents().size());
        }

        @Test
        @DisplayName("Should return students sorted ascending by name")
        void shouldReturnStudentsSortedAscendingByName() {
            //given
            List<Student> sortedStudents = new ArrayList<>();
            sortedStudents.add(student2);
            sortedStudents.add(student1);
            sortedStudents.add(student3);

            //when
            List<Student> sortedStudentsOutput = studentFacade.getSortedStudents("NAME_ASC");

            //then
            assertIterableEquals(sortedStudents, sortedStudentsOutput);
        }

        @Test
        @DisplayName("Should return students sorted descending by name")
        void shouldReturnStudentsSortedDescendingByName() {
            //given
            List<Student> sortedStudents = new ArrayList<>();
            sortedStudents.add(student3);
            sortedStudents.add(student1);
            sortedStudents.add(student2);

            //when
            List<Student> sortedStudentsOutput = studentFacade.getSortedStudents("NAME_DSC");

            //then
            assertIterableEquals(sortedStudents, sortedStudentsOutput);
        }


        @Test
        @DisplayName("Should return students sorted ascending by age")
        void shouldReturnStudentsSortedAscendingByAge() {
            //given
            List<Student> sortedStudents = new ArrayList<>();
            sortedStudents.add(student3);
            sortedStudents.add(student2);
            sortedStudents.add(student1);

            //when
            List<Student> sortedStudentsOutput = studentFacade.getSortedStudents("AGE_ASC");

            //then
            assertIterableEquals(sortedStudents, sortedStudentsOutput);
        }

        @Test
        @DisplayName("Should return students sorted descending by age")
        void shouldReturnStudentsDescendingByAge() {
            //given
            List<Student> sortedStudents = new ArrayList<>();
            sortedStudents.add(student1);
            sortedStudents.add(student2);
            sortedStudents.add(student3);

            //when
            List<Student> sortedStudentsOutput = studentFacade.getSortedStudents("AGE_DSC");

            //then
            assertIterableEquals(sortedStudents, sortedStudentsOutput);
        }
    }

    @Nested
    @DisplayName("getStudent tests")
    class GetStudentTests {

        @Test
        @DisplayName("Should return student")
        void shouldReturnStudent() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            //when
            Student output = studentFacade.getStudent(1).get();

            //then
            assertEquals(student1, output);
        }

        @Test
        @DisplayName("Should return empty optional if student is not present.")
        void shouldReturnEmptyOptional() {
            //given

            //when
            Optional<Student> output = studentFacade.getStudent(1);

            //then
            if (output.isPresent()) {
                fail();
            }
        }
    }

    @Nested
    @DisplayName("addStudent tests")
    class AddStudentTests {

        @Test
        @DisplayName("Should add Student")
        void shouldAddStudent() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);

            //when
            studentFacade.addStudent(student1);

            //then
            Student output = studentFacade.getStudent(1L).get();
            assertEquals(student1, output);
        }

        @Test
        @DisplayName("addStudent test using argument captor")
        void shouldAddStudentCaptor() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            StudentRepository studentRepository = mock(StudentRepository.class);
            studentFacade = new StudentFacadeConfiguration().studentFacade(studentRepository);

            //when
            studentFacade.addStudent(student1);
            ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);

            //then
            verify(studentRepository, times(1)).addStudent(argumentCaptor.capture());
            assertEquals(student1, argumentCaptor.getValue());
        }
    }

    @Nested
    @DisplayName("Exception should be thrown when")
    class InvalidStudentExceptionTest {

        @Test
        @DisplayName("Email field does not contain '@' sign.")
        void shouldThrowExceptionWhenEmailHasNoAt() {
            //given
            Student student1 = new Student(1L, "a", "examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);

            //when
            //then
            assertThrows(InvalidStudentException.class, () -> studentFacade.addStudent(student1));
        }

        @Test
        @DisplayName("Email field contains invalid domain.")
        void shouldThrowExceptionWhenEmailHasInvalidDomain() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.abc", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);

            //when
            //then
            assertThrows(InvalidStudentException.class, () -> studentFacade.addStudent(student1));
        }

        @Test
        @DisplayName("Student age is greater than 18.")
        void shouldThrowExceptionWhenStudentIsOlderThan18() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2000-01-01"), 21, Gender.FEMALE);

            //when
            //then
            assertThrows(InvalidStudentException.class, () -> studentFacade.addStudent(student1));
        }

        @Test
        @DisplayName("Student age does not match his age from birth date.")
        void shouldThrowExceptionWhenAgeDoesNotMatchDOB() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2010-01-01"), 16, Gender.FEMALE);

            //when
            //then
            assertThrows(InvalidStudentException.class, () -> studentFacade.addStudent(student1));
        }

        @Test
        @DisplayName("Gender field is left null.")
        void shouldThrowExceptionWhenGenderIsNull() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, null);

            //when
            //then
            assertThrows(InvalidStudentException.class, () -> studentFacade.addStudent(student1));
        }

        @Test
        @DisplayName("Student ID is set to 0.")
        void shouldThrowExceptionWhenStudentIdIsZero() {
            //given
            Student student1 = new Student(0L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);

            //when
            //then
            assertThrows(InvalidStudentException.class, () -> studentFacade.addStudent(student1));
        }

        @Test
        @DisplayName("Student ID is lower than 0.")
        void shouldThrowExceptionWhenStudentIdIsLowerThanZero() {
            //given
            Student student1 = new Student(-5L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);

            //when
            //then
            assertThrows(InvalidStudentException.class, () -> studentFacade.addStudent(student1));
        }
    }

    @Nested
    @DisplayName("deleteStudent tests")
    class deleteStudentTests {

        @Test
        @DisplayName("deleteStudent should delete student.")
        void shouldDeleteStudent() {
            //given
            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            studentFacade.addStudent(student1);

            //when
            studentFacade.deleteStudentAndHisGrades(1);

            //then
            if (studentFacade.getStudent(1).isPresent()) {
                fail();
            }
        }

        @Test
        @DisplayName("deleteStudent call should result in deleting his grades and his average.")
        void deleteStudentShouldDeleteGradesAndAverage() {
            //given
            GradeFacade gradeFacade = new GradeFacadeConfiguration().gradeFacade(studentFacade);
            StudentAverageFacade studentAverageFacade = new StudentAverageFacadeConfiguration().studentAverageFacade(gradeFacade, studentFacade);

            Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
            Grade grade1 = new Grade(1, GradeScale.GOOD, 1L, LocalDate.parse("2020-01-01"), 2);

            studentFacade.addStudent(student1);
            gradeFacade.addGrade(grade1);

            Assumptions.assumeFalse(gradeFacade.getStudentGrades(1L).isEmpty());
            Assumptions.assumeTrue(studentAverageFacade.getStudentAverage(1L).isPresent());

            //when
            studentFacade.deleteStudentAndHisGrades(1L);

            //then
            assertAll(
                    () -> assertEquals(0, gradeFacade.getStudentGrades(1L).size()),
                    () -> assertNull(studentAverageFacade.getStudentAverage(1L).orElse(null))
            );
        }

    }

    @Test
    @DisplayName("updateStudent should update student.")
    void shouldUpdateStudent() {
        //given
        Student student1 = new Student(1L, "a", "a@examplemail.com", LocalDate.parse("2005-01-01"), 16, Gender.FEMALE);
        studentFacade.addStudent(student1);

        Student updatedStudent = new Student(1L, "b", "b@examplemail.com", LocalDate.parse("2010-01-01"), 11, Gender.MALE);
        Assumptions.assumeFalse(student1.equals(updatedStudent));

        //when
        studentFacade.updateStudentData(1L, updatedStudent);

        //then
        assertEquals(updatedStudent, studentFacade.getStudent(1).get());
    }
}