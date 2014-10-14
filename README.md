amqp-research
=============

Testing out AMQP with Spring Integration and PHP

UPDATE 2014-10-13: Added Gearman support

## Requirements

### RabbitMQ

1. Install RabbitMQ
2. Install 'admin ui' plugin
3. Create durable exchange 'danielrobert.amqpresearch.clicks'
4. Create durable queues 'clicks_in' and 'clicks_out'
5. Add routing to the exchange such that 'click.raw.#' routes to 'clicks_in' and 'click.billable.#' route to 'clicks_out'

### Java

Ensure java 7+ and maven 3+ are installed and on the classpath

### PHP

Must have PHP 5.3+ with gearman pecl extension

Run:
```bash
php composer.phar install
php composer.phar update
```

### Gearman

Install gearman-job-server (http://gearman.org/getting-started/)

## Running 

### Java

Run the Main.java application in your IDE, or via maven/spring boot:

```bash
mvn spring-boot:run
```

Once running, the application listens for messages in queue 'clicks_in', filters and transforms them, then writes the resulting messages to the 'danielrobert.amqpresearch.clicks' exchange with routing key 'click.billable', which should ultimately end up in queue 'clicks_out'.

Messages that do not contain the string 'Dobalina' will be ignored. All other strings will end up transformed in the clicks_out queue.

#### To run without PHP support:

Log into your RabbitMQ admin, and post a message into the 'clicks_in' queue with the following properties:

* add Property 'content_type' with value 'text/plain'
* add Payload 'Mista Dobalina'

The 'clicks_out' queue should end up with a new message.

#### To run with PHP support

Start the java application as described and following the 'PHP' section below

### PHP

The PHP 'runner.php' application waits for user input and submits the given text to gearman as a job.

The PHP 'gearman_publisher.php' application is a gearman worker that awaits jobs from the gearman queue and sends them to the amqp 'clicks_in' queue in RabbitMQ.

Edit the 'config.php' file before starting to be appropriate for your RabbitMQ instance. The committed version works for default RabbitMQ installs.

To run the CLI:

* start gearman with sqlite3 support:
  * gearmand --verbose=INFO -q libsqlite3 --libsqlite3-db=/tmp/gearman.db
* start the CLI input listener:
  * php ./runner.php
  * exit by submitting 'q'
* start one or more gearman workers
  * php ./gearman_publisher.php

Note that there are no ordering requirements here other than running gearman first. The runner will submit jobs to gearman and they will wait for workers to be spun up to handle them. Running multiple workers does not cause any race conditions; gearman ensures only one worker receives any job.
