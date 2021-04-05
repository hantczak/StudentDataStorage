package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class StudentPostgreSQLRepository implements StudentRepository {

    private final StudentPostgreSQLRepositoryInterface database;

    public StudentPostgreSQLRepository(StudentPostgreSQLRepositoryInterface database) {
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
