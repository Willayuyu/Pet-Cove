# Instructions to run the application

## Usual way

In a usual way, we simply run Springboot back-end application and Vue-cli front-end application separately. 

### Back-end

First, make sure you have installed java, mysql and maven on your computer. 

If you're using a java IDE, you can simply run the "SpringbootApplication" java class. Or, we manage the dependencies using maven, so simple operations are needed:

`mvn clean install `

`mvn compile`

Then you will get .jar file in the '/target' path.

After running, the back-end server will listen on port 8080.

### Front-end

Make sure you have npm, node and @vue-cli installed on your computer.

Then, you should run these commands under the "/Front-end" path: 

`npm install`

`npm run serve`

Then you can reach the web page through http://localhost:8081/ .

## Using Docker

We tried to use docker to manage our application. However, we met several problems. Some of them are solved and some are not. 

We split our application service to back-end and front-end.

### Back-end

For the backend, you can find the dockerfile in "/Back-end" path. 

We use eclipse-temurin:17-jdk as the basic image here. Then we use maven to compile the .jar file for the Springboot application and then COPY the .jar file to the image. This is how you can run a container of this image:

`docker build -t backend`

`docker run -p 8080:8080 backend`

### Front-end

For the frontend, you can find the dockerfile in "/Front-end" path.

We use node:16-alpine as the basic image here. This is how you can run a container of this image:

`docker build -t frontend`

`docker run -p 8081:8081 frontend`

### Docker Compose

You can also find the docker compose file in the root path of the project "Pet Cove". Then you can just run `docker-compose up` to run both back and front services.

The docker way to run the back-end is stable. However, front-end is recommended to use the usual way to run, since it may appear that the web page cannot be shown after the corresponded link is opened.
