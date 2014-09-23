<?php

require_once __DIR__.'/vendor/autoload.php';

define('HOST', 'localhost');
define('PORT', 5672);
define('USER', 'guest');
define('PASS', 'guest');
define('VHOST', '/');
define('KEEPALIVE', false);

//If this is enabled you can see AMQP output on the CLI
define('AMQP_DEBUG', false);

// SUPER IMPORTANT!!
/*
define('AMQP_WITHOUT_SIGNALS', true);

$pcntlHandler = function ($signal) {
    switch ($signal) {
        case \SIGTERM:
        case \SIGUSR1:
        case \SIGINT:
            // some stuff before stop consumer e.g. delete lock etc
            exit(0);
            break;
        case \SIGHUP:
            // some stuff to restart consumer
            break;
        default:
            // do nothing
    }
};

declare(ticks = 1) {
    pcntl_signal(\SIGTERM, $pcntlHandler);
    pcntl_signal(\SIGINT,  $pcntlHandler);
    pcntl_signal(\SIGUSR1, $pcntlHandler);
    pcntl_signal(\SIGHUP,  $pcntlHandler);
}
*/
