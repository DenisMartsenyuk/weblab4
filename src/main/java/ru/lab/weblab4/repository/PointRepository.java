package ru.lab.weblab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.weblab4.model.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
}
