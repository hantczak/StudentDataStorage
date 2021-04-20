package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.average.domain.StudentAverage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface StudentAveragePostgreSQLRepositoryInterface extends JpaRepository<StudentAverage, Long> {
    @Transactional
    Optional<StudentAverage> findByStudentId(Long studentId);

    @Transactional
    void deleteByStudentId(Long studentId);
}
