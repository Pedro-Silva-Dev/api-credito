# remover os containers docker.
docker-compose down
# buildar e executar todos os comandos necessários para deixar o container pronto para uso.
docker-compose build
# subir os containers do banco de dados e do spring boot.
docker-compose up -d