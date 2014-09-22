amqp-research
=============

Testing out AMQP with Spring Integration and PHP

## Requirements

### Java

Install RabbitMQ and the admin ui plugin
Create queues 'clicks_in' and 'clicks_out'

Must have java and maven installed

### PHP

Must have PHP 5.3+
App requires Composer (bundled)

## Running 

### Java

Run the Main.java application in your IDE, or via maven/spring boot:

> mvn spring-boot:run

Once running, log into your RabbitMQ admin, and post a message into the 'clicks_in' queue with the following properties:

* add Property 'content_type' with value 'text/plain'
* add Payload 'Mista Dobalina'

The 'clicks_out' queue should end up with a message with content 'Mr. Bob Dobalina'

