package com.example.Ordenes.Repository;

import com.example.Ordenes.Model.OrdenesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenesRepository extends JpaRepository<OrdenesModel, Long> {
}