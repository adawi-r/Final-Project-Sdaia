package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.UriInfo;
import org.example.dao.PatientDao;
import org.example.dto.PatientDto;
import org.example.dto.PatientDtoAll;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.PatientMapper;
import org.example.models.Patient;

import java.sql.SQLException;
import java.net.URI;
import java.util.ArrayList;

@Path("/PATIENTS")
public class PatientController {

    PatientDao patientDao = new PatientDao();
    PatientDto patientDto = new PatientDto();


    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    public PatientController() throws SQLException,ClassNotFoundException {
    }

    // REGISTER NEW PATIENT
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response registerPatient(PatientDtoAll patientDtoAll) throws SQLException {
        try {
            Patient patient = PatientMapper.INSTANCE.toPatientModel(patientDtoAll);

            patientDao.insertPatient(patient);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(patientDtoAll.getPatient_id())).build();
            return Response.created(uri).build();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // PATIENT LOGIN
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response loginPatient(@FormParam("patient_email") String patient_email, @FormParam("patient_password") String patient_password) throws SQLException {
        try {
            Patient patient = patientDao.loginPatien(patient_email, patient_password);
            if (patient == null) {
                throw new DataNotFoundException("Invalid email or password");
            }
            PatientDtoAll patientDtoAll = PatientMapper.INSTANCE.toPatientDto(patient);


            return Response.ok(patientDtoAll).build();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //GET ALL PATIENT

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response selectAllPatient() throws SQLException, ClassNotFoundException{
        try {
            GenericEntity<ArrayList<PatientDto>> patientDtos = new GenericEntity<ArrayList<PatientDto>>(patientDao.selectAllPatients(patientDto)) {};
            return Response.ok(patientDtos, MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET,POST,PUT")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // GET PATIENT BY ID
    @GET
    @Path("/{patient_id}")
    @Produces({//MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON, "text/csv"})
    public Response selectPatient(@PathParam("patient_id") int patient_id) throws SQLException, ClassNotFoundException {
        try {
            Patient patient = patientDao.selectPatient(patient_id);
            if (patient == null) {
                throw new DataNotFoundException("Patient with ID " + patient_id + " not found");
            }

            PatientDto patientDto = PatientMapper.INSTANCE.toPatientDtop(patient);

            return Response.ok(patientDto).build();
        } catch (SQLException |ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE PATIENT
    @PUT
    @Path("{patient_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public void updatePatient(@PathParam("patient_id") int patient_id, PatientDtoAll patientDtoAll) throws SQLException, ClassNotFoundException {

        try {
            patientDtoAll.setPatient_id(patient_id);
            patientDao.updatePatient(patientDtoAll);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


//    // REQUEST CONSULTATION
//    @POST
//    @Path("/{patient_id}/consultations/{doctor_id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response requestConsultation(@PathParam("patient_id") int patient_id, @PathParam("doctor_id") int doctor_id) throws SQLException {
//        try {
//            patientDao.requestConsultation(patient_id, doctor_id);
//            return Response.ok().build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // CHECK CONSULTATION RESULT
//    @GET
//    @Path("/{patient_id}/consultations/{consultationId}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response checkConsultationResult(@PathParam("consultationId") int consultationId) throws SQLException {
//        try {
//            Consultation consultation = patientDao.checkConsultationResult(consultationId);
//            if (consultation == null) {
//                throw new DataNotFoundException("Consultation with ID " + consultationId + " not found");
//            }
//            ConsultationDto consultationDto = ConsultationMapper.INSTANCE.toConsultationDto(consultation);
////            ConsultationDto consultationDto = new ConsultationDto();
////            consultationDto.setConsultation_id(consultation.getConsultation_id());
////            consultationDto.setPatient_id(consultation.getPatient_id());
////            consultationDto.setDoctor_id(consultation.getDoctor_id());
////            consultationDto.setStatus(consultation.getStatus());
////            consultationDto.setRating(consultation.getRating());
//            return Response.ok(consultationDto).build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // REQUEST MEDICAL HISTORY
//    @GET
//    @Path("/{patient_id}/medical-history")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response requestMedicalHistory(@PathParam("patient_id") int patient_id) throws SQLException {
//        try {
//            ArrayList<Consultation> consultations = patientDao.requestMedicalHistory(patient_id);
//            ArrayList<ConsultationDto> consultationDtos = new ArrayList<>();
//
//            for (Consultation consultation : consultations) {
//                ConsultationDto consultationDto = ConsultationMapper.INSTANCE.toConsultationDto(consultation);
////                ConsultationDto consultationDto = new ConsultationDto();
////                consultationDto.setConsultation_id(consultation.getConsultation_id());
////                consultationDto.setPatient_id(consultation.getPatient_id());
////                consultationDto.setDoctor_id(consultation.getDoctor_id());
////                consultationDto.setStatus(consultation.getStatus());
////                consultationDto.setRating(consultation.getRating());
//                consultationDtos.add(consultationDto);
//            }
//            return Response.ok(consultationDtos).build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // RATE DOCTOR
//    @PUT
//    @Path("/consultations/{consultationId}/rate/{rating}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
//    public Response rateDoctor(@PathParam("consultationId") int consultationId, @PathParam("rating") int rating) throws SQLException {
//        try {
//            patientDao.rateDoctor(consultationId, rating);
//            return Response.ok().build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Helper method to add links to PatientDto
////        private void addLinks(PatientDto dto) {
////            URI selfUri = uriInfo.getAbsolutePath();
////            URI doctorsUri = uriInfo.getAbsolutePathBuilder().path(DoctorController.class).build();
////            dto.addLink(selfUri.toString(), "self");
////            dto.addLink(doctorsUri.toString(), "doctors");
////        }
//    }

}
