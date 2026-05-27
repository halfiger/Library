package com.tommy.library;

public class BookInfoDTO {
    private String name;
    private String autor;

    public BookInfoDTO() {}

    public BookInfoDTO(String name, String autor) {
        this.name = name;
        this.autor = autor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "BookInfoDTO{" +
                "name='" + name + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
