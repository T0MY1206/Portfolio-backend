package com.TOMY.portfolio.portfolio_backend.repository;

import com.TOMY.portfolio.portfolio_backend.model.Technology;
import com.TOMY.portfolio.portfolio_backend.model.TechnologyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findByName(String name);
    List<Technology> findByType(TechnologyType type);
    boolean existsByName(String name);
}
