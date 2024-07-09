package ru.practicum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

import ru.practicum.dto.HitRequestDto;

import java.util.List;
import java.util.Map;

import static ru.practicum.Constants.HIT_API_PREFIX;
import static ru.practicum.Constants.STATS_API_PREFIX;

public class StatisticsClient extends BaseClient {

    public StatisticsClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> addHitRequestHit(HitRequestDto hitRequestDto) {
        return post(HIT_API_PREFIX, hitRequestDto);
    }

    public ResponseEntity<Object> getStatistics(String start, String end, List<String> uris, Boolean unique) {
        Map<String, Object> params = Map.of(
                "start", start,
                "end", end,
                "uris", uris,
                "unique", unique
        );
        String path = STATS_API_PREFIX + "?start={start}&end={end}&uris=uris&unique={unique}";
        return get(path, params);
    }

}
