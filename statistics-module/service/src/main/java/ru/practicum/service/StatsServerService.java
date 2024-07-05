package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.dto.StatsViewDto;
import ru.practicum.entity.HitRequest;
import ru.practicum.mapper.HitRequestMapper;
import ru.practicum.repository.StatsServerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServerService {
    private final StatsServerRepository statsServerRepository;
    private final HitRequestMapper hitRequestMapper;

    public void saveHitRequestData(HitRequestDto hitRequestDto) {
        log.info("Received a request for hit creating: {}", hitRequestDto);
        statsServerRepository.save(hitRequestMapper.mapDtoToRequest(hitRequestDto));
    }

    public List<StatsViewDto> getViewStatistics(List<String> uris,
                                            LocalDateTime start,
                                            LocalDateTime end,
                                            Boolean unique) {
        if (uris != null) {
            if (unique) {
                log.info("Received a request with uri, unique IP, parameters: start date {}, end date {}", start, end);
                return statsServerRepository.uniqueIpUri(uris, start, end);
            } else {
                log.info("Received a request with uri, not unique IP, parameters: start date {}, end date {}", start, end);
                return statsServerRepository.noUniqueIpUri(uris, start, end);
            }
        } else {
            if (unique) {
                log.info("Received a request without uri, unique IP, parameters: start date {}, end date {}", start, end);
                return statsServerRepository.uniqueIrBetween(start, end);
            } else {
                log.info("Received a request without uri, not unique IP, parameters: start date {}, end date {}", start, end);
                return statsServerRepository.notUniqueIpBetween(start, end);
            }
        }
    }
}
