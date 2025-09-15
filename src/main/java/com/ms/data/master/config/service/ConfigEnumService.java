package com.ms.data.master.config.service;

import com.ms.data.master.config.model.ConfigEnum;
import com.ms.data.master.config.model.dto.config.ConfigEnumDTO;
import com.ms.data.master.config.model.dto.response.PageResponse;
import com.ms.data.master.config.model.mapper.ConfigEnumMapper;
import com.ms.data.master.config.respository.ConfigEnumRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ConfigEnumService {
    private final ConfigEnumRepository configEnumRepository;

    public PageResponse<ConfigEnumDTO> getAllService(Integer pageableSize, Integer pageablePage, Sort sorting, ConfigEnumDTO configEnumDTO)
    {
        return
                new PageResponse<>(
                        getAllFromRepository(pageableSize, pageablePage, sorting, configEnumDTO)
                                .getContent()
                                .stream()
                                .map(ConfigEnumMapper.INSTANCE::toDTO)
                                .collect(Collectors.toList()),
                        getAllFromRepository(pageableSize, pageablePage, sorting, configEnumDTO).getTotalElements(),
                        getAllFromRepository(pageableSize, pageablePage, sorting, configEnumDTO).getSize(),
                        getAllFromRepository(pageableSize, pageablePage, sorting, configEnumDTO).getNumber() + 1

                );
    }

    public ConfigEnumDTO getIdService(UUID id) {
        return ConfigEnumMapper.INSTANCE.toDTO(getIdFromRepository(id));
    }

    public ConfigEnumDTO getTypeService(String type) {
        return ConfigEnumMapper.INSTANCE.toDTO(getTypeFromRepository(type));
    }

    @Transactional
    public ConfigEnumDTO createService(ConfigEnumDTO ConfigEnumDTO) {
        return ConfigEnumMapper.INSTANCE.toDTO(
                configEnumRepository.save(
                        ConfigEnumMapper.INSTANCE.toEntity(ConfigEnumDTO)
                )
        );
    }

    @Transactional
    public ConfigEnumDTO updateService(UUID id, ConfigEnumDTO configEnumDTO) {
        return ConfigEnumMapper.INSTANCE.toDTO(
                configEnumRepository.save(updateEntityFromDTO(getIdFromRepository(id), configEnumDTO))
        );
    }

    public void deleteService(UUID id) {
        deleteFromRepository(id);
    }

    private Page<ConfigEnum> getAllFromRepository(Integer pageableSize, Integer pageablePage, Sort sorting, ConfigEnumDTO configEnumDTO) {
        return configEnumRepository.findAll(buildSpecification(configEnumDTO), PageRequest.of(pageablePage, pageableSize, sorting));
    }

    private ConfigEnum getIdFromRepository(UUID id) {
        return configEnumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order Request not found for id: " + id));
    }

    private ConfigEnum getTypeFromRepository(String type) {
        return configEnumRepository.findByType(type).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Enum not found for type: " + type));
    }

    private void deleteFromRepository(UUID id) {
        configEnumRepository.deleteById(id);
    }

    private ConfigEnum updateEntityFromDTO(ConfigEnum existing, ConfigEnumDTO dto) {
        ConfigEnumMapper.INSTANCE.updateFromDTOToEntity(dto, existing);
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setUpdatedBy(dto.getUpdatedBy());
        return existing;
    }

    private Specification<ConfigEnum> buildSpecification(ConfigEnumDTO configEnumDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (configEnumDTO.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), configEnumDTO.getId()));
            }

            if (configEnumDTO.getCreatedBy() != null && !configEnumDTO.getCreatedBy().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("createdBy")),
                        configEnumDTO.getCreatedBy().toLowerCase()
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
