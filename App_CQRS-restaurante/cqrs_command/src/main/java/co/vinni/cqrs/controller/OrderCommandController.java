package co.vinni.cqrs.controller;

import co.vinni.cqrs.dto.CreateOrderRequest;
import co.vinni.cqrs.persistence.entity.OrderCmd;
import co.vinni.cqrs.service.OrderCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin(origins="*")
public class OrderCommandController {
    private final OrderCommandService service;

    @PostMapping
    public OrderCmd create(@RequestBody CreateOrderRequest r){
        return service.create(r);
    }

    @PutMapping("/{id}/accept") public OrderCmd accept(@PathVariable Long id){ return service.accept(id); }
    @PutMapping("/{id}/reject") public OrderCmd reject(@PathVariable Long id){ return service.reject(id); }
}
