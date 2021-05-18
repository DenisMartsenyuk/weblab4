package ru.lab.weblab4.mbeans;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource
public class AreaCalculator implements AreaCalculatorMBean {


    @Override
    public double getArea(double r) {
        return (r * r / 4) + (r * r / 2) + (Math.PI * (r / 2) * (r / 2) / 4);
    }

}
