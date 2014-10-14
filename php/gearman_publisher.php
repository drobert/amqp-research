#!/usr/bin/php
<?php
require_once __DIR__.'/vendor/autoload.php';

interface ClickHandler {
    public function handleClick($msg);
}

use PhpAmqpLib\Connection\AbstractConnection;
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

class AmqpClickHandler implements ClickHandler {

    /** @var AbstractConnection $conn */
    private $conn;
    private $exchange;
    private $routingKey;

    public function __construct(AbstractConnection $conn = null, $exchange = 'danielrobert.amqpresearch.clicks', $routingKey = 'click.raw.incoming') {
        // TODO: this is not a resiliant strategy if the connection fails or needs to be reestablished later
        if (is_null($conn)) {
            include(__DIR__ . '/config.php');
            $this->conn = new AMQPStreamConnection(HOST, PORT, USER, PASS, '/', false, 'AMQPLAIN', null, 'en_US', 3, 3, null, true);
        } else {
            $this->conn = $conn;
        }

        $this->exchange = $exchange;
        $this->routingKey = $routingKey;

        // doesn't really belong here, but meh for now
        register_shutdown_function(array($this->conn, 'close'));
    }

    public function handleGearmanJob(GearmanJob $job) {
        $this->handleClick($job->workload());
    }

    public function handleClick($msg) {
        // see: https://www.rabbitmq.com/tutorials/tutorial-one-php.html
        $ch = null;

        echo "Sending '$msg' to exchange {$this->exchange} with routing key {$this->routingKey}\n";

        try {
            $ch = $this->conn->channel();

            // delivery_mode=2 --> 'persistent'
            $msg = new AMQPMessage($msg, array('content_type' => 'text/plain', 'delivery_mode' => 2));
            $ch->basic_publish($msg, $this->exchange, $this->routingKey, true); // final true is 'mandatory', meaning 'I need to know if this doesn't end up in a queue'
            $ch->close(); // *super* important as it turns out
            $ch = null;

        } catch (Exception $e) {
            echo "Failed to submit message due to " . $e->getTraceAsString() . "\n";
        } finally {
            if (!is_null($ch)) { $ch->close(); }
        }
    }
}

$ch = new AmqpClickHandler();

$worker = new GearmanWorker();
$worker->addServer(); // localhost by default

$worker->addFunction('track_click', array($ch, 'handleGearmanJob'));
while ($worker->work());

