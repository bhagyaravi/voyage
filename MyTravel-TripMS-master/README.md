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
