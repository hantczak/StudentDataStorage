package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryInterface extends JpaRepository<Student,Long> {

    @Query(value="SELECT * FROM student ORDER BY name ASC limit ?2 offset ?1", nativeQuery = true)
    List<Student> findByNameAscendingWithPagination(long offset, long limit);

    @Query(value="SELECT * FROM student ORDER BY name DESC limit ?2 offset ?1", nativeQuery = true)
    List<Student> findByNameDescendingWithPagination(long offset, long limit);

    @Query(value="SELECT * FROM student ORDER BY age ASC limit ?2 offset ?1", nativeQuery = true)
    List<Student> findByAgeAscendingWithPagination(long offset, long limit);

    @Query(value="SELECT * FROM student ORDER BY age DESC limit ?2 offset ?1", nativeQuery = true)
    List<Student> findByAgeDescendingWithPagination(long offset, long limit);
}