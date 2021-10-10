package hantczak.studentDataStorage.student.domain;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor
public class StudentValidator {

    void validateStudent(Student student) {
        List<String> errors = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        if (!(student.getEmail().contains("@"))) {
            errors.add("Not appropriate e-mail address. It has to contain '@' sign.");
        }

        if (student.getEmail().contains(".")) {
            validateEmailDomain(student, errors);
        } else {
            errors.add("Not appropriate e-mail address. It has to contain a valid domain, preceded with '.' sign.");
        }

        if (calendar.get(Calendar.YEAR) - student.getDateOfBirth().getYear() > 18 || student.getAge() > 18) {
            errors.add("Students cannot be over 18 years old.");
        }

        if (calendar.get(Calendar.YEAR) - student.getDateOfBirth().getYear() != student.getAge()) {
            errors.add("Student age does not match his date of birth. Please input correct age value.");
        }

        if (student.getGender() == null) {
            errors.add("Please set the gender.");
        }

        if (!errors.isEmpty()) {
            throw new InvalidStudentException(errors);
        }
    }

    private void validateEmailDomain(Student student, List<String> errors) {
        List<String> supportedEmailDomainsList = List.of("com", "pl", "net", "org", "uk", "de", "ru");
        String studentDomain = extractDomainFromEmail(student);
        AtomicBoolean isDomainValid = new AtomicBoolean(false);

        supportedEmailDomainsList.stream()
                .forEach(supportedDomain -> {
                    if (supportedDomain.equals(studentDomain))
                        isDomainValid.set(true);
                });
        if (!isDomainValid.get()) {
            errors.add("Not appropriate e-mail address. It contains not supported domain - ." + studentDomain + ". Supported domains list: " + supportedEmailDomainsList);
        }
    }

    private String extractDomainFromEmail(Student student) {
        String[] parts = student.getEmail().split("\\.");
        return parts[1];
    }
}