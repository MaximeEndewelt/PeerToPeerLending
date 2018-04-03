CREATE TABLE Loaninvestment
(
   id BIGINT PRIMARY KEY not null ,
   loan_id BIGINT not null ,
   invester_id BIGINT not null,
   amount DOUBLE  not null
);