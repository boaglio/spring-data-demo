package com.boaglio.apivmvp.exception;

public class FilmeNotFoundException extends RuntimeException {

    private Long id;
    private String titulo;

    public FilmeNotFoundException(Long id) {
        super("ID "+id+" not found!");
        this.id = id;
    }

    public FilmeNotFoundException(String titulo) {
        super("Titulo "+titulo+" not found!");
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }
    public String getTitulo() { return titulo; }
}
