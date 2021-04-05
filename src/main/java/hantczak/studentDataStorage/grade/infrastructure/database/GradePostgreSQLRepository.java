package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class GradePostgreSQLRepository implements GradeRepository {
    GradePostgreSQLRepositoryInterface database;

    public GradePostgreSQLRepository(GradePostgreSQLRepositoryInterface database) {
        this.database = database;
    }

    @Override
    public List<Grade> getAllGrades() {
        return database.findAll();
    }

    @Override
    public List<Grade> getStudentGrades(long studentId) {
        return database.findByStudentId(studentId);
    }

    @Override
    public void addGrade(Grade grade) {
        database.save(grade);
    }

    @Override
    public boolean updateGrade(Grade updatedGrade, long oldGradeId) {
        if (database.existsById(oldGradeId)) {
            database.deleteById(oldGradeId);
            database.save(updatedGrade);
        }
        return false;
    }

    @Override
    public boolean deleteGrade(long gradeToBeDeletedId) {
        if (database.existsById(gradeToBeDeletedId)) {
            database.deleteById(gradeToBeDeletedId);
            return true;
        }
        return false;
    }

    @Override
    public void deleteStudentGrades(long studentId) {
        database.deleteByStudentId(studentId);
    }
}
