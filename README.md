# Officina
 
Contesto di riferimento e obiettivi
Contesto
Si vuole realizzare un sistema informativo su Web per la prenotazione degli interventi meccanici di una piccola officina
 • Oltre agli utenti occasionali, interagiscono con il sistema due tipologie di attori: i clienti e l'amministrazione 
• Un cliente può svolgere le seguenti operazioni :
 – Consultazione tipologie di interventi offerti dall'officina 
– Consultazione degli interventi effettuati presso l'officina 
• L'amministrazione può svolgere le seguenti operazioni:
 – Inserimento di una tipologia di intervento
 – Inserimento di un intervento 
– Inserimento di un cliente nella anagrafica clienti 
– Aggiornamento dei dati di un cliente
Per ogni tipologia di intervento sono di interesse un nome, un codice, una descrizione, un costo
Per ogni intervento è necessario memorizzare, oltre al cliente, la data di prenotazione (con data e ora in cui è stata effettuata la prenotazione), la data in cui è stato effettuato l'intervento, il nome del meccanico 
Per ogni meccanico è necessario gestire: nome, cognome, lista degli interventi che può effettuare
-Obiettivi
L’obiettivo è creare un sistema informativo su Web che contempli i seguenti casi d’uso che seguono
Casi D’Uso
Caso d'uso UC1: consulta tipi di intervento: 
Attore primario: cliente.
Scenario principale di successo: 
dopo aver eseguito l’accesso al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di cliente e aver cliccato su “lista interventi”,
il sistema mostra tutte le tipologie di intervento che si possono svolgere presso l’officina con la descrizione e il prezzo relativi ad essa.
Caso d'uso UC2: Cronologia interventi cliente: 
Attore primario: cliente.
Scenario principale di successo: 
dopo aver eseguito l’accesso al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di cliente e aver cliccato su “cronologia interventi”,
il sistema mostra tutti gli interventi svolti presso l’officina con nome dell’intervento, nome del meccanico assegnato all’intervento e data intervento. Sarà possibile vedere lo stato dell’intervento attraverso due scritte che comunicano se l’intervento è ancora in corso oppure è concluso.


Caso d'uso UC4: visualizza e aggiorna clienti.
Attore primario: amministrazione. 
Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministratore”.
Scenario principale di successo: 
Dopo aver cliccato su “Clienti” il sistema mostrerà la pagina con i dati di tutti i clienti registrati al sito. L’amministratore potrà aggiornare l’anagrafica del cliente cliccando sull’apposito tasto(edit) sarà possibile aggiornare i dati direttamente sulla tabella( nome, cognome, numero), dopo aver aggiornato i dati nel DB il sistema visualizzerà nuovamente l’elenco di tutti i clienti.
L’amministratore potrà aggiungere un nuovo cliente cliccando sul tasto “Aggiungi un cliente” che aprirà la form per creare una nuova anagrafica, un nuovo username e password, dopo aver controllato se i dati inseriti sono corretti e che il numero non risulti già registrato il sistema salverà tutto nel DB.


Caso d'uso UC5: visualizza e aggiungi meccanico
Attore primario: amministrazione.
Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministratore”.
Scenario principale di successo: 
Dopo aver cliccato su “Visualizza meccanici” il sistema mostrerà la pagina con i dati di tutti i meccanici. 
L’amministratore potrà aggiungere un nuovo meccanico cliccando sul tasto “Aggiungi” che aprirà la form per creare una nuova anagrafica e selezionare tutte le tipologie di intervento che il meccanico è abilitato a svolgere, dopo aver inserito i campi correttamente, il sistema salverà tutto nel DB.
Caso d'uso UC6: visualizza e aggiungi tipologie intervento.
Attore primario: amministrazione. 
Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministratore”.
Scenario principale di successo: 
Dopo aver cliccato su “Tipologie” il sistema mostrerà la pagina con l’elenco di tutte le tipologie di intervento svolte dall’officina con relativa descrizione e prezzo. 
L’amministratore potrà aggiungere una nuova tipologia cliccando sul tasto “Aggiungi” che aprirà la form da compilare con i dati della tipologia dell’intervento (nome, descrizione e prezzo) dopo aver controllato se i dati inseriti sono corretti e che non risulti un altro intervento con lo stesso nome il sistema salverà tutto nel DB.
Caso d'uso UC7: aggiungi intervento.
Attore primario: amministrazione. 
Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”.
Scenario principale di successo: 
Dopo aver cliccato su “Prenota  Intervento” il sistema mostrerà la pagina con i diversi interventi che l’officina effettua. Dopo aver cliccato sulla tipologia di interesse il sistema mostrerà una pagina con il form per l’aggiunta dell’intervento.  Nel form sarà visualizzato:
- l’elenco di tutti i clienti e bisognerà selezionare il cliente che vuole svolgere l’intervento.
- l’elenco di tutti i meccanici e bisognerà selezionare il meccanico che  svolgerà l’intervento
-Un campo dove aggiungere una descrizione del veicolo.
Dopo aver selezionato tutti i dati, il sistema registrerà l’intervento.

Caso d'uso UC8: cronologia intervento.
Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”.
Scenario principale di successo: 
Dopo aver cliccato su “Cronologia interventi” il sistema mostrerà la pagina con tutti gli interventi fatti e da fare mostrando per ogni intervento: cognome cliente, data intervento, tipo di intervento, nome meccanico assegnato, data e ora di prenotazione.
Sulla stessa pagine l’amministratore potrà svolgere altre azioni quali : 
-cliccando sul tasto “conferma” potrà selezionare il meccanico specializzato in quel determinato intervento e assegnarlo ad esso selezionando anche la data prevista per l’intervento.
-Sarà presente una scritta che indicherà se l’intervento è ancora in attesa di essere eseguito o è già stato svolto( se confermato nella sezione conferma)

Caso d'uso UC9: contatti.
 Attore primario: utente generico
Scenario principale di successo: L'utente seleziona il campo contatti. Il sistema mostra la form L'utente inserisce il proprio nome, email e richieste. Il sistema inoltra tutto ad un indirizzo email dell’officina.

