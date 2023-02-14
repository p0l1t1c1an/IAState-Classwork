
# Relational Databases

### Concepts
- Relations 
    - Set of tuples (rows)
    - DB has table of relations or tuples
- Schema
    - Each relation has fixed set of attributes (columns)
    - Constaints on each attribute
- Tuples
    - All tuple in relation has same attributes
    - Tuples in relaiton have any order
- Attributes
    - Has domain, set of possible values
- Insert Update and Delete
    - Relations can be modified 
    - Each relation has a primary key that uniquely identifies each of tuple
- Primary Key
    - Attribute can be made a primary key if it does not repeat in different tuples
    - Unique ID


### Guarantees
- Atomicity
    - All or nothing
    - Entire transaction is applied or none of it
- Consistency
    - Goes from one valid state to another
    - Always conforms to schema and constraints
- Isolation
    - Concurrent and serial transaction have same outcome
    - I do 100 at once is same as doing each after one another
- Durability
    - Data remains as it is after a transaction
    - Failure does not lose at failure like power
    - Can recover from abnormal trnasactions


### Categories
- Online Transaction Processing (OLTP)
    - eCommerce where everything is transactions
    - High volume of IUD
- Online Analytical Processing (OLAP)
    - Data warehouses
    - Analysis for business decisions
    - Lower volume of IUD but more complex use of data


### Widely Used RDB
- MySQL

