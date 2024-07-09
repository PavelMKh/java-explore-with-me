package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.dto.StatsViewDto;
import ru.practicum.service.StatsServerService;

import static ru.practicum.Constants.DATE_TIME_JSON_FORMAT;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class StatsServerController {
    private static final Logger log = LoggerFactory.getLogger(StatsServerController.class);
    private final StatsServerService statsServerService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveHitRequestData(@RequestBody HitRequestDto hitRequestDto) {
        log.info("Requested saving data: " + hitRequestDto.toString());
        statsServerService.saveHitRequestData(hitRequestDto);
    }

    @GetMapping("/stats")
    public List<StatsViewDto> getViewStatistics(@RequestParam(required = false) List<String> uris,
                                            @RequestParam @DateTimeFormat(pattern = DATE_TIME_JSON_FORMAT) LocalDateTime start,
                                            @RequestParam @DateTimeFormat(pattern = DATE_TIME_JSON_FORMAT) LocalDateTime end,
                                            @RequestParam(defaultValue = "false") Boolean unique) {
        return statsServerService.getViewStatistics(uris, start, end, unique);
    }
}
