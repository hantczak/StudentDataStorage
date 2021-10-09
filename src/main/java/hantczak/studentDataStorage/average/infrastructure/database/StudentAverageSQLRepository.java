package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.average.domain.InvalidStudentAverageSortTypeException;
import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;
import hantczak.studentDataStorage.average.domain.StudentAverageSortType;
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
public class StudentAverageSQLRepository implements StudentAverageRepository {
    private final StudentAverageSQLRepositoryInterface database;

    public StudentAverageSQLRepository(StudentAverageSQLRepositoryInterface database) {
        this.database = database;
    }

    @Override
    public List<StudentAverage> getAllAveragesSorted(StudentAverageSortType sortType, long offset, long limit) {
        switch (sortType) {
            case VALUE_ASC:
                return database.findAllAveragesSortByValueAscendingWithPagination(offset,limit);
            case VALUE_DSC:
                return database.findAllAveragesSortByValueDescendingWithPagination(offset,limit);
            case STUDENT_ID_ASC:
                return database.findAllAveragesSortByStudentIdAscendingWithPagination(offset,limit);
            case STUDENT_ID_DSC:
                return database.findAllAveragesSortByStudentIdDescendingWithPagination(offset,limit);
            default:
                StringBuilder sortTypes = new StringBuilder();
                Arrays.stream(StudentAverageSortType.values())
                        .forEach(value -> {
                            sortTypes.append(value);
                            sortTypes.append(",");
                        });
                throw new InvalidStudentAverageSortTypeException(",available sort types: " + sortTypes);
        }
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
            database.save(updatedStudentAverage);
            return true;
        }
    }

    @Override
    public boolean deleteAverage(long studentId) {
        database.deleteByStudentId(studentId);
        return true;
    }
}
