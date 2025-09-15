package com.ms.data.master.config.controller;

import com.ms.data.master.config.exception.AccountExceptionHandler;
import com.ms.data.master.config.model.dto.config.ConfigEnumDTO;
import com.ms.data.master.config.model.dto.response.PageResponse;
import com.ms.data.master.config.service.ConfigEnumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("${endpoint.config-enum.base}")
@RequiredArgsConstructor
@Slf4j
public class ConfigEnumController {
    private final ConfigEnumService configEnumService;

    @Value("${common.pageable.size}")
    private Integer pageableSize;

    @Value("${common.pageable.page}")
    private Integer pageablePage;

    @Value("${common.sorting}")
    private String sortingPage;

    @GetMapping(value = "${endpoint.config-enum.get-all}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<ConfigEnumDTO>> getAllOrderRequests(
            @RequestParam(value = "pageableSize", required = false) Integer defaultPageableSize,
            @RequestParam(value = "pageablePage", required = false) Integer defaultPageablePage,
            @ModelAttribute ConfigEnumDTO configEnumDTO,
            @SortDefault(sort = "id", direction = Sort.Direction.ASC) Sort sorting) throws AccountExceptionHandler {

        return ResponseEntity.ok(configEnumService.getAllService(
                Optional.ofNullable(defaultPageableSize).filter(size -> size > 0).orElse(pageableSize),
                Optional.ofNullable(defaultPageablePage).filter(page -> page >= 0).orElse(pageablePage),
                Optional.ofNullable(sorting).orElse(Sort.by(Sort.Direction.fromString(sortingPage), "id")),
                configEnumDTO
        ));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEnumDTO> createOrderRequests(@RequestBody ConfigEnumDTO ConfigEnumDTO) {
        return ResponseEntity.ok(configEnumService.createService(ConfigEnumDTO));
    }

    @GetMapping(value = "${endpoint.config-enum.get-by-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEnumDTO> getOrderRequests(@PathVariable UUID id) {
        return ResponseEntity.ok(configEnumService.getIdService(id));
    }

    @GetMapping(value = "${endpoint.config-enum.get-by-type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEnumDTO> getOrderRequests(@PathVariable String type) {
        return ResponseEntity.ok(configEnumService.getTypeService(type));
    }

    @PutMapping( value = "${endpoint.config-enum.update}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEnumDTO> updateOrderRequests(@PathVariable UUID id, @RequestBody ConfigEnumDTO configEnumDTO) {
        return ResponseEntity.ok(configEnumService.updateService(id, configEnumDTO));
    }

    @DeleteMapping(value = "${endpoint.config-enum.delete}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOrderRequests(@PathVariable UUID id) {
            configEnumService.deleteService(id);
            return ResponseEntity.ok().build();
    }


}
