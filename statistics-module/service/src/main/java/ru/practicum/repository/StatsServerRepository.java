package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.dto.StatsViewDto;
import ru.practicum.entity.HitRequest;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsServerRepository extends JpaRepository<HitRequest, Long> {
    HitRequest save(HitRequest hitRequest);

    @Query(value = "select app, uri, count(ip) " +
            "from hit_requests " +
            "where date_time between :start and :end " +
            "and uri in (:uris) " +
            "group by app, uri " +
            "order by count(ip) desc ", nativeQuery = true)
    List<StatsViewDto> findByUriIsUnique(@Param("uris") List<String> uris,
                                         @Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end);

    @Query(value = "select app, uri, count(distinct ip) " +
            "from hit_requests " +
            "where date_time between :start and :end " +
            "and uri in (:uris) " +
            "group by app, uri " +
            "order by count(ip) desc ", nativeQuery = true)
    List<StatsViewDto> findByUri(List<String> uris, LocalDateTime start, LocalDateTime end);
}
