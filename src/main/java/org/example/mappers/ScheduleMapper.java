package org.example.mappers;

import org.example.dto.ScheduleDto;
import org.example.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    ScheduleDto toScheduleDto(Schedule schedule);

    Schedule toScheduleModel(ScheduleDto scheduleDto);
}
