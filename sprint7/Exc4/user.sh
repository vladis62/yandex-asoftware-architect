#!/bin/bash

CERT_DIR="certificates"
mkdir -p $CERT_DIR

create_user_cert() {
  local username=$1
  local group=$2

  if [[ -f "$CERT_DIR/${username}.crt" && -f "$CERT_DIR/${username}.key" ]]; then
    echo "Сертификаты для пользователя ${username} уже существуют, пропуск создания."
    return
  fi

  echo "Создание сертификатов для ${username}..."

  openssl genrsa -out ${CERT_DIR}/${username}.key 2048 && \
  openssl req -new -key ${CERT_DIR}/${username}.key -out ${CERT_DIR}/${username}.csr -subj "/CN=${username}/O=${group}" && \
  openssl x509 -req -in ${CERT_DIR}/${username}.csr -CA ~/.minikube/ca.crt -CAkey ~/.minikube/ca.key -CAcreateserial -out ${CERT_DIR}/${username}.crt -days 365

  if [[ $? -eq 0 ]]; then
    echo "Сертификаты для ${username} успешно созданы."
  else
    echo "Ошибка при создании сертификатов для ${username}."
  fi
}

create_user_cert "client-developer" "client-developers"
create_user_cert "client-devops" "client-devops"
create_user_cert "client-ops" "client-ops"

create_user_cert "housing-and-utilities-developer" "housing-and-utilities-developers"
create_user_cert "housing-and-utilities-devops" "housing-and-utilities-devops"
create_user_cert "housing-and-utilities-ops" "housing-and-utilities-ops"

create_user_cert "accounting-developer" "accounting-developers"
create_user_cert "accounting-devops" "accounting-devops"
create_user_cert "accounting-ops" "accounting-ops"
