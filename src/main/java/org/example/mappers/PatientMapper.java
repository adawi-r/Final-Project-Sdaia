package org.example.mappers;

import org.example.dto.PatientDto;
import org.example.dto.PatientDtoAll;
import org.example.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDtoAll toPatientDto(Patient patient);

    Patient toPatientModel(PatientDtoAll patientDtoAll);

    PatientDto toPatientDtop(Patient patient);

}
