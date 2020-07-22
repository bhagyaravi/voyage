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
