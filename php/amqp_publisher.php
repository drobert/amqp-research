#!/usr/bin/php
<?php

include(__DIR__ . '/config.php');
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

// see: https://www.rabbitmq.com/tutorials/tutorial-one-php.html

$exchange = '';
$queue = 'clicks_in';

//$conn = new AMQPStreamConnection(HOST, PORT, USER, PASS, VHOST, false, 'AMQPLAIN', null, 'en_US', 3, 3, null, KEEPALIVE);
$conn = new AMQPStreamConnection(HOST, PORT, USER, PASS);
$ch = null;

try {
    $prompt = "Enter message to submit to queue $queue ('q' to quit): ";

    echo $prompt;
    while($line = trim(fgets(STDIN))) {
        if (strcasecmp('q', $line) === 0 || strcasecmp('quit', $line) === 0) {
            echo "Exiting...\n";
            break;
        }
        echo "\tSending '$line' to queue\n";

        $ch = $conn->channel();

        // delivery_mode=2 --> 'persistent'
        $msg = new AMQPMessage($line, array('content_type' => 'text/plain', 'delivery_mode' => 2));
        $ch->basic_publish($msg, $exchange, $queue);
        $ch->close();
        $ch = null;
        echo $prompt;
    }
} catch (Exception $e) {
    echo "Dying due to " . $e->getTraceAsString() . "\n";
} finally {
    if (!is_null($ch)) { $ch->close(); }
    if (!is_null($conn)) { $conn->close(); } // not desirable with keepalive I'd imagine, but going to be tricky to manage
}
