package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.grade.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface GradePostgreSQLRepositoryInterface extends JpaRepository<Grade, Long> {
    @Transactional
    List<Grade> findByStudentId(Long studentId);

    @Transactional
    void deleteByStudentId(Long studentId);

    @Query(value = "SELECT * FROM grade WHERE student_id= ?1 ORDER BY grade_scale ASC LIMIT ?3  OFFSET ?2", nativeQuery = true)
    List<Grade> findForStudentOrderedByValueAscendingWithPagination(long StudentId, long offset, long limit);

    @Query(value = "SELECT * FROM grade WHERE student_id= ?1 ORDER BY grade_scale DESC LIMIT ?3 OFFSET ?2", nativeQuery = true)
    List<Grade> findForStudentOrderedByValueDescendingWithPagination(long StudentId, long offset, long limit);

    @Query(value = "SELECT * FROM grade WHERE student_id= ?1 ORDER BY insertion_date ASC LIMIT ?3  OFFSET ?2", nativeQuery = true)
    List<Grade> findForStudentOrderedByInsertionDateAscendingWithPagination(long StudentId, long offset, long limit);

    @Query(value = "SELECT * FROM grade WHERE student_id= ?1 ORDER BY insertion_date DESC LIMIT ?3  OFFSET ?2", nativeQuery = true)
    List<Grade> findForStudentOrderedByInsertionDateDescendingWithPagination(long StudentId, long offset, long limit);
}
