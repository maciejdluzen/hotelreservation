# hotelreservation

Hotel reservation and management system.

Click the following url to access the web application: https://hotelreservationmaciejd.herokuapp.com

**You may encounter the Heroku error at the first time, when entering the web page 
(error may occure up to 3 times) - it that case, please refresh the browser**

#### Overview of the functionalities

There are 3 user roles (Guest/Admin/Receptionist) within the system - each of them has different permissions and scope of functionalities.

Functionalities for GUEST role:
- Making a room reservation in a hotel in the desired location and dates,
- Browsing guest's reservations (past/current/future) and possibly cancelling them.

Functionalities for RECEPTIONIST role:
- Browsing all reservations made for receptionist's location,
- Confirming new reservations.

Functionalities for ADMIN role:
- Creating/Updating/Deleting hotels and hotels' rooms,
- Creating receptionist account and enabling/disabling access,
- Enabling/disabling access for guests accounts,
- Defining room standards and features (create/update/delete),
- Website image content management (uploading/deleting pictures of the rooms).

Unauthorized user is allowed to perform the following:
- Register new account (must confirm email address via clicking a link),
- Begin a new reservation,
- Browse the webpage content.

#### Technology Stack

Java, Spring Boot, Thymeleat, JavaScript, Ajax, Bootstrap, MySQL

The application has been deployed to Heroku cloud and uses free remote MySQL database


#### Testing

Please feel free to access the webpage and register a new guest account (valid email address is required).
If you wish to test either admin's or receptionist's functionalities, please contact me via github for credentials.  
