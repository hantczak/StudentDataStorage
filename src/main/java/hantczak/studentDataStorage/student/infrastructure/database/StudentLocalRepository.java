package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

public class StudentLocalRepository implements StudentRepository {

    private final Map<Long, Student> studentsMap = new HashMap<>();

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = studentsMap.values().stream()
                .collect(Collectors.toCollection(ArrayList::new));
        return studentList;
    }

    @Override
    public Optional<Student> getStudent(long studentId) {
        Optional<Student> studentOptional;
        studentOptional = Optional.ofNullable(studentsMap.get(studentId));
        return studentOptional;
    }

    @Override
    public void addStudent(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public boolean updateStudentData(long studentId, Student student) {
        Student oldStudent = null;
        if (studentsMap.containsKey(studentId)) {
            oldStudent = studentsMap.put(student.getId(), student);
        }
        return oldStudent != null;
    }

    @Override
    public boolean deleteStudent(long studentId) {
        Student removedStudent = studentsMap.remove(studentId);
        return removedStudent != null;
    }
}
