#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa_dima \
    target/demo-1.0-SNAPSHOT.jar \
    dima@192.168.0.0.107:home/dev

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa_drucoder dru@192.168.0.107 << EOF
pgrep java | xargs kill -9
nohup java -jar demo-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'