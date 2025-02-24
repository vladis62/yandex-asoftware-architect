#!/bin/bash

namespaces=("client" "housing_and_utilities" "accountant")

for ns in "${namespaces[@]}"; do
  kubectl create namespace "$ns"
  if [[ $? -eq 0 ]]; then
    echo "Неймспейс '$ns' успешно создан."
  else
    echo "Ошибка при создании неймспейса '$ns'."
  fi
done
