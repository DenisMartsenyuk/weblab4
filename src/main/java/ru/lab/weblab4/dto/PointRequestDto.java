package ru.lab.weblab4.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class PointRequestDto {

    @NotNull
    @NotEmpty
    private Double x;

    @NotNull
    @NotEmpty
    private Double y;

    @NotNull
    @NotEmpty
    private Integer r;
}
