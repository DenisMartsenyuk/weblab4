package ru.lab.weblab4.service;

import ru.lab.weblab4.dto.PointRequestDto;
import ru.lab.weblab4.dto.PointResponseDto;

import java.util.List;

public interface PointService {
    PointResponseDto checkAndBuildPoint(PointRequestDto pointRequestDto, String token);
    void removePointsByToken(String token);
    List<PointResponseDto> getPointsByToken(String token);
}
