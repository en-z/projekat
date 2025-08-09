package com.projekat.biblioteka_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZahtjevDTO {
    public Long knjigaId;
    public Long userId;
}
