package com.somativa.cafearomaesabor.repository;

import com.somativa.cafearomaesabor.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNomeUsuario(String nomeUsuario);
    Usuario findByEmail(String email);
}

