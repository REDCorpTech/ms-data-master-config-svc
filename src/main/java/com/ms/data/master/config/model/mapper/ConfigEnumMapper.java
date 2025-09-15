package com.ms.data.master.config.model.mapper;

import com.ms.data.master.config.model.ConfigEnum;
import com.ms.data.master.config.model.dto.config.ConfigEnumDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConfigEnumMapper {
    ConfigEnumMapper INSTANCE = Mappers.getMapper(ConfigEnumMapper.class);

    ConfigEnum toEntity(ConfigEnumDTO configEnumDTO);

    ConfigEnumDTO toDTO(ConfigEnum configEnum);

    void updateFromDTOToEntity(ConfigEnumDTO configEnumDTO, @MappingTarget ConfigEnum configEnum);
}
