package com.example.Pagos.Repository;

import com.example.Pagos.Model.PagosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepository extends JpaRepository<PagosModel, Long> {
}