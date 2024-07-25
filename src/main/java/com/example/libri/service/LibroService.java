package com.example.libri.service;

import com.example.libri.model.Libro;
import com.example.libri.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> getAllLibri() {
        return libroRepository.findAll();
    }

    public Libro getLibroById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro createLibro(String titolo, String autore,
                             int anno, int numPagine,
                             String genere, String lingua) {
        Libro newLibro = new Libro(null,
                titolo, autore, anno, numPagine, genere, lingua);
        return libroRepository.save(newLibro);
    }

    public Libro updateUser(Long id, String titolo,
                            String autore, int anno,
                            int numPagine, String genere,
                            String lingua) {
        Libro libro = new Libro(id, titolo, autore, anno, numPagine, genere, lingua);
        return libroRepository.save(libro);
    }

    public boolean deleteLibro(Long id) {
        Libro user = libroRepository.findById(id).orElse(null);
        if (user != null) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

