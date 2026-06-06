package com.example.Tracking.Repository;

import com.example.Tracking.Model.TrackingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingModel, Long> {
}