package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.dto.StatsViewDto;
import ru.practicum.mapper.HitRequestMapper;
import ru.practicum.repository.StatsServerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServerService {
    private final StatsServerRepository statsServerRepository;
    private final HitRequestMapper hitRequestMapper;

    public void saveHitRequestData(HitRequestDto hitRequestDto) {
        statsServerRepository.save(hitRequestMapper.mapDtoToRequest(hitRequestDto));
    }

    public List<StatsViewDto> getViewStatistics(List<String> uris,
                                                LocalDateTime start,
                                                LocalDateTime end,
                                                Boolean unique) {
        return unique ? statsServerRepository.findByUriIsUnique(uris, start, end) :
                statsServerRepository.findByUri(uris, start, end);
    }
}
