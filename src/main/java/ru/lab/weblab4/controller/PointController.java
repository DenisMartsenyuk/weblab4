package ru.lab.weblab4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.lab.weblab4.dto.PointRequestDto;
import ru.lab.weblab4.dto.PointResponseDto;
import ru.lab.weblab4.service.PointService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/points")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping("/new")
    public ResponseEntity<PointResponseDto> newPoint(@RequestBody PointRequestDto pointRequestDto, @RequestHeader("Authorization") String bearerToken) {
        return new ResponseEntity<>(pointService.checkAndBuildPoint(pointRequestDto, bearerToken), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePoints(@RequestHeader("Authorization") String bearerToken) {
        pointService.removePointsByToken(bearerToken);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PointResponseDto>> getAllPoints(@RequestHeader("Authorization") String bearerToken) {
        List<PointResponseDto> points = pointService.getPointsByToken(bearerToken);
        return new ResponseEntity<>(points, HttpStatus.OK);
    }
}
