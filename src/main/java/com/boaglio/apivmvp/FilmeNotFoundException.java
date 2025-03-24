package com.boaglio.apivmvp;

public class FilmeNotFoundException extends RuntimeException {
    private final Long id;
    public FilmeNotFoundException(Long id) {
        super("Movie "+id+" not found!");
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
