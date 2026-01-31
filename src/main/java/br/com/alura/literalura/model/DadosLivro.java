package br.com.alura.literalura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivro {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DadosAutor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer downloads;

    public String getTitulo() {
        return titulo;
    }

    public List<DadosAutor> getAutores() {
        return autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    @Override
    public String toString() {
        return "\nTítulo: " + titulo +
                "\nAutores: " + autores +
                "\nIdiomas: " + idiomas +
                "\nDownloads: " + downloads;
    }
}
