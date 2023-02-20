package br.com.projectWeb.web.exception;

public class BadRequestExeption extends RuntimeException{

    public BadRequestExeption(String message) {
        super(message);
    }
}
