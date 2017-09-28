#!/usr/bin/env bash

if sudo ./runcrud.sh 
	then
		firefox http://localhost:8080/crud/v1/task/getTasks
	else
		echo "błąd runcrud.sh"
fi
