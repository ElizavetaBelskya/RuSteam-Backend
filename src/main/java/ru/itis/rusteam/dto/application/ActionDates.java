package ru.itis.rusteam.dto.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionDates {
    @Column(nullable = false)
    private LocalDateTime publishDate;
    @Column(nullable = false)
    private LocalDateTime modificationDate;
}
