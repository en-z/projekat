package com.projekat.student_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlusanjaReqDto {
    private Long studentId;
    private List<Long> predmetIds;
}
