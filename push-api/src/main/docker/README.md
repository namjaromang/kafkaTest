# Docker 구성
원메딕스 서비스 Docker 구성 설명

## Docker 설치방법

### local(개발환경)
 - [Docker Desktop 설치](https://www.docker.com/products/docker-desktop)

### Amazon EC2
 - Docker 설치 
 ```
 sudo yum update
 sudo yum install -y docker
 sudo usermod -a -G docker ec2-user
 sudo service docker start
 sudo chkconfig docker on
 ```
 - Docker compose 설치 
 ```
 sudo curl -L https://github.com/docker/compose/releases/download/1.21.0/docker-compose-`uname -s`-`uname -m` | sudo tee /usr/local/bin/docker-compose > /dev/null
 sudo chmod +x /usr/local/bin/docker-compose
 ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
 docker-compose --version
 ```

### push-db 기동
```
docker-compose -f src/main/docker/push-db.yml up -d
```

### push-api 기동
```
docker-compose -f src/main/docker/push-api.yml up -d
```
