#!/bin/bash

for i in {1..100}; do
    curl -X GET http://localhost:8080/user
done