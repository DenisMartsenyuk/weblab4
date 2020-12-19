package ru.lab.weblab4.service.impl;

import org.springframework.stereotype.Service;
import ru.lab.weblab4.service.HitChecker;

@Service
public class HitCheckerImpl implements HitChecker {
    @Override
    public boolean isHit(Double x, Double y, Integer r) {

        if (r >= 0) {
            if (x <= 0 && y <= 0 && (4 * x * x <= r * r) && y * y <= r * r) {
                return true;
            }

            if (x <= 0 && y >= 0 &&  y <= r + 2 * x) {
                return true;
            }

            if(x >= 0 && y <= 0 && (4 * x * x + 4 * y * y <= r * r)) {
                return true;
            }
        } else {
            if (x >= 0 && y >= 0 && (4 * x * x <= r * r) && y * y <= r * r) {
                return true;
            }

            if (x >= 0 && y <= 0 && -y <= -r - 2 * x) {
                return true;
            }

            if (x <= 0 && y >= 0 && (4 * x * x + 4 * y * y <= r * r)) {
                return true;
            }
        }

        return false;
    }
}
