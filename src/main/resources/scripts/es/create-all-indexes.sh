#!/usr/bin/env bash

SCRIPTDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

$SCRIPTDIR/create-place-index.sh
$SCRIPTDIR/create-visitor-index.sh