# Istruzioni per effettuare una release

## Prerequisiti

- Avere attivato git rerere con `git config --global rerere.enabled true` e capirne il funzionamento. ref:
  - [Git Tools - Rerere](https://git-scm.com/book/en/v2/Git-Tools-Rerere)
  - [git-rerere](https://git-scm.com/docs/git-rerere)

- Capire il sistema a moduli di PowerShell. In particolare il fatto che i moduli vengono importati
  _solo nella sessione corrente_. Se si chiude e riapre la shell il modulo non sarà più disponibile, e va reimportato.

## Preparazione

1. Posizionarsi sulla branch che va rilasciata.
1. Eseguire `git merge master` e risolvere i conflitti.
1. Eseguire `git rebase master`. Se rerere è abilitato, i conflitti dovrebbero venire risolti automaticamente grazie
   allo step precedente
1. Determinare il successivo numero di versione `x.y.z`.
1. Importare il modulo contenente le funzioni per effettuare la release automatica con `Import-Module -PassThru ./.release/StalkerReleaser.psm1`
1. Invocare il comando `Invoke-PrepareRelease`
1. Controllare che il commit generato automaticamente sia andato a buon fine.
1. Effettuare un `git push` del commit generato automaticamente.
1. Marcare la PR come "Ready for review".
1. Attendere che i verificatori completino la Code Review.

## Release

Quando i verificatori hanno completato la Code Review:

1. Effettuare un `Merge` della PR (squash o normale).
1. Posizionarsi su `master` e assicurarsi che sia up to date con `git pull origin master`.
1. Eseguire `npm run tag-release`.
1. Effettuare un push della tag, utilizzando il comando scritto nell'output dello step precedente.
