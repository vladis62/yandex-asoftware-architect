#!/bin/bash

create_role() {
  local namespace=$1
  local role=$2
  local verbs=$3
  local resources=$4
  kubectl create role ${namespace}-${role} \
    --verb=${verbs} \
    --resource=${resources} \
    -n $namespace
}

namespaces=("client" "housing_and_utilities" "accountant")

for ns in "${namespaces[@]}"; do
  create_role $ns "admin" "get,list,create,delete" "pods,services"
  create_role $ns "edit" "get,list,create,update" "pods,services"
  create_role $ns "view" "get,list" "pods,pods/log,services,events"
  create_role $ns "namespace-admin" "get,list,create,delete,update" "deployments,services,configmaps,secrets"
  create_role $ns "default" "get,list" "pods,services"
done
