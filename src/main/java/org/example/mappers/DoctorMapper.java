package org.example.mappers;

import org.example.dto.DoctorDtoAll;
import org.example.models.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDtoAll toDoctorDto(Doctor doctor);

    Doctor toDoctorModel(DoctorDtoAll doctorDtoAll);






}
