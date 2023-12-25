package com.hw.mixvoice.domain.shorts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShortsRepository extends JpaRepository<Shorts, Long> {

    @Query(value = "SELECT * FROM Shorts s order by RAND() limit 1",nativeQuery = true)
    Optional<Shorts> findRandomShorts();
}
