# Hospital Database Management via JDBC

## Description
An advanced standalone Java script utilizing JDBC to dynamically generate and manage a complete relational database schema for a Hospital Management System in Microsoft SQL Server.

## Concepts Demonstrated
* **Relational Database Design:** Automated creation of a schema containing `Patients`, `Doctors`, `DoctorSchedules`, and `Appointments` tables.
* **SQL Constraints:** Handling Primary Keys and Foreign Key relationships programmatically via Java.
* **Advanced JDBC Execution:** Managing complex state changes, such as inserting schedules, booking appointments, and subsequently updating doctor availability statuses using `executeUpdate`.

## Features
* Automatically attempts to connect to a SQL Server database named `Testing`.
* Drops/Creates tables systematically.
* Populates mock data for doctors, patients, and weekly schedules.
* Demonstrates transactional logic (e.g., If an appointment is created/canceled, the doctor's availability is updated accordingly).

## Setup & Configuration
1. **Database:** Ensure Microsoft SQL Server is running and a blank database named `Testing` exists.
2. **JDBC Driver:** Ensure `mssql-jdbc.jar` is added to your project dependencies.
3. **Connection String:** Update the connection URL in the code to match your server name (currently set to `DESKTOP-MBDMDNQ\\SQLEXPRESS`).

## How to Run
Compile and run the Java file. Check your console for the step-by-step success logs, and then open SQL Server Management Studio (SSMS) to verify the generated tables and data.
