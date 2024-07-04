package ru.practicum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.dto.HitRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "hit_requests")
@AllArgsConstructor
@NoArgsConstructor
public class HitRequest {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String app;

    @Column(length = 100)
    private String uri;

    @Column(length = 20)
    private String ip;

    @Column
    private LocalDateTime dateTime;

    public HitRequest(HitRequestDto hitRequestDto) {
        this.app = hitRequestDto.getApp();
        this.ip = hitRequestDto.getIp();
        this.uri = hitRequestDto.getUri();
        this.dateTime = hitRequestDto.getTime();
    }

}
