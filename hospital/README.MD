# Hospital

## A pet project that uses Spring Core, Spring MVC, Spring Data Jpa, Spring Security.

### Required functionality:
1. Three types of users - Doctor, Patient, Chief medical officer
2. Users can view list of doctors, see their schedule, send a request to visit a particular doctor with stated reason (health issues, medical examination etc.)
3. Chief manages requests, can either approve it or reject with some reason. He also can change the schedule of doctors
4. Doctors have access to a medical card of each patient, can view their schedule.

In order to run application locally:
1. Create 'hospital' DB
2. Run app with IDE
3. Import 'hospital.postman-collection.json' into Postman
4. Use requests to check application's functionality