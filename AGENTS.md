# AGENTS.md - Build Workflow

## Project
Afkz Client Cosmetic Core - Fabric mod for Minecraft 26.1.2

## Build Workflow (EVERY TIME)
1. Edit source files
2. `cd /storage/emulated/0/Afkz-client-cosmetic-core`
3. `git add . && git commit -m "description" && git push`
4. GitHub Actions auto-builds (~1 min)
5. Download JAR: `gh run download <run_id> --repo Neeraj899700/Afkz-client-cosmetic-core --name afkz-client-cosmetic-core --dir build/libs/`

## Quick One-Liner
```bash
cd /storage/emulated/0/Afkz-client-cosmetic-core && git add . && git commit -m "$1" && git push && sleep 8 && RUN_ID=$(gh run list --repo Neeraj899700/Afkz-client-cosmetic-core --limit 1 --json databaseId -q '.[0].databaseId') && gh run watch $RUN_ID --repo Neeraj899700/Afkz-client-cosmetic-core --exit-status && gh run download $RUN_ID --repo Neeraj899700/Afkz-client-cosmetic-core --name afkz-client-cosmetic-core --dir build/libs/
```

## Install JAR
Copy `build/libs/afkz-client-cosmetic-core-1.0.0.jar` to Mojo Launcher mods folder.

## Key Facts
- MC 26.1 is NOT obfuscated (no yarn mappings needed)
- Use `net.fabricmc.fabric-loom` plugin (not `fabric-loom`)
- Use `implementation` not `modImplementation`
- Access widener uses `official` namespace (not `named`)
- Java 25 required
- GitHub repo: https://github.com/Neeraj899700/Afkz-client-cosmetic-core
