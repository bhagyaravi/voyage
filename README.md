# MyTravel-TripMS

This is the trip micro service for the travel application.<br>
It has REST calls for performing CRUD operations on trip, group member, seat member mapping tables.<br>

The table definitions are as follows:
<br><br>
create table Trip<br>
(<br>
	id uuid not null,<br>
	userid varchar(256) not null,<br>
	tripBookingDate timestamptz not null,<br>
	tripType varchar(256) not null check (<br>
		tripType = 'one-way' OR<br>
		tripType = 'round-trip' OR<br>
		tripType = 'multi-city'<br>
	),<br>
	primary key (id)<br>
);<br>
<br><br>
create table GroupMember<br>
(<br>
	id uid not null,<br>
	tripId uuid not null,<br>
	name varchar(256) not null,<br>
	age numeric not null check (age >= 0 OR age < 120),<br>
	sex varchar(10) not null check (sex = 'male' or sex = 'female'),<br>
	contact varchar(50) not null,<br>
	address varchar(512),<br>
	primary key (id),<br>
	foreign key (tripId) references trip (id)<br>
);<br>
<br><br>
create table MemberSeatMapping<br>
(<br>
	id uuid not null,<br>
	groupMemberId uuid not null,<br>
	flightId uuid not null,<br>
	seatNumber numeric check(seatNumber>=0 OR seatNumber<600),<br>
	primary key (id),<br>
	foreign key (groupMemberId) references GroupMember (id)<br>
);<br>


# Mytravel-FlightMS
This is the flight micro service for the travel application.<br> 
It has APIs for CRUD operations on flight and flight booking.<br><br>

The table definitions of flight and flight booking are as follows:<br>
create table Flight<br>
(<br>
	id uuid not null,<br>
	flightBrand varchar(50) not null,<br>
	flightTime timestamptz not null,<br>
	fromCity varchar(100) not null,<br>
	toCity varchar(100) not null,<br>
	totalSeats numeric not null,<br>
	seatsFilled numeric not null,<br>
	primary key (id)<br>
);<br>
<br><br>

create table FlightBooking<br>
(<br>
	id uuid not null,<br>
	tripId uuid not null,<br>
	flightId uuid not null,<br>
	flightAmount numeric not null,<br>
	flightClass varchar(100) not null,<br>
	primary key (id),<br>
	foreign key (flightId) references Flight (id)<br>
);<br>


<br><br>
Currently, the implementation contains flight search, booking flight for end user. And also adding flights(through the backend process).<br>

# Payment-MS
This is the payment microservice for the travel application.<br>
Once the payment is successful, message is sent to Kafka on the topic payment status.<br>
Flight Micro service and Trip Micro service subscribe to this topic and once they receive the message, will update the database tables accordingly.<br><br>

The table definition is as follows:<br>

create table payment<br>
(<br>
	id uuid not null,<br>
	paymentType varchar(20) not null check (paymentType = 'debit-card' or paymentType = 'credit-card'),<br>
	cardnumber varchar(20) not null,<br>
	expiryDate timestamp not null,<br>
	cvvnumber numeric not null,<br>
	amount bigint not null,<br>
	status varchar(50) not null check (status = 'in-progress' or status = 'Payment Success'),<br>
	tripid uuid not null,<br>
	primary key (id)<br>
);<br>
