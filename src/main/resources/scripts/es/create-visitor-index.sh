#!/usr/bin/env bash

curl -X PUT --data "@/Users/denis/Documents/Java/Idea_Projects/RestaurantServer/src/main/resources/scripts/es/create-visitor-index.json"  "http://localhost:9200/visitors"

