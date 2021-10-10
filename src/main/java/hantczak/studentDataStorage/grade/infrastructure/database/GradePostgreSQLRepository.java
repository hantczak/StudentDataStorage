package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeRepository;
import hantczak.studentDataStorage.grade.domain.GradeSortType;
import hantczak.studentDataStorage.grade.domain.InvalidGradeSortTypeException;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@PropertySource("application.properties")
@Primary
public class GradePostgreSQLRepository implements GradeRepository {
    GradePostgreSQLRepositoryInterface databaseAccessor;

    @PersistenceContext
    EntityManager entityManager;

    public GradePostgreSQLRepository(GradePostgreSQLRepositoryInterface databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    @Override
    public List<Grade> getAllGrades() {
        return databaseAccessor.findAll();
    }

    @Override
    public List<Grade> getStudentGrades(long studentId) {
        return databaseAccessor.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getAllGradesSorted(GradeSortType gradeSortType, int offset, int limit) {

        TypedQuery<Grade> limitedCriteriaQuery = entityManager.createQuery(getCriteriaQuery(gradeSortType))
                .setMaxResults(limit).setFirstResult(offset);
        return limitedCriteriaQuery.getResultList();
    }

    @Override
    public List<Grade> getAllStudentGradesSorted(long studentId, GradeSortType gradeSortType, int offset, int limit) {
        switch (gradeSortType) {
            case VALUE_ASC:
                return databaseAccessor.findForStudentOrderedByValueAscendingWithPagination(studentId, offset, limit);
            case VALUE_DSC:
                return databaseAccessor.findForStudentOrderedByValueDescendingWithPagination(studentId, offset, limit);
            case INSERTION_DATE_ASC:
                return databaseAccessor.findForStudentOrderedByInsertionDateAscendingWithPagination(studentId, offset, limit);
            case INSERTION_DATE_DSC:
                return databaseAccessor.findForStudentOrderedByInsertionDateDescendingWithPagination(studentId, offset, limit);
            default:
                StringBuilder sortTypes = new StringBuilder();
                Arrays.stream(GradeSortType.values())
                        .forEach(value -> {
                            sortTypes.append(value);
                            sortTypes.append(", ");
                        });
                throw new InvalidGradeSortTypeException(",available sort types: " + sortTypes);
        }
    }

    @Override
    public void addGrade(Grade grade) {
        databaseAccessor.save(grade);
    }

    @Override
    public boolean updateGrade(Grade updatedGrade, long oldGradeId) {
        if (updatedGrade.getGradeId() != oldGradeId) {
            if (databaseAccessor.existsById(oldGradeId)) {
                databaseAccessor.deleteById(oldGradeId);
                databaseAccessor.save(updatedGrade);
                return true;
            }
        } else {
            Optional<Grade> persistedGrade = databaseAccessor.findById(oldGradeId);
            if (persistedGrade.isPresent()) {
                persistedGrade.get().setGradeId(updatedGrade.getGradeId());
                persistedGrade.get().setGradeScale(updatedGrade.getGradeScale());
                persistedGrade.get().setGradeWeight(updatedGrade.getGradeWeight());
                persistedGrade.get().setInsertionDate(updatedGrade.getInsertionDate());
                persistedGrade.get().setStudentId(updatedGrade.getStudentId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteGrade(long gradeToBeDeletedId) {
        if (databaseAccessor.existsById(gradeToBeDeletedId)) {
            databaseAccessor.deleteById(gradeToBeDeletedId);
            return true;
        }
        return false;
    }

    @Override
    public void deleteStudentGrades(long studentId) {
        databaseAccessor.deleteByStudentId(studentId);
    }

    private CriteriaQuery<Grade> getCriteriaQuery(GradeSortType gradeSortType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Grade> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Grade.class);
        Root<Grade> student = criteriaQuery.from(Grade.class);
        criteriaQuery.select(student);

        switch (gradeSortType) {
            case VALUE_ASC:
                return criteriaQuery.orderBy(cb.asc(student.get("gradeScale")));
            case VALUE_DSC:
                return criteriaQuery.orderBy(cb.desc(student.get("gradeScale")));
            case INSERTION_DATE_ASC:
                return criteriaQuery.orderBy(cb.asc(student.get("insertionDate")));
            case INSERTION_DATE_DSC:
                return criteriaQuery.orderBy(cb.desc(student.get("insertionDate")));
        }
        return criteriaQuery;
    }
}
