#FROM node:lts-alpine
FROM node:16-alpine
RUN npm install -g http-server

WORKDIR /app

COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

EXPOSE 8081
#CMD ["npm", "run", "serve"]
CMD [ "http-server", "dist" ]