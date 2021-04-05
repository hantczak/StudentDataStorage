package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean updateAverage(StudentAverage studentAverage) {
        database.deleteByStudentId(studentAverage.getStudentId());
        database.save(studentAverage);
        return true;
    }

    @Override
    public boolean deleteAverage(long studentId) {
        database.deleteByStudentId(studentId);
        return true;
    }
}
