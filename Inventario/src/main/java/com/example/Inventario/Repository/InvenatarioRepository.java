package com.example.Inventario.Repository;

import com.example.Inventario.Model.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvenatarioRepository extends JpaRepository<InventarioModel, Long> {
}