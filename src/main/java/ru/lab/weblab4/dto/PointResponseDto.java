package ru.lab.weblab4.dto;

import lombok.Data;

@Data
public class PointResponseDto {
    private Double x;
    private Double y;
    private Integer r;
    private Boolean hit;

    public PointResponseDto(Double x, Double y, Integer r, Boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }
}
