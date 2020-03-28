# Istruzioni per effettuare una release

## Preparazione

- Generare il changelog automatico eseguendo lo script `.release/prepare-current.ps1`
- Effettuare un `git push` del commit generato automaticamente
- Marcare la PR come "Ready for review"
- Attendere che i verificatori completino la Code Review

## Release

Quando i verificatori hanno completato la Code Review:

- Effettuare uno `Squash Merge` della PR
- Posizionarsi su `master` e assicurarsi che sia up to date con `git pull origin master`
- Eseguire `.release/tag-current.ps1`
- Effettuare un push della tag, utilizzando il comando scritto nell'output dello step precedente
