-- Create schema and table using code from table.sql, then execute this query to fill the table

DROP TABLE IF EXISTS your_table_name;

SELECT * FROM your_schema_name.your_table_name;

INSERT INTO your_schema_name.your_table_name VALUES
(NULL, 'Jan Kowalski', '1234', '12345678910', 'Janusz', 'kowalski@gmail.com', 'Poland, Cracow ', 150, 0), 
(NULL, 'Gregory Doom', '4321', '98765432111', 'John', 'doom@gmail.com', 'Spain, Madrid ', 350, 59),
(NULL, 'Lynn Bennett', '1111', '62749187545', 'Serana', 'bennett@gmail.com', 'France, Paris ', 50, 0),
(NULL, 'Dianne Lowe', '2242', '59087623456', 'Ana', 'dianne@gmail.com', 'Ireland, Dublin ', 1500, 250);