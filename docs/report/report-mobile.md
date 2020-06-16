# Report Mobile App

## Scelte Architetturali

Abbiamo scelto per lo sviluppo dell'applicazione web l'utilizzo di **Android** nativo.
A seguito di un'analisi attenta il gruppo ha deciso di utilizzare come target la versione di Android **Pie** come consigliato dal capitolato.
Sono state scelte inoltre le seguenti SdkVersion:

- minSdkVersion : **24**
- targetSdkVersion : **28**

Abbiamo deciso di utilizzare la minSdkVersion 24 perché rendeva disponibili gli stream delle collections per la prima volta, molto utili per effettuare operazioni sui dati in maniera più semplice.

### Geolocalizzazione

Per la geolocalizzazione abbiamo scelto di utilizzare una combinazione di **GeoFences API** e **FusedLocationProvider API** per garantire un corretto bilanciamento tra il consumo di batteria e l'accuratezza e precisione della posizione.
Utilizziamo le **GeoFences API** per controllare se un utente si avvicina ad un luogo a cui è connesso, se si avvicina a sufficienza vengono attivate le **FusedLocationProvider API** per controllare l'effettiva posizione dell'utente.
Abbiamo inoltre deciso di affidarci alle **FusedLocationProvider API** anche perché effettuano una ottimizzazione dell'accuratezza della posizione e il consumo in maniera automatica, è sufficiente infatti specificare l'intervallo di aggiornamento (nel nostro caso 5 minuti) e il bilanciamento tra accuratezza e risparmio energetico (abbiamo scelto il profilo *bilanciato*).
L'intervallo di 5 minuti è riferito al contesto che andiamo ad analizzare, ossia al monitoraggio della permanenza di una persona in un luogo, possiamo dunque ritenere soddisfacente un eventuale ritardo di 5 minuti nella notifica di entrata o uscita da un luogo.

### Consumo della batteria

Per regolare il consumo della batteria, come citato sopra. Ci siamo affidati alle API di Google che gestiscono in maniera automatica il consumo.
Abbiamo scelto il profilo *bilanciato* poiché andiamo a tenere traccia di luoghi con una dimensione sufficientemente grande da non richiedere una accuratezza elevata e allo stesso tempo ci consente di aumentare la durata della batteria sotto tracciamento.

## Test

Sono stati eseguiti test di unità e test strumentali per tutti gli elementi della mobile application, arrivando ad una code coverage complessiva dell'**82.7%**.

## Problemi Aperti

I seguenti aspetti della mobile application posso essere migliorati:

- migliorare la funzionalità di ricerca nel report e nella lista delle organizzazioni
- offrire la funzionalità di visualizzare e modificare il proprio profilo anche nella mobile app oltre che nella web app
