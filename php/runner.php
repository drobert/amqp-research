#!/usr/bin/php
<?php

$client = new GearmanClient();
$client->addServer(); // localhost by default

$prompt = "Enter message to submit to job queue ('q' to quit): ";

echo $prompt;
while($line = trim(fgets(STDIN))) {
    if (strcasecmp('q', $line) === 0 || strcasecmp('quit', $line) === 0) {
        echo "Exiting...\n";
        break;
    }
    echo "\tSending '$line' to job queue (gearman)\n";

    $job_id = $client->doBackground('track_click', $line);
    $return_code = $client->returnCode();
    if ($return_code !== GEARMAN_SUCCESS) {
        echo "Received bad return code '$return_code' for job id '$job_id'!\n";
    } else {
        echo "Successfully tracked job $job_id\n";
    }
    echo $prompt;
}

