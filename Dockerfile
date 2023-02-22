# Utilisez l'image officielle de webMethods comme point de départ
FROM sagcr.azurecr.io/webmethods-integrationserver:10.15

# Définir les variables d'environnement
ENV NODE_NAME=node
ENV PORT=5555

# Copier les fichiers de configuration du service webMethods Flow
COPY * /opt/softwareag/IntegrationServer/instances/default/package

# Exposer le port utilisé par le service webMethods Flow
EXPOSE 5555

# Démarrer le serveur webMethods
CMD ["/opt/softwareag/IntegrationServer/bin/server.sh", "start", "node"]
