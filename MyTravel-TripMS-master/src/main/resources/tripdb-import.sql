create table Trip
(
	id uuid not null,
	userid varchar(256) not null,
	tripBookingDate timestamptz not null,
	tripType varchar(256) not null check (
		tripType = 'one-way' OR
		tripType = 'round-trip' OR
		tripType = 'multi-city'
	),
	primary key (id)
);

create table GroupMember
(
	id uuid not null,
	tripId uuid not null,
	name varchar(256) not null,
	age numeric not null check (age >= 0 OR age < 120),
	sex varchar(10) not null check (sex = 'male' or sex = 'female'),
	contact varchar(50) not null,
	address varchar(512),
	primary key (id),
	foreign key (tripId) references trip (id)
);

create table MemberSeatMapping
(
	id uuid not null,
	groupMemberId uuid not null,
	flightId uuid not null,
	seatNumber numeric check(seatNumber>=0 OR seatNumber<600),
	primary key (id),
	foreign key (groupMemberId) references GroupMember (id)
);
