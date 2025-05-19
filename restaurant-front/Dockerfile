# Étape 1 : builder l'application React
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Étape 2 : serveur Nginx pour servir l'application buildée
FROM nginx:alpine

# Copier les fichiers buildés dans le dossier de Nginx
COPY --from=build /app/build /usr/share/nginx/html

# Expose le port 80
EXPOSE 80

# Démarrer Nginx en premier plan
CMD ["nginx", "-g", "daemon off;"]

