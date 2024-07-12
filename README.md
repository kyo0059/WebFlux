# WebFlux

try 一下 WebFlux

## Installation

- java17
- Docker
- Docker Desktop
- pgAdmin

```bash
docker build -t test-web-flux .
docker-compose up -d
```

## Usage

- 確認是否 container 都在同一個 network

```bash
docker network inspect my-network
```

- Test Data Migrate 還沒做
要先在 container DB 用 pgadmin 自建 table與塞值

- Log 位置記得改為自己本機位置

## Todo
- 之後記得把 controller 拔掉, 走 router 風格就好