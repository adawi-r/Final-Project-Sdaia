package org.example.mappers;

import org.example.dto.DoctorDto;
import org.example.dto.PatientDto;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDto toPatientDto(Patient patient);

    Patient toPatientModel(PatientDto patientDto);
}
