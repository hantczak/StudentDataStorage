package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@Primary
public class StudentAveragePostgreSQLRepository implements StudentAverageRepository {
    private final StudentAveragePostgreSQLRepositoryInterface database;

    public StudentAveragePostgreSQLRepository(StudentAveragePostgreSQLRepositoryInterface database) {
        this.database = database;
    }

    @Override
    public List<StudentAverage> getAllAverages() {
        return database.findAll();
    }

    @Override
    public Optional<StudentAverage> getStudentAverage(long studentId) {
        return database.findByStudentId(studentId);
    }

    @Transactional
    @Override
    public boolean updateAverage(StudentAverage updatedStudentAverage) {
        Optional<StudentAverage> studentAverageOptional = database.findByStudentId(updatedStudentAverage.getStudentId());
        if(studentAverageOptional.isPresent()) {
            studentAverageOptional.get().setAverage(updatedStudentAverage.getAverage());
            studentAverageOptional.get().setStudentId(updatedStudentAverage.getStudentId());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteAverage(long studentId) {
        database.deleteByStudentId(studentId);
        return true;
    }
}
