package com.example.individual_spring.stores;

import jakarta.validation.constraints.Digits;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("MASTER")
@AllArgsConstructor
@NoArgsConstructor()
public class Master {
    @Id
    private Long masterId;

    @Column("FULL_NAME")
    @NotNull
    @Size(min = 5, message = "ФИО должно содержать не менее 5 символов")
    private String fullName;

    @NotNull
    @NotBlank(message = "Укажите Должность")
    private String post;

    @NotNull
    @Digits(integer = 11, fraction = 0, message = "Некорректный номер телефона")
    @Size(min = 11, max = 11, message = "Номер телефона состоит из 11 цифр")
    private String phone;

    @MappedCollection(keyColumn = "MASTER_ID", idColumn = "MASTER_ID")
    private List<Service> services = new ArrayList<>();
}
