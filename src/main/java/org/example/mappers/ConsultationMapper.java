package org.example.mappers;

import org.example.dto.ConsultationDtoAll;
import org.example.models.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConsultationMapper {

    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);


    ConsultationDtoAll toConsultationDto(Consultation consultation);

    Consultation toConsultationModel(ConsultationDtoAll consultationDtoAll);


    List<ConsultationDtoAll> toConsultationDto(List<Consultation> consultations);

    List<Consultation> toConsultationModel(List<ConsultationDtoAll> consultationsDtos);

}
