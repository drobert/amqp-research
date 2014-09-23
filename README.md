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

Run:
> php composer.phar update
> php composer.phar install

## Running 

### Java

Run the Main.java application in your IDE, or via maven/spring boot:

> mvn spring-boot:run

Once running, the application listens for messages in queue 'clicks_in', filters and transforms them, then writes the resulting messages to queue 'clicks_out'

Messages that do not contain the string 'Dobalina' will be ignored. All other strings will end up transformed in the clicks_out queue.

#### To run without PHP support:
Log into your RabbitMQ admin, and post a message into the 'clicks_in' queue with the following properties:

* add Property 'content_type' with value 'text/plain'
* add Payload 'Mista Dobalina'

The 'clicks_out' queue should end up with a new message.

#### To run with PHP support

Start the java application as described and following the 'PHP' section below

### PHP

The PHP application waits for user input and submits the given text to the 'clicks_in' queue.

Edit the 'config.php' file before starting to be appropriate for your RabbitMQ instance. The committed version works for default RabbitMQ installs.

To run the CLI:

> php ./amqp_publisher.php

Exit by submitting 'q'

