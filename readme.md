# Healthcare Management Application

## Description
This is a healthcare management system developed using Spring Boot and REST services. The application allows healthcare providers to manage patient records, schedule appointments, and prescribe medication. The system also integrates with a third-party service, such as Google Maps, to provide directions to healthcare facilities.

## Prerequisites
Before running the application, ensure that you have the following installed on your system:
- Java Development Kit (JDK) 11 or higher
- Apache Maven
- Google Maps API key

## Configuration
To configure the application, you need to set up the Google Maps API key. Edit the `application.properties` file and provide your Google Maps API key as follows:
```
google.maps.api.key=YOUR_GOOGLE_MAPS_API_KEY
```

## Build and Run
To build and run the application, follow these steps:

1. Clone the repository:
```
git clone https://github.com/yourusername/healthcare-management.git
```

2. Change into the project directory:
```   
cd healthcare-management
```

3. Build the application using Maven:
```
mvn clean package
```

## Run the application
After building the application, you can run it using the following command:
```
java -jar target/healthcare-management-0.1.0.jar
```

The application will start, and you can access it at `http://localhost:8080`.

## API Endpoints
The following are the API endpoints provided by the application:

1. Get all patients:
    - Endpoint: `GET /api/patients`

2. Get a patient by ID:
    - Endpoint: `GET /api/patients/{id}`

3. Create a new patient:
    - Endpoint: `POST /api/patients`
    - Request body: JSON representation of the patient

4. Update a patient:
    - Endpoint: `PUT /api/patients/{id}`
    - Request body: JSON representation of the updated patient

5. Delete a patient:
    - Endpoint: `DELETE /api/patients/{id}`

6. Schedule an appointment:
    - Endpoint: `POST /api/appointments`
    - Request body: JSON representation of the appointment

7. Get all appointments:
    - Endpoint: `GET /api/appointments`

8. Get an appointment by ID:
    - Endpoint: `GET /api/appointments/{id}`

9. Prescribe medication:
    - Endpoint: `POST /api/medications`
    - Request body: JSON representation of the medication

10. Get all medications:
    - Endpoint: `GET /api/medications`

## Unit Tests
The application contains unit tests to ensure the correctness of its functionality. To run the unit tests, use the following command:
```
mvn test
```

## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

## Feedback

**Was it easy to complete the task using AI?**

    That was a challenging task. 

**How long did task take you to complete?**

    It took me around 4 hours and I didn't complete the whole task.

**Was the code ready to run after generation? What did you have to change to make it usable?**

    Half of the code was ready to run after generation, but if it wasn't, I just wrote more prompts to ChatGPT,
    with additional hints to him or just googled the information, because it's knowledge is limited.

**Which challenges did you face during completion of the task?**

    Lack of knowledge of the ChatGPT.

**Which specific prompts you learned as a good practice to complete the task?**

    For example, step-by-step, error handling, testing and documentation prompts. 