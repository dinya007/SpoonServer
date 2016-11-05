#!/usr/bin/env bash

curl -X PUT --data "@create-place-index.json"  "http://localhost:9200/places"

