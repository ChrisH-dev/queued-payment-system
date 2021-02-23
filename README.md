# Queued Payment System
This project was created as a way to gain experience with techniques and technologies which are not (or cannot reasonably be) used in WPG.

The project takes for the form of a payment system (accepts a request from a merchant, sends a request to an acquirer and then returns the result back the merchant) and utilises the following:

* microservices
* message queues for inter-service communication
* Docker and Kubernetes

A more detailed description of the architectural decisions can be found [here]

## Getting Started
These instructions should allow you to get the project working locally.

### Prerequesites
stuff that needs to be installed before starting
* Java 8+
* Maven
* Docker
* Kubernetes
* Local docker registry setup

### Building
1. Clone the repo

   `git clone git@github.com:ChrisH-dev/queued-payment-system.git`

2. Build each of the supporting components to add them to your local Maven repository. In the following directories:

   `queue-comms`

   `payment-orchestrator-comms`

   `acquirer-communicator-comms`

   run

   `mvn clean install`

3. Each of the microservices is build independently. In the following directories:

   `api-gateway`
   
   `payment-orchestrator`
   
   `acquirer-communicator-comms`
   
   run

   `mvn clean install`

   `docker push localhost:32000/{directory name}` (replacing `{directory name}` as appropriate)

### Deployment
The Kubernetes deployment configuration files in the `kubernates-definitions` directory. Run the following:

`kubectl create -f rabbitmq-controller.yaml`

`kubectl create -f rabbitmq-service.yaml`

`kubectl create -f api-gateway-pod.yaml`

`kubectl create -f payment-orchestrator-pod.yaml`

`kubectl create -f acquirer-communicator-pod.yaml`

Enable port forwarding to the api-gateway

`kubectl port-forward api-gateway 8082:8080`

## Usage
Go to: http://localhost:8082/payment/test2

This will submit a dummy payment request to the system and should display a page containing a payment ID.

Logs can be viewed using the following command

`kubectl logs -f api-gateway` (for the api-gateway)
