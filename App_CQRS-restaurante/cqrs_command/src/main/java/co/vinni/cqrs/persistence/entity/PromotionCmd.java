package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="promotions_command")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PromotionCmd {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Kitchen kitchen;

    @Enumerated(EnumType.STRING)
    private PromotionType type;

    private boolean active;
    private String targetProductCode; // para 2x1: producto al que aplica
}
