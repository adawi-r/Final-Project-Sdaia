package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.UriInfo;
import org.example.dao.ScheduleDao;
import org.example.dto.ScheduleDto;
import org.example.dto.ScheduleFilterDto;
import org.example.mappers.ScheduleMapper;
import org.example.models.Schedule;

import org.example.exceptions.DataNotFoundException;

import java.net.URI;
import java.sql.*;
import java.util.ArrayList;

@Path("/SCHEDULES")
public class ScheduleController {

    ScheduleDao scheduleDao = new ScheduleDao();
    ScheduleDto scheduleDto = new ScheduleDto();

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    public ScheduleController()  {
    }


    //GET ALL Schedule

//    @GET
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response selectAllSchedules() throws SQLException, ClassNotFoundException {
//        try {
//            GenericEntity<ArrayList<Schedule>> patient = new GenericEntity<ArrayList<Schedule>>(scheduleDao.selectAllSchedules(scheduleDto)) {
//            };
//            return Response.ok(patient, MediaType.APPLICATION_JSON).build();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // GET Schedule BY ID
    @GET
    @Path("/{schedule_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response selectSchedulesById(@PathParam("schedule_id") int schedule_id) throws SQLException, ClassNotFoundException {
        try {
            Schedule schedule = scheduleDao.selectSchedulesById(schedule_id);
            if (schedule == null) {
                throw new DataNotFoundException("Schedule with ID " + schedule_id + " not found");
            }

            ScheduleDto scheduleDto = ScheduleMapper.INSTANCE.toScheduleDto(schedule);

            return Response.ok(scheduleDto).build();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    // select many things

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,"text/csv"})
    public Response getAllSchedule(
            @BeanParam ScheduleFilterDto filter) throws SQLException, ClassNotFoundException {

        try {
            GenericEntity<ArrayList<Schedule>> schedules = new GenericEntity<ArrayList<Schedule>>(scheduleDao.selectAllSchedules(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(schedules)
                        .type(MediaType.APPLICATION_XML)
                        .build();

            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(schedules)
                        .type("text/csv")
                        .build();
            }

            return Response
                    .ok(schedules, MediaType.APPLICATION_JSON)
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    //Insert Schedule
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response InsertSchedule(ScheduleDto scheduleDto) throws SQLException, ClassNotFoundException {
        try {
            Schedule schedule = ScheduleMapper.INSTANCE.toScheduleModel(scheduleDto);

            scheduleDao.InsertSchedule(schedule);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(schedule.getSchedule_id())).build();
            return Response.created(uri).build();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE schedule
    @PUT
    @Path("{schedule_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public void updateSchedule(@PathParam("schedule_id") int schedule_id, Schedule schedule) throws SQLException, ClassNotFoundException {

        try {
            schedule.setSchedule_id(schedule_id);
            scheduleDao.updateSchedule(schedule);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


//    select by doctor id
//@GET
//@Path("/{doctor_id}")
//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//public Response selectSchedulesByDoctorId(@PathParam("doctor_id") int doctor_id) throws SQLException, ClassNotFoundException {
//    try {
//        Schedule schedule = scheduleDao.selectSchedulesByDoctor(doctor_id);
//        if (schedule == null) {
//            throw new DataNotFoundException("Schedule with ID " + doctor_id + " not found");
//        }
//
//        ScheduleDto scheduleDto = ScheduleMapper.INSTANCE.toScheduleDto(schedule);
//
//        return Response.ok(scheduleDto).build();
//    } catch (SQLException | ClassNotFoundException e) {
//        throw new RuntimeException(e);
//    }
//}


}
