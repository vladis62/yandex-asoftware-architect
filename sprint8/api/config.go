package main

import (
	"context"
	"fmt"
	"github.com/sethvargo/go-envconfig"
)

type Config struct {
	HostPort        string `env:"HOST_PORT,required"`
	KeyCloakRealm   string `env:"KEYCLOAK_REALM,required"`
	KeyCloakCertURL string `env:"KEYCLOAK_CERT_URL,required"`
}

func GetConfig() (*Config, error) {
	cfg := &Config{}
	if err := envconfig.Process(context.Background(), cfg); err != nil {
		return nil, fmt.Errorf("ошибка загрузки переменных окружения: %w", err)
	}
	return cfg, nil
}
