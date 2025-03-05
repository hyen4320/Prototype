package org.example.be.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barcode;//바코드 숫자
    @ManyToOne
    private User user_id;
    private LocalDate expiryDate;//유통기한
    private LocalDateTime createdAt;//생성시간


}
