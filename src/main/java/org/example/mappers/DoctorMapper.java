package org.example.mappers;

import org.example.dto.DoctorDto;
import org.example.dto.ScheduleDto;
import org.example.models.Doctor;
import org.example.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDto toDoctorDto(Doctor doctor);

    Doctor toDoctorModel(DoctorDto doctorDto);






}
