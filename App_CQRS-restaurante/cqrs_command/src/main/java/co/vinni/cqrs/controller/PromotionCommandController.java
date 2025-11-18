package co.vinni.cqrs.controller;

import co.vinni.cqrs.dto.PromotionRequest;
import co.vinni.cqrs.persistence.entity.PromotionCmd;
import co.vinni.cqrs.service.PromotionCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
@AllArgsConstructor
@CrossOrigin(origins="*")
public class PromotionCommandController {
    private final PromotionCommandService service;
    @PutMapping("/") public PromotionCmd set(@RequestBody PromotionRequest r){ return service.set(r); }
}
