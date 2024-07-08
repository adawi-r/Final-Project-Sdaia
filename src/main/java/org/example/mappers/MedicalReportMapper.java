package org.example.mappers;

import org.example.dto.MedicalReportDto;
import org.example.models.MedicalReport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicalReportMapper {

    MedicalReportMapper INSTANCE = Mappers.getMapper(MedicalReportMapper.class);

    MedicalReportDto toMedicalReportDto(MedicalReport medicalReport);

    MedicalReport toMedicalReportModel(MedicalReportDto medicalReportDto);
}
