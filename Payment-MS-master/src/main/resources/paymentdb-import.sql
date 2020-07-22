create table payment
(
	id uuid not null,
	paymentType varchar(20) not null check (paymentType = 'debit-card' or paymentType = 'credit-card'),
	cardnumber varchar(20) not null,
	expiryDate timestamp not null,
	cvvnumber numeric not null,
	amount bigint not null,
	status varchar(50) not null check (status = 'in-progress' or status = 'Payment Success'),
	tripid uuid not null,
	primary key (id)
);
