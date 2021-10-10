package hantczak.studentDataStorage.average.infrastructure.api;

import lombok.Getter;

import java.util.List;

@Getter
public class StudentAverageResponse {
    private List<StudentAverageDto> studentAverageDtoList;
    private int count;

    public StudentAverageResponse(List<StudentAverageDto> studentAverageDtoList) {
        this.studentAverageDtoList = studentAverageDtoList;
        this.count = studentAverageDtoList.size();
    }

    public StudentAverageResponse() {
    }
}
