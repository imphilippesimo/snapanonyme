package com.zerofiltre.snapanonyme.application.mapper;

import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SnapDTOMapper {

    public static SnapDTOMapper INSTANCE = Mappers.getMapper(SnapDTOMapper.class);

    public abstract SnapDTO snapToSnapDTO(Snap snap);

    public abstract Snap snapDTOToSnap(SnapDTO snapDTO);

    public abstract List<Snap> snapDTOsToSnaps(List<SnapDTO> snaps);

    public abstract List<SnapDTO> snapsToSnapDTOs(List<Snap> snap);


}
