create table Flight
(
	id uuid not null,
	flightBrand varchar(50) not null,
	flightTime timestamptz not null,
	fromCity varchar(100) not null,
	toCity varchar(100) not null,
	totalSeats numeric not null,
	seatsFilled numeric not null,
	primary key (id)
);


create table FlightBooking
(
	id uuid not null,
	tripId uuid not null,
	flightId uuid not null,
	flightAmount numeric not null,
	flightClass varchar(100) not null,
	flightBookingStatus varchar(100) not null,
	primary key (id),
	foreign key (flightId) references Flight (id)
);
