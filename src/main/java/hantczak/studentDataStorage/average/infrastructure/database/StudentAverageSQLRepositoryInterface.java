package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StudentAverageSQLRepositoryInterface extends JpaRepository<StudentAverage, Long> {
    @Transactional
    Optional<StudentAverage> findByStudentId(Long studentId);

    @Transactional
    void deleteByStudentId(Long studentId);

    @Query(value="SELECT * FROM average ORDER BY average ASC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<StudentAverage> findAllAveragesSortByValueAscendingWithPagination(long offset, long limit);

    @Query(value="SELECT * FROM average ORDER BY average DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<StudentAverage> findAllAveragesSortByValueDescendingWithPagination(long offset, long limit);

    @Query(value="SELECT * FROM average ORDER BY student_id ASC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<StudentAverage> findAllAveragesSortByStudentIdAscendingWithPagination(long offset, long limit);

    @Query(value="SELECT * FROM average ORDER BY student_id DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<StudentAverage> findAllAveragesSortByStudentIdDescendingWithPagination(long offset, long limit);
}
