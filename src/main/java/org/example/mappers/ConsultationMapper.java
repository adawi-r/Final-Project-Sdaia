package org.example.mappers;

import org.example.dto.ConsultationDto;
import org.example.models.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConsultationMapper {

    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);


    ConsultationDto toConsultationDto(Consultation consultation);

    Consultation toConsultationModel(ConsultationDto consultationDto);


    List<ConsultationDto> toConsultationDto(List<Consultation> consultations);

    List<Consultation> toConsultationModel(List<ConsultationDto> consultationsDtos);

}
