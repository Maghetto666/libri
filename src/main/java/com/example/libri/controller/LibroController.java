package com.example.libri.controller;


import com.example.libri.exception.LibroNotFoundException;
import com.example.libri.model.Libro;
import com.example.libri.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libri")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping
    public List<Libro> getAllLibri() {
        return libroService.getAllLibri();
    }

    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable String id) {
        Libro libro = libroService.getLibroById(id);
        if (libro == null) {
            throw new LibroNotFoundException("Libro not found with id: " + id);
        }
        return libro;
    }

    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestParam String titolo,
                                             @RequestParam String autore,
                                             @RequestParam int anno,
                                             @RequestParam int numPagine,
                                             @RequestParam String genere,
                                             @RequestParam String lingua) {
        Libro newLibro = libroService.createLibro(titolo, autore, anno, numPagine, genere, lingua);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLibro);
    }

    @PutMapping("{id}")
    public Libro updateLibro(@PathVariable String id,
                             @RequestParam String titolo,
                             @RequestParam String autore,
                             @RequestParam int anno,
                             @RequestParam int numPagine,
                             @RequestParam String genere,
                             @RequestParam String lingua
    ) {
        Libro updatedLibro = libroService.updateUser(id, titolo, autore, anno, numPagine, genere, lingua);
        if (updatedLibro == null) {
            throw new LibroNotFoundException("Libro not found with id: " + id);
        } else {
            return updatedLibro;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable String id) {
        boolean deleted = libroService.deleteLibro(id);
        if (!deleted) {
            throw new LibroNotFoundException("Libro not found with id: " + id);
        }
    }

}
