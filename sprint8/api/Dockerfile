FROM golang:1.23.3 as builder

WORKDIR /app

COPY go.mod go.sum ./

RUN go mod download

COPY . .

RUN CGO_ENABLED=0 GOOS=linux go build -o main -ldflags="-s -w" -trimpath

FROM alpine:latest

COPY --from=builder /app/main /app/main

WORKDIR /app

EXPOSE 8000

CMD ["./main"]