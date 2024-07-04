package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.dto.StatsViewDto;
import ru.practicum.service.StatsServerService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class StatsServerController {
    private final StatsServerService statsServerService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveHitRequestData(@RequestBody HitRequestDto hitRequestDto) {
        statsServerService.saveHitRequestData(hitRequestDto);
    }

    @GetMapping("/stats")
    public List<StatsViewDto> getViewStatistics(@RequestParam List<String> uris,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam LocalDateTime start,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam LocalDateTime end,
                                                @RequestParam(defaultValue = "false") Boolean unique) {
        return statsServerService.getViewStatistics(uris, start, end, unique);
    }
}
