#!/bin/sh

debug() { ! "${log_debug-false}" || log "DEBUG: $*" >&2; }
log() { printf '%s\n' "$*"; }
warn() { log "WARNING: $*" >&2; }
error() { log "ERROR: $*" >&2; }
fatal() { error "$*"; exit 1; }
try() { "$@" || fatal "'$@' failed"; }

mydir=$(cd "$(dirname "$0")" && pwd -L) || fatal "Unable to determine script directory"

curl localhost:5010/v1.32/containers/create?name=test -X POST -H "Content-Type: application/json" \
    -d '{"Image":"fake_load_server"},"HostConfig":{"NanoCPUs":1000000000,"Memory":4294967296}'
