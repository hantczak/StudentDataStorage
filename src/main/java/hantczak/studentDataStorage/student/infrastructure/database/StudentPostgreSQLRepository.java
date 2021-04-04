package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class StudentPostgreSQLRepository implements StudentRepository {

    @Autowired
    private final StudentPostgreSQLDBAccessInterface database;

    public StudentPostgreSQLRepository(StudentPostgreSQLDBAccessInterface database) {
        this.database = database;
    }


    @Override
    public List<Student> getAllStudents() {
        return database.findAll();
    }

    @Override
    public Optional<Student> getStudent(long studentId) {
        return database.findById(studentId);
    }

    @Override
    public void addStudent(Student student) {
        database.save(student);
    }

    @Override
    public boolean updateStudentData(long studentId, Student student) {
        if (database.existsById(studentId)) {
            database.deleteById(studentId);
            database.save(student);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStudent(long studentId) {
        if (database.existsById(studentId)){
            database.deleteById(studentId);
            return true;
        }else {
            return false;
        }
    }
}
