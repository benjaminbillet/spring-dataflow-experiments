#!/bin/sh
set -e

echo "Running application..."
exec java -jar ./*.jar
