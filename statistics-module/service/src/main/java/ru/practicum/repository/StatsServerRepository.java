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

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(DISTINCT hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime >= ?2 AND hr.dateTime <= ?3 " +
            "AND hr.uri IN (?1) " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC ")
    List<StatsViewDto> uniqueIpUri(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime >= ?2 AND hr.dateTime <= ?3 " +
            "AND hr.uri IN (?1) " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC")
    List<StatsViewDto> noUniqueIpUri(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(DISTINCT hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime >= ?1 AND hr.dateTime <= ?2 " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC ")
    List<StatsViewDto> uniqueIrBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsViewDto(hr.app, hr.uri, COUNT(hr.ip)) " +
            "FROM HitRequest hr " +
            "WHERE hr.dateTime >= ?1 AND hr.dateTime <= ?2 " +
            "GROUP BY hr.app, hr.uri " +
            "ORDER BY COUNT(hr.ip) DESC ")
    List<StatsViewDto> notUniqueIpBetween(LocalDateTime start, LocalDateTime end);

}
