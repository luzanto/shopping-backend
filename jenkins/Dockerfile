# This is the base image we use to create our image from
FROM jenkins/jenkins:2.117-alpine

MAINTAINER Nicolas Mariangel (nicolas.mariangel@usach.cl)

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY groovy/* /usr/share/jenkins/ref/init.groovy.d/
