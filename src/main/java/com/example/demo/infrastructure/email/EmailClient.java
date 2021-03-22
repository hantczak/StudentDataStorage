package com.example.demo.infrastructure.email;

public interface EmailClient {

    void send(String message, String email);
}
