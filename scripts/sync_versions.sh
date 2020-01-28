#!/usr/bin/env sh
TAG=$1
DAIKON_ROOT="$(dirname $0)/.."
SCRIPT_NAME="publish.sh"

exclude_main_daikon() {
    cat - | grep -v "./daikon/"
}

extensions_scripts_folders() {
    find ${DAIKON_ROOT}/.. -name "${SCRIPT_NAME}" | exclude_main_daikon | sed "s/${SCRIPT_NAME}$//"
}

for folder in $(extensions_scripts_folders)
do
    (
        cd ${folder}
        echo "deploy: $(pwd)"
#        git stash
#        git pull --rebase
#        ./${SCRIPT_NAME} ${TAG}
#        git stash pop
    )
done