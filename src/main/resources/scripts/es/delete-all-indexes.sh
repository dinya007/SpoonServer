#!/usr/bin/env bash

SCRIPTDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

$SCRIPTDIR/delete-place-index.sh
$SCRIPTDIR/delete-visitor-index.sh