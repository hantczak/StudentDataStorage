package com.example.demo.gradenotifier;

import com.example.demo.grade.domain.Grade;
import com.example.demo.grade.domain.GradeAddedListener;
import com.example.demo.infrastructure.email.EmailClient;

public class GradeNotifier implements GradeAddedListener {

    private final EmailClient emailClient;

    public GradeNotifier(EmailClient emailClient) {
        this.emailClient = emailClient;
    }


    @Override
    public void onAdd(Grade grade) {
        emailClient.send("Twoj synek dostał ocene - laczka" , "exmaple@gmail.com");
    }
}

