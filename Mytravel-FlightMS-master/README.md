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
