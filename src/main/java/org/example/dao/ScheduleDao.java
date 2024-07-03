package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.ScheduleDto;

import org.example.dto.ScheduleFilterDto;
import org.example.models.Schedule;

import java.sql.*;
import java.util.ArrayList;

public class ScheduleDao {

    // Data URL
//    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\Final_ProjectV1\\FinalDataV4.db";

    private static final String SELECT_ALL_SCHEDULE = "select * from SCHEDULES";
    private static final String SELECT_ONE_SCHEDULE = "select * from SCHEDULES where schedule_id = ?";
    private static final String SELECT_ONE_SCHEDULE_BY_DOCTOR_ID = "select * from SCHEDULES where doctor_id = ?";

    //Patient search for doctor by dynamic criteria [availability] Name
    private static final String SELECT_ONE_IS_AVAILABLE = "select * from SCHEDULES where schedule_is_available = ?";
    private static final String SELECT_ONE_IS_AVAILABLE_DOCTOR_ID = "select * from SCHEDULES where doctor_id = ? AND schedule_is_available = ?";

    private static final String INSERT_SCHEDULE = "INSERT INTO SCHEDULES (doctor_id, schedule_start_time, schedule_end_time, schedule_is_available) VALUES (?, ?, ?, ?)";

    //• Doctor define and manage availability schedule
    private static final String UPDATE_SCHEDULES = "UPDATE SCHEDULES SET schedule_start_time = ?, schedule_end_time = ?, schedule_is_available = ? WHERE schedule_id = ?";
//    private static final String DELETE_SCHEDULES = "delete from SCHEDULES where schedule_id = ?";

    private static final String SELECT_SCHEDULES_BY_DOCTOR = "SELECT * FROM SCHEDULES WHERE doctor_id = ?";



//    //SELECT_ALL_SCHEDULES
//    public ArrayList<Schedule> selectAllSchedules(ScheduleDto scheduleDto) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//Connection conn = MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(SELECT_ALL_SCHEDULE);
//        ResultSet rs = st.executeQuery();
//        ArrayList<Schedule> schedules = new ArrayList<>();
//        while (rs.next()) {
//            schedules.add(new Schedule(rs));
//        }
//
//        return schedules;
//    }

    //SELECT_ONE_SCHEDULE
    public Schedule selectSchedulesById(int schedule_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_SCHEDULE);
        st.setInt(1, schedule_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Schedule(rs);
        }
        else {
            return null;
        }
    }

    //select many things

    public ArrayList<Schedule> selectAllSchedules(ScheduleFilterDto filter) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st;
        if(filter.getDoctor_id() != null) {
            st = conn.prepareStatement(SELECT_ONE_SCHEDULE_BY_DOCTOR_ID);
            st.setInt(1, filter.getDoctor_id());
        }
//        else  if(filter.getSchedule_is_available() != null) {
//            st = conn.prepareStatement(SELECT_ONE_IS_AVAILABLE);
//            st.setBoolean(1, filter.getSchedule_is_available());
//        }
//        else  if(filter.getDoctor_id() != null && filter.getSchedule_is_available() != null){
//        st = conn.prepareStatement(SELECT_ONE_IS_AVAILABLE_DOCTOR_ID);
//        st.setInt(1, filter.getDoctor_id());
//        st.setBoolean(2, filter.getSchedule_is_available());
//    }
        else {
            st = conn.prepareStatement(SELECT_ALL_SCHEDULE);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Schedule> schedules = new ArrayList<>();
        while (rs.next()) {
            schedules.add(new Schedule(rs));
        }

        return schedules;
    }


    //Insert Schedule
    public void InsertSchedule(Schedule schedule) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(INSERT_SCHEDULE);
        st.setInt(1, schedule.getDoctor_id());
        st.setString(2, schedule.getSchedule_start_time().toString());
        st.setString(3, schedule.getSchedule_end_time().toString());
        st.setBoolean(4, schedule.getSchedule_is_available());
        st.executeUpdate();
    }

//    update
public void updateSchedule(Schedule s) throws SQLException, ClassNotFoundException {
//    Class.forName("org.sqlite.JDBC");
    Connection conn = MCPConnection.getConn();
    PreparedStatement st = conn.prepareStatement(UPDATE_SCHEDULES);
    st.setInt(4, s.getSchedule_id());
    st.setString(1, s.getSchedule_start_time().toString());
    st.setString(2, s.getSchedule_end_time().toString());
    st.setBoolean(3, s.getSchedule_is_available());
    st.executeUpdate();
}

    public ArrayList<Schedule> selectSchedulesByDoctor(int doctor_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_SCHEDULES_BY_DOCTOR);
        st.setInt(1, doctor_id);
        ResultSet rs = st.executeQuery();
        ArrayList<Schedule> schedules = new ArrayList<>();
        while (rs.next()) {
            schedules.add(new Schedule(rs));
        }
        return schedules;
    }

//    public void updateScheduleAvailability(int scheduleId, boolean isAvailable) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(UPDATE_SCHEDULE_AVAILABILITY);
//        st.setBoolean(1, isAvailable);
//        st.setInt(2, scheduleId);
//        st.executeUpdate();
//    }
}
