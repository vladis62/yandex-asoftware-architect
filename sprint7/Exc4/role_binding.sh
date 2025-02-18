#!/bin/bash


bind_role() {
  local namespace=$1
  local role=$2
  local group=$3
  kubectl create rolebinding ${namespace}-${role}-${group}-binding \
    --role=${namespace}-${role} \
    --group=$group \
    -n $namespace
}

namespaces=("client" "housing_and_utilities" "accountant")


for ns in "${namespaces[@]}"; do
  bind_role $ns "admin" "client-developers"
  bind_role $ns "admin" "client-devops"
  bind_role $ns "view" "client-ops"

  bind_role $ns "admin" "housing-and-utilities-developers"
  bind_role $ns "admin" "housing-and-utilities-devops"
  bind_role $ns "view" "housing-and-utilities-ops"

  bind_role $ns "admin" "accounting-developers"
  bind_role $ns "admin" "accounting-devops"
  bind_role $ns "view" "accounting-ops"
done
