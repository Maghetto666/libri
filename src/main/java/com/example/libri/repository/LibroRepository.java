package com.example.libri.repository;

import com.example.libri.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, String> {
}
