# Install Docker

# curl -fsSL https://get.docker.com -o get-docker.sh

# chmod +x ./get-docker.sh

# ./get-docker.sh

# Install MySQL

apt-get update -y

apt-get install mysql-client mysql-server -y

# Maven Installation

apt install gradle -y

# Java installation

apt install default-jdk -y


# Currently in root folder of the project
#cd XMeme-Backend

gradle build

cp build/libs/xmeme-0.0.1-SNAPSHOT xmeme.jar

