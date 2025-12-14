package com.TOMY.portfolio.portfolio_backend.repository;

import com.TOMY.portfolio.portfolio_backend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // Solo habrá un perfil, pero usamos JPA estándar
}
