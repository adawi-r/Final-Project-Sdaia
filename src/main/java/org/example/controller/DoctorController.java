package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.ConsultationDao;
import org.example.dao.DoctorDao;
import org.example.dto.DoctorDto;
import org.example.dto.DoctorFilterDto;
import org.example.dto.RateDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.DoctorMapper;
import org.example.models.Doctor;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/DOCTORS")
public class DoctorController {

    DoctorDao doctorDao = new DoctorDao();
    DoctorDto doctorDto = new DoctorDto();
    ConsultationDao consultationDao = new ConsultationDao();

    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    public DoctorController() throws SQLException,ClassNotFoundException {
    }

    //REGISTER NEW DOCTOR
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response registerDoctor(DoctorDto doctorDto) throws SQLException,ClassNotFoundException {
        try {
            Doctor doctor = DoctorMapper.INSTANCE.toDoctorModel(doctorDto);
            doctorDao.registerDoctor(doctor);
            URI uri = uriInfo.getAbsolutePathBuilder().path(doctor.getDoctor_id() + "").build();
            return Response.created(uri).build();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Doctor login
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response loginDoctor(@FormParam("doctor_email") String doctor_email, @FormParam("doctor_password") String doctor_password) throws SQLException {
        try {
            Doctor doctor = doctorDao.loginDoctor(doctor_email, doctor_password);
            if (doctor == null) {
                throw new DataNotFoundException("Invalid email or password");
            }
            DoctorDto doctorDto = DoctorMapper.INSTANCE.toDoctorDto(doctor);
            return Response.ok(doctorDto).build();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @GET
    @Path ("/search")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,"text/csv"})
    public Response getAllDoctors (
            @BeanParam DoctorFilterDto filter ) {

        try {
            GenericEntity<ArrayList<Doctor>> docs = new GenericEntity<ArrayList<Doctor>>(doctorDao.selectAllDocs(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(docs)
                        .type(MediaType.APPLICATION_XML)
                        .build();

            }else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(docs)
                        .type("text/csv")
                        .build();
            }

            return Response
                    .ok(docs, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //GET ALL DOCTORS
//
//    @GET
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response selectAllDoctors() throws SQLException,ClassNotFoundException {
//        try {
//            GenericEntity<ArrayList<Doctor>> doctors = new GenericEntity<ArrayList<Doctor>>(doctorDao.selectAllDoctor(doctorDto)) {};
//            return Response.ok(doctors, MediaType.APPLICATION_JSON).build();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    //GET DOCTOR BY ID
    @GET
    @Path("{doctor_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response selectDoctor(@PathParam("doctor_id") int doctor_id) throws SQLException,ClassNotFoundException {
        try {
            Doctor doctor = doctorDao.selectDoctorById(doctor_id);
            if (doctor == null) {
                throw new DataNotFoundException("Doctor " + doctor_id + " not found");
            }

            DoctorDto doctorDto = DoctorMapper.INSTANCE.toDoctorDto(doctor);
            addLinks(doctorDto);

            return Response.ok(doctorDto).build();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE doctors
    @PUT
    @Path("{doctor_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public void updateDoctor(@PathParam("doctor_id") int doctor_id, Doctor doctor) throws SQLException, ClassNotFoundException {

        try {
            doctor.setDoctor_id(doctor_id);
            doctorDao.updateDoctor(doctor);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
//
//    public Response getDoctorByRate(@QueryParam("consultation_rating") int consultation_rating) {
//        ArrayList<Doctor> doctors = new ArrayList<>();
//        try {
//            ArrayList<RateDto> rateDtos = consultationDao.searchByRate(consultation_rating);
//            for (RateDto rateDto : rateDtos) {
//                doctors.add(doctorDao.selectDoctorById(rateDto.getDoctor_id()));
//            }
//
//            return Response.ok(doctors).build();
//            // return jobs;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


//    @GET
//    @Path("/available")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
//    public Response getAvailableDoctors(@QueryParam("isAvailable") boolean isAvailable) throws SQLException, ClassNotFoundException {
//        ArrayList<Doctor> doctors = doctorDao.getAvailableDoctors(isAvailable);
//
////        if (doctors.isEmpty()) {
////            return Response.status(Response.Status.NOT_FOUND)
////                    .entity(new ErrorMessage("No available doctors found"))
////                    .build();
////        }
//
//        ArrayList<DoctorDto> doctorDtos = new ArrayList<>();
//        for (Doctor doctor : doctors) {
//            DoctorDto doctorDto = DoctorMapper.INSTANCE.toDoctorDto(doctor);
//            doctorDtos.add(doctorDto);
//        }
//
//        GenericEntity<ArrayList<DoctorDto>> doctorDto = new GenericEntity<ArrayList<DoctorDto>>(doctorDtos) {};
//        return Response.ok(doctor).build();
//    }

    @GET
    @Path("/available")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response getAvailableDoctors(@QueryParam("schedule_is_available") boolean schedule_is_available) {
        try {
            ArrayList<Doctor> doctors = doctorDao.getAvailableDoctors(schedule_is_available);

            GenericEntity<ArrayList<Doctor>> doctor = new GenericEntity<ArrayList<Doctor>>(doctors) {
            };
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response.ok(doctor).type(MediaType.APPLICATION_XML).build();
            } else if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response.ok(doctor).type("text/csv").build();
            } else {
                return Response.ok(doctor, MediaType.APPLICATION_JSON).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

// Doctor Rate
    @GET
    @Path("/rate")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response getRateDoctors(@QueryParam("consultation_rating") int consultation_rating) {
        try {
            ArrayList<Doctor> doctors = doctorDao.getRateDoctors(consultation_rating);

            GenericEntity<ArrayList<Doctor>> doctor = new GenericEntity<ArrayList<Doctor>>(doctors) {
            };
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response.ok(doctor).type(MediaType.APPLICATION_XML).build();
            } else if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response.ok(doctor).type("text/csv").build();
            } else {
                return Response.ok(doctor, MediaType.APPLICATION_JSON).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    @GET
//    @Path("/available")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
//    public Response getAvailableDoctors(@QueryParam("schedule_is_availablele") boolean schedule_is_availablele) throws SQLException, ClassNotFoundException {
//        ArrayList<Doctor> doctors = doctorDao.getAvailableDoctors(schedule_is_availablele);
//
//        if (doctors == null || doctors.isEmpty()) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity(new ErrorMessage())
//                    .build();
//        }
//
//        ArrayList<DoctorDto> doctorDtos = doctors.stream()
//                .map(DoctorMapper.INSTANCE::toDoctorDto)
//                .collect(Collectors.toCollection(ArrayList::new));
//
//        return Response.ok(new GenericEntity<ArrayList<DoctorDto>>(doctorDtos) {}).build();
//    }

//


//    // Add a new schedule
//    @POST
//    @Path("/{doctor_id}/schedules")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response addSchedule(@PathParam("doctor_id") int doctor_id, ScheduleDto scheduleDto) throws SQLException {
//        try {
//            Schedule schedule = DoctorMapper.INSTANCE.toScheduleModel(scheduleDto);
//            schedule.setDoctor_id(doctor_id);
//            doctorDao.addSchedule(schedule);
//            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(schedule.getSchedule_id())).build();
//            return Response.created(uri).build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    // Get pending consultations
//    @GET
//    @Path("/{doctor_id}/consultations/pending")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response getPendingConsultations(@PathParam("doctor_id") int doctor_id) throws SQLException {
//        try {
//            ArrayList<Consultation> consultations = doctorDao.getPendingConsultaion(doctor_id);
//            GenericEntity<ArrayList<ConsultationDto>> entity = new GenericEntity<ArrayList<ConsultationDto>>((ArrayList<ConsultationDto>) ConsultationMapper.INSTANCE.toConsultationDto(consultations)) {};
//            return Response.ok(entity).build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    // Record a consultation
//    @PUT
//    @Path("/consultations/{consultationId}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response recordConsultation(@PathParam("consultationId") int consultationId, ConsultationDto consultationDto) throws SQLException {
//        try {
//            Consultation consultation = ConsultationMapper.INSTANCE.toConsultationModel(consultationDto);
//            consultation.setConsultation_id(consultationId);
//            doctorDao.recordConsultaion(consultation);
//            return Response.ok().build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    // Search patient medical records
//    @GET
//    @Path("/patients/{patientId}/records")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response searchPatientRecords(@PathParam("patientId") int patientId) throws SQLException {
//        try {
//            ArrayList<Consultation> consultations = doctorDao.searchPatientRecord(patientId);
//            GenericEntity<ArrayList<ConsultationDto>> entity = new GenericEntity<ArrayList<ConsultationDto>>((ArrayList<ConsultationDto>) ConsultationMapper.INSTANCE.toConsultationDto(consultations)) {};
//            return Response.ok(entity).build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


        // Helper method to add links to DoctorDto
        private void addLinks (DoctorDto dto){
            URI selfUri = uriInfo.getAbsolutePath();
            URI doctorsUri = uriInfo.getAbsolutePathBuilder().path(DoctorController.class).build();
            dto.addLink(selfUri.toString(), "self");
            dto.addLink(doctorsUri.toString(), "doctors");
        }
    }
