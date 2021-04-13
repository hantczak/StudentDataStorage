package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@Primary
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
    public boolean updateStudentData(long oldStudentId, Student updatedStudent) {
        if (updatedStudent.getId() == oldStudentId) {
            if (database.existsById(oldStudentId)) {
                database.deleteById(oldStudentId);
                database.save(updatedStudent);
                return true;
            } else {
                Optional<Student> persistedStudent = database.findById(oldStudentId);
                if(persistedStudent.isPresent()){
                    persistedStudent.get().setAge(updatedStudent.getAge());
                    persistedStudent.get().setDateOfBirth(updatedStudent.getDateOfBirth());
                    persistedStudent.get().setEmail(updatedStudent.getEmail());
                    persistedStudent.get().setGender(updatedStudent.getGender());
                    persistedStudent.get().setName(updatedStudent.getName());
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteStudent(long studentId) {
        if (database.existsById(studentId)) {
            database.deleteById(studentId);
            return true;
        } else {
            return false;
        }
    }
}
