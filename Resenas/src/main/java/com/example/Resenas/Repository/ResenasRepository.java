package com.example.Resenas.Repository;

import com.example.Resenas.Model.ResenasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenasRepository extends JpaRepository<ResenasModel, Long> {
}