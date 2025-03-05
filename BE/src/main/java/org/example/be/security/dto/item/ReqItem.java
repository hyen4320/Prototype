package org.example.be.security.dto.item;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.be.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class ReqItem {
    private Long barcode;//바코드 숫자

    private Long user_id;
    private LocalDate expiryDate;//유통기한
}
