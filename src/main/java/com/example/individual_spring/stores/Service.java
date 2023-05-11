package com.example.individual_spring.stores;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("SERVICE")
@AllArgsConstructor
@NoArgsConstructor()
public class Service {
    @Id
    private Long id;

    @NotNull
    @NotBlank(message = "Введите наименование услуги")
    private String title;

    @NotNull(message = "Введите стоимость услуги кратную 50р.")
    private Float price;

    @NotNull(message = "Введите продолжительность работы")
    private Float duration;
}
