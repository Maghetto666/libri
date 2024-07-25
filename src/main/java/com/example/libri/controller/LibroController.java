package com.example.libri.controller;


import com.example.libri.exception.LibroNotFoundException;
import com.example.libri.model.Libro;
import com.example.libri.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/libri")
@Tag(name = "Gestione libri", description = "Api di gestione libri")
public class LibroController {

    @Autowired
    LibroService libroService;

    private static final Logger logger = LoggerFactory.getLogger(LibroController.class);

    @GetMapping
    @Operation(summary = "Recupera libri", description = "Recupera tutti i libri sul database")
    @ApiResponse(responseCode = "200", description = "Libri recuperati con successo")
    public List<Libro> getAllLibri() {
        return libroService.getAllLibri();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera libro per id", description = "Torna il libro con l'id cercato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro trovato"),
            @ApiResponse(responseCode = "404", description = "Libro non trovato con quell'id")
    })
    public Libro getLibroById(@PathVariable Long id) {
        Libro libro = libroService.getLibroById(id);
        if (libro == null) {
            logger.error("Libro non trovato con quell'id");
            throw new LibroNotFoundException("Libro not found with id: " + id);
        }
        return libro;
    }

    @PostMapping
    @Operation(summary = "Aggiunge un libro", description = "Aggiunge un nuovo libro sul database")
    @ApiResponse(responseCode = "200", description = "Libro aggiunto con successo")
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
    @Operation(summary = "modifica dettagli libro", description = "modifica i dettagli di un libro trovato tramite id")
    @ApiResponse(responseCode = "200", description = "Libro modificato con successo")
    public Libro updateLibro(@PathVariable Long id,
                             @RequestParam String titolo,
                             @RequestParam String autore,
                             @RequestParam int anno,
                             @RequestParam int numPagine,
                             @RequestParam String genere,
                             @RequestParam String lingua
    ) {
        Libro updatedLibro = libroService.updateUser(id, titolo, autore, anno, numPagine, genere, lingua);
        if (updatedLibro == null) {
            logger.error("Libro non trovato con quell'id");
            throw new LibroNotFoundException("Libro not found with id: " + id);
        } else {
            return updatedLibro;
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancella libro", description = "Cancella libro dal database")
    @ApiResponse(responseCode = "200", description = "Libro cancellato dal database")
    public void deleteLibro(@PathVariable Long id) {
        boolean deleted = libroService.deleteLibro(id);
        if (!deleted) {
            logger.error("Libro non trovato con quell'id");
            throw new LibroNotFoundException("Libro not found with id: " + id);
        }
    }

}
