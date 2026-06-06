package com.example.Envios.Repository;

import com.example.Envios.Model.Envios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnviosRepository extends JpaRepository<Envios, Long> {
}