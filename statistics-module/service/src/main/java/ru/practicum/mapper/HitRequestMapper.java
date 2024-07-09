package ru.practicum.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.entity.HitRequest;


@Component
@Slf4j
public class HitRequestMapper {
    public HitRequest mapDtoToRequest(HitRequestDto hitRequestDto) {
        return new HitRequest(hitRequestDto);
    }
}
