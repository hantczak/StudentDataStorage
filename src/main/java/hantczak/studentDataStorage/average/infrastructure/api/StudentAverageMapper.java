package hantczak.studentDataStorage.average.infrastructure.api;

import hantczak.studentDataStorage.average.domain.StudentAverage;

import java.util.List;
import java.util.stream.Collectors;

public class StudentAverageMapper {

    public static List<StudentAverageDto> StudentAverageListToStudentAverageDtoList(List<StudentAverage> studentAverages) {

        List<StudentAverageDto> studentAverageDtoList = studentAverages.stream()
                .map(StudentAverageMapper::toDto)
                .collect(Collectors.toList());
        return studentAverageDtoList;
    }

    public static StudentAverageDto toDto(StudentAverage studentAverage) {
        return new StudentAverageDto(studentAverage.getAverage(), studentAverage.getStudentId());
    }

    public static StudentAverage fromDto(StudentAverageDto studentAverageDto) {
        return new StudentAverage(studentAverageDto.getAverage(), studentAverageDto.getStudentId());
    }
}
