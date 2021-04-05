package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.grade.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface GradePostgreSQLRepositoryInterface extends JpaRepository<Grade,Long> {
    @Transactional
    List<Grade> findByStudentId(Long studentId);
    @Transactional
    void deleteByStudentId(Long studentId);
}
