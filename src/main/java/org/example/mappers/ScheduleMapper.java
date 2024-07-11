package org.example.mappers;

import org.example.dto.ScheduleDtoAll;
import org.example.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    ScheduleDtoAll toScheduleDto(Schedule schedule);

    Schedule toScheduleModel(ScheduleDtoAll scheduleDtoAll);
}
