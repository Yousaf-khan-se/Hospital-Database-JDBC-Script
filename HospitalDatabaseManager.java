import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class I228797_F_q2 {

    public static void main(String[] args) {

        Connection connection = null;
        String query = null;
        try {
            String url = "jdbc:sqlserver://DESKTOP-MBDMDNQ\\SQLEXPRESS;databaseName=Testing;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            query = "USE Testing";
            if (statement.execute(query)) {
                System.out.println("Successfully Connected to Database");
            } else {
                System.out.println("Connected but could not access the database(database: Sessional)");
            }

            
            
            query = "CREATE TABLE Patients (" +
                    "PatientID INT PRIMARY KEY," +
                    "Name VARCHAR(50)," +
                    "Age INT," +
                    "ContactInformation VARCHAR(50)," +
                    "Symptoms VARCHAR(200)" +
                    ")";
            if (statement.execute(query)) {
                System.out.println("Patients Table is created successfully!");
            } else {
                System.out.println("Could not create the table Patients!");
            }

            
            
            
            query = "CREATE TABLE Doctors (" +
                    "DoctorID INT PRIMARY KEY," +
                    "Name VARCHAR(50)," +
                    "Specialization VARCHAR(50)," +
                    "ContactInformation VARCHAR(255)" +
                    ")";
            if (statement.execute(query)) {
                System.out.println("Doctors Table is created successfully!");
            } else {
                System.out.println("Could not create the table Doctors!");
            }

            
            
            query = "CREATE TABLE DoctorSchedules (" +
                    "ScheduleID INT PRIMARY KEY," +
                    "DoctorID INT," +
                    "DayOfWeek VARCHAR(10)," +
                    "StartTime TIME," +
                    "EndTime TIME," +
                    "Available INT," + //0 for 0, 1 for 1
                    "FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)" +
                    ")";
            if (statement.execute(query)) {
                System.out.println("DoctorSchedules Table is created successfully!");
            } else {
                System.out.println("Could not create the table DoctorSchedules!");
            }

            
            
            query = "CREATE TABLE Appointments (" +
                    "AppointmentID INT PRIMARY KEY," +
                    "PatientID INT," +
                    "DoctorID INT," +
                    "AppointmentDate DATE," +
                    "AppointmentTime TIME," +
                    "ReasonForVisit TEXT," +
                    "Status VARCHAR(50)," +
                    "FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)," +
                    "FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)" +
                    ")";
            if (statement.execute(query)) {
                System.out.println("Appointments Table is created successfully!");
            } else {
                System.out.println("Could not create the table Appointments!");
            }

            
            
            query = "INSERT INTO Patients (PatientID, Name, Age, ContactInformation, Symptoms) VALUES (1, 'Will', 35, '00000000000', 'Persistent headaches')";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Patient data inserted successfully!");
            } else {
                System.out.println("Failed to insert patient data!");
            }

            
            
            query = "INSERT INTO Doctors (DoctorID, Name, Specialization, ContactInformation) VALUES (1, 'Dr. Lecter', 'Neurologist', 'Contact Info')";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Dr. Lecter data inserted successfully!");
            } else {
                System.out.println("Failed to insert doctor data!");
            }
            
            
            
            query = "INSERT INTO Doctors (DoctorID, Name, Specialization, ContactInformation) VALUES (2, 'Dr. Bloom', 'Internal Medicine', 'Contact Info')";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Dr. Bloom's data inserted successfully!");
            } else {
                System.out.println("Failed to insert Dr. Bloom's data!");
            }

            
            
            query = "INSERT INTO DoctorSchedules (ScheduleID, DoctorID, DayOfWeek, StartTime, EndTime, Available) VALUES " +
                    "(1, 1, 'Monday', '09:00:00', '12:00:00', 1), " +
                    "(2, 1, 'Monday', '14:00:00', '17:00:00', 1), " +
                    "(3, 1, 'Tuesday', '09:00:00', '13:00:00', 1), " +
                    "(4, 1, 'Tuesday', '15:00:00', '18:00:00', 1)";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Dr. Lecter schedules inserted successfully!");
            } else {
                System.out.println("Failed to insert doctor schedules!");
            }
            
            
            
            query = "INSERT INTO DoctorSchedules (ScheduleID, DoctorID, DayOfWeek, StartTime, EndTime, Available) VALUES " +
                    "(5, 2, 'Monday', '09:00:00', '17:00:00', 1), " + 
                    "(6, 2, 'Tuesday', '09:00:00', '17:00:00', 1), " + 
                    "(7, 2, 'Wednesday', '09:00:00', '17:00:00', 1), " +
                    "(8, 2, 'Thursday', '09:00:00', '17:00:00', 1), " +
                    "(9, 2, 'Friday', '09:00:00', '17:00:00', 1), " + 
                    "(10, 2, 'Saturday', '09:00:00', '12:00:00', 1), " +
                    "(11, 2, 'Sunday', '09:00:00', '12:00:00', 1)";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Dr. Bloom's initial schedule and availability inserted successfully!");
            } else {
                System.out.println("Failed to insert Dr. Bloom's initial schedule and availability!");
            }

            
            
            query = "INSERT INTO Appointments (AppointmentID, PatientID, DoctorID, AppointmentDate, AppointmentTime, ReasonForVisit, Status) " +
                    "VALUES (1, 1, 1, '2024-03-25', '10:00:00', 'Persistent headaches', 'Scheduled')";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Appointment data inserted successfully!");
            } else {
                System.out.println("Failed to insert appointment data!");
            }

            
            
            query = "UPDATE DoctorSchedules SET Available = 0 WHERE DoctorID = 1 AND DayOfWeek = 'Tuesday' AND StartTime = '09:00:00'";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Doctor marked as unavailable for the appointment time!");
            } else {
                System.out.println("Failed to mark doctor as unavailable!");
            }

            
            
            query = "UPDATE Appointments SET Status = 'Canceled' WHERE AppointmentID = 1";
            int rowsAffected = statement.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Appointment canceled successfully.");
            } else {
                System.out.println("Failed to cancel appointment.");
            }

            
            
            query = "UPDATE DoctorSchedules SET Available = 1 WHERE DoctorID = 1 AND DayOfWeek = 'Tuesday' AND StartTime = '09:00:00'";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Doctor marked as available for the canceled appointment time!");
            } else {
                System.out.println("Failed to mark doctor as unavailable for the canceled appointment time!");
            }
            
            
            
            query = "UPDATE DoctorSchedules SET Available = 0 WHERE DoctorID = 2 AND DayOfWeek = 'Monday'";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Dr. Bloom's availability for Monday updated successfully!");
            } else {
                System.out.println("Failed to update Dr. Bloom's availability for Monday!");
            }

            
            
            
            query = "UPDATE DoctorSchedules SET StartTime = '12:00:00', EndTime = '15:00:00' WHERE DoctorID = 2 AND DayOfWeek = 'Wednesday'";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Dr. Bloom's schedule for Wednesday updated successfully!");
            } else {
                System.out.println("Failed to update Dr. Bloom's schedule for Wednesday!");
            }

        } catch (SQLException e) {
            System.out.println("Database not Connected");
            e.printStackTrace();
        } finally {
        	
        	if (connection != null) {
        	    try {
        	        connection.close();
        	    } catch (SQLException e) {
        	        e.printStackTrace();
        	    }
        	}
        }
    }
}

