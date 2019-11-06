#!/bin/bash
set -e

cupsd

ATTEMPT_COUNT=10
WAIT_SECOND=5

SUCCESS=false

for (( i = 0; i < $ATTEMPT_COUNT; i++ )); do
  echo Probing for CUPS...
  lpstat -o &> /dev/null && SUCCESS=true && break
  sleep $WAIT_SECOND
done

if [ "$SUCCESS" == false ]; then
  echo CUPS is not ready. >&2
  exit 1
fi

echo CUPS is ready.

echo "Check if the printer exists, add it if it doesn't"
lpstat -v "$PRINTER_NAME" &> /dev/null || lpadmin -p "$PRINTER_NAME" -v cups-pdf:/ -E -P /usr/share/ppd/cups-pdf/CUPS-PDF.ppd

java -jar -DQUICK_PRINT_API_KEY=$API_KEY /app/quickprint.war
