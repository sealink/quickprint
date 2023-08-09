#!/bin/bash

# Define your mount point
MOUNT_POINT="/mnt/nfs"

# Check if the mount point is already mounted
if mountpoint -q "$MOUNT_POINT"; then
    echo "Mount point $MOUNT_POINT is already mounted."
else
    echo "Mount point $MOUNT_POINT is not mounted. Mounting now..."
    
    SFTP_PASSWORD=$(sudo /opt/elasticbeanstalk/bin/get-config environment -k SFTP_PASSWORD)
    echo "$SFTP_PASSWORD" > /tmp/credentials
    sudo sshfs -o allow_other,StrictHostKeyChecking=no,password_stdin sftp-user@172.18.0.12:/ /mnt/nfs < /tmp/credentials
    rm /tmp/credentials
    
    echo "Mounting complete."

    echo "Reboot docker now..."
    sudo service docker restart
    echo "Docker has been restarted."
fi
