package com.example.demo.average.domain;

import java.util.List;

public interface AverageRepository {

    List<StudentAverage> getAllAverages();
    StudentAverage getStudentAverage(long studentId);
    boolean updateAverage(StudentAverage studentAverage);
    boolean deleteAverage(long studentId);
}



