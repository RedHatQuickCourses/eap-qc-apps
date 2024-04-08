= You can optionally get started using vagrant along with libvirt that would configure below settings.

* Set hostname to server.example.com for RHEL 9 VM.
* Set a private IP address 172.25.123.10.
* Subscribe RHEL 9 server to using your private subscription credentials.
* Install OpenJDK 17.
* Install GUI packages.
* Set default target to graphical.target.
* Create a user named jboss with no login shell.

Execute the Vagrantfile using below vagrant up command:
---
[user@localhost ~]$ vagrant up --no-destroy-on-error
---
