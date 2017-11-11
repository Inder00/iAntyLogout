# AntyLogoutAPI

* Jest to proste API, dzięki któremu możesz dopisać do pluginu co chcesz!

#### Dokumentacja

* new AntyLogoutAPI(plugin); - Tworzenie APi dla pluginu

* Gracz jest w PVP
* .inPVP(player) - return boolean

* Tworzenie PVP
* .createPVP(player, time) - return boolean

* Tworzenie actionbara
* .createActionbar(text) - return actionbar

* Wysylanie actionbara do gracza
* .sendActionBar(actionbar, player) - void

* Wysylanie actionbara do wszystkich graczy
* .sendActionBarAll(actionbar, null) - void

* Pobieranie pozostalego graczu PVP
* .getLeftTime(pvp) - return double

* Pobieranie PVP
* .getPVP(uuid) - return pvp

* .toString() - return string

* Przykladowe uzycie: https://hastebin.com/uzocumikul.scala
