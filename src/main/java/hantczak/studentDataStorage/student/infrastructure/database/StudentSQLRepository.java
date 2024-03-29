package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.student.domain.InvalidStudentSortTypeException;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;
import hantczak.studentDataStorage.student.domain.StudentSortType;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@PropertySource("application.properties")
@Primary
public class StudentSQLRepository implements StudentRepository {

    private final StudentRepositoryInterface databaseAccessor;

    public StudentSQLRepository(StudentRepositoryInterface databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    @Override
    public List<Student> getAllStudentsSortedWithPagination(StudentSortType studentSortTypes, long offset, long limit) {
        switch (studentSortTypes) {
            case AGE_ASC:
                return databaseAccessor.findByAgeAscendingWithPagination(offset, limit);
            case AGE_DSC:
                return databaseAccessor.findByAgeDescendingWithPagination(offset, limit);
            case NAME_ASC:
                return databaseAccessor.findByNameAscendingWithPagination(offset, limit);
            case NAME_DSC:
                return databaseAccessor.findByNameDescendingWithPagination(offset, limit);
            default:
                StringBuilder availableSortTypes = new StringBuilder();
                Arrays.stream(StudentSortType.values())
                        .forEach(type -> {
                            availableSortTypes.append(type);
                            availableSortTypes.append(", ");
                        });

                throw new InvalidStudentSortTypeException("Available values: " + availableSortTypes);
        }
    }

    @Override
    public Optional<Student> getStudent(long studentId) {
        return databaseAccessor.findById(studentId);
    }

    @Override
    public Student addStudent(Student student) {
        return databaseAccessor.save(student);
    }

    @Override
    public boolean updateStudentData(long oldStudentId, Student updatedStudent) {
        Optional<Student> persistedStudent = databaseAccessor.findById(oldStudentId);
        if (persistedStudent.isPresent()) {
            persistedStudent.get().setAge(updatedStudent.getAge());
            persistedStudent.get().setDateOfBirth(updatedStudent.getDateOfBirth());
            persistedStudent.get().setEmail(updatedStudent.getEmail());
            persistedStudent.get().setGender(updatedStudent.getGender());
            persistedStudent.get().setName(updatedStudent.getName());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStudent(long studentId) {
        if (databaseAccessor.existsById(studentId)) {
            databaseAccessor.deleteById(studentId);
            return true;
        } else {
            return false;
        }
    }
}
