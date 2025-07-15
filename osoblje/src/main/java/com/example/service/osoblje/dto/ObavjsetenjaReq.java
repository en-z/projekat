package com.example.service.osoblje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObavjsetenjaReq {
    private String naslov;
    private String text;
    private Long fakultetID;
}
