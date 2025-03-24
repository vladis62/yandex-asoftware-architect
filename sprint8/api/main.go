package main

import (
	"encoding/json"
	"errors"
	"fmt"
	"github.com/MicahParks/keyfunc"
	"log"
	"net/http"
	"time"
)

type Report struct {
	Info string `json:"info"`
}

func getReports(w http.ResponseWriter, _ *http.Request) {
	report := Report{Info: "Это замечательный отчет"}

	w.Header().Set("Content-Type", "application/json")
	if err := json.NewEncoder(w).Encode(report); err != nil {
		http.Error(w, "Ошибка кодирования отчета", http.StatusInternalServerError)
		log.Printf("Ошибка кодирования JSON: %v", err)
	}
}

func main() {
	cfg, err := GetConfig()
	if err != nil {
		log.Fatalf("Failed to load config: %v", err)
	}

	jwks, err := keyfunc.Get(cfg.KeyCloakCertURL, keyfunc.Options{
		RefreshInterval: time.Hour,
	})
	if err != nil {
		log.Fatalf("Failed to create JWKS from URL %s: %v", cfg.KeyCloakCertURL, err)
	}

	serverAddress := fmt.Sprintf(":%s", cfg.HostPort)
	done := make(chan struct{})
	router := http.NewServeMux()

	keycloakAuthMiddleware := KeyCloakMiddleware(jwks, "prothetic_user")
	router.Handle("/reports", keycloakAuthMiddleware(http.HandlerFunc(getReports)))

	server := &http.Server{
		Addr:    serverAddress,
		Handler: corsMiddleware(router),
	}

	log.Printf("Starting server on %s", serverAddress)
	if err := server.ListenAndServe(); !errors.Is(err, http.ErrServerClosed) {
		log.Fatalf("HTTP server error: %v", err)
	}

	<-done
	log.Println("Server gracefully stopped")
}
