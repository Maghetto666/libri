package com.example.libri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "libri")
public class Libri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String autore;
    private int anno;
    private int numPagine;
    private String genere;
    private String lingua;

}
