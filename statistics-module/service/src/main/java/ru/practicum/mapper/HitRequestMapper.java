package ru.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.entity.HitRequest;


@Component
public class HitRequestMapper {
    public HitRequest mapDtoToRequest(HitRequestDto hitRequestDto) {
        return new HitRequest(hitRequestDto);
    }
}
