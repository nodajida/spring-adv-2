package com.sparta.adv2.domain.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoSaveRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String contents;
}
