package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.dto.StatsViewDto;
import ru.practicum.entity.HitRequest;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsServerRepository extends JpaRepository<HitRequest, Long> {

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime BETWEEN ?2 AND ?3 " +
            "AND hr.uri IN (?1) " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC ")
    List<StatsViewDto> findByUriIsUnique(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(DISTINCT hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime BETWEEN ?2 AND ?3 " +
            "AND hr.uri IN (?1) " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC")
    List<StatsViewDto> findByUriNotUniqueIp(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime BETWEEN ?1 AND ?2 " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC ")
    List<StatsViewDto> findUniqueIp(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(DISTINCT hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime BETWEEN ?1 AND ?2 " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC ")
    List<StatsViewDto> findNotUniqueIp(LocalDateTime start, LocalDateTime end);
}
