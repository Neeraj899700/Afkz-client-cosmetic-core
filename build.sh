#!/bin/bash
cd /storage/emulated/0/Afkz-client-cosmetic-core

MSG="${1:-Auto build}"
echo ">> Committing: $MSG"
git add .
git commit -m "$MSG"
git push

echo ">> Waiting for GitHub Actions..."
sleep 10

RUN_ID=$(gh run list --repo Neeraj899700/Afkz-client-cosmetic-core --limit 1 --json databaseId -q '.[0].databaseId')
echo ">> Build ID: $RUN_ID - watching..."

gh run watch "$RUN_ID" --repo Neeraj899700/Afkz-client-cosmetic-core --exit-status

echo ">> Downloading JAR..."
gh run download "$RUN_ID" --repo Neeraj899700/Afkz-client-cosmetic-core --name afkz-client-cosmetic-core --dir build/libs/

echo ">> DONE! JAR at: build/libs/"
ls -la build/libs/*.jar
