package ru.lab.weblab4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab.weblab4.dto.PointRequestDto;
import ru.lab.weblab4.dto.PointResponseDto;
import ru.lab.weblab4.mbeans.EntryCounter;
import ru.lab.weblab4.mbeans.EntryCounterMBean;
import ru.lab.weblab4.model.Point;
import ru.lab.weblab4.service.HitChecker;
import ru.lab.weblab4.repository.PointRepository;
import ru.lab.weblab4.security.jwt.JwtTokenProvider;
import ru.lab.weblab4.service.PointService;
import ru.lab.weblab4.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    private final UserService userService;

    private final PointRepository pointRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final HitChecker hitChecker;

    private final EntryCounter entryCounter;

    @Autowired
    public PointServiceImpl(UserService userService,
                            PointRepository pointRepository,
                            JwtTokenProvider jwtTokenProvider,
                            HitChecker hitChecker,
                            EntryCounter entryCounter) {
        this.userService = userService;
        this.pointRepository = pointRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.hitChecker = hitChecker;
        this.entryCounter = entryCounter;
    }

    @Override
    public PointResponseDto checkAndBuildPoint(PointRequestDto pointRequestDto, String bearerToken) {
        String token = jwtTokenProvider.getOnlyToken(bearerToken);
        Point point = new Point();
        point.setX(pointRequestDto.getX());
        point.setY(pointRequestDto.getY());
        point.setR(pointRequestDto.getR());
        point.setHit(hitChecker.isHit(pointRequestDto.getX(), pointRequestDto.getY(), pointRequestDto.getR()));
        point.setUser(userService.findByUsername(jwtTokenProvider.getUsername(token)));
        entryCounter.apply(point.getHit());
        pointRepository.save(point);
        return new PointResponseDto(point.getX(), point.getY(), point.getR(), point.getHit());
    }

    @Override
    public void removePointsByToken(String bearerToken) {
        String token = jwtTokenProvider.getOnlyToken(bearerToken);
        Long userId = userService.findByUsername(jwtTokenProvider.getUsername(token)).getId();
        List<Point> points = pointRepository.findAll();
        for (Point point : points) {
            if (point.getUser().getId().equals(userId)) {
                pointRepository.delete(point);
            }
        }
    }

    @Override
    public List<PointResponseDto> getPointsByToken(String bearerToken) {
        String token = jwtTokenProvider.getOnlyToken(bearerToken);
        Long userId = userService.findByUsername(jwtTokenProvider.getUsername(token)).getId();
        List<Point> points = pointRepository.findAll();
        List<PointResponseDto> responseDtoList = new ArrayList<>();
        for (Point point : points) {
            if (point.getUser().getId().equals(userId)) {
                responseDtoList.add(new PointResponseDto(point.getX(), point.getY(), point.getR(), point.getHit()));
            }
        }
        return responseDtoList;
    }
}
