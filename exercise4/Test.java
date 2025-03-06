package Aufgabe4;

public class Test {
}



/*

Begründung für nicht bestehende Untertypbeziehungen: 

Building <-> Circulation:
->: Ein Gebäude dient nicht ausschließlich der Fortbewegung.
<-: Circulation sind Bereiche, die der Erschließung dienen, nicht physische Gebäude.

Building <-> Complex:
->:  Ein Building ist ein einzelnes Gebäude, kein Zusammenschluss mehrerer Gebäude.
<-: Ein Complex besteht aus mehreren physisch verbundenen Gebäuden, nicht aus einem einzigen.

Building <-> Ensemble:
->: in Ensemble beschreibt eine Zusammengehörigkeit von Gebäuden, während Building immer ein physisch definiertes Einzelgebäude ist.
<-: Ein Ensemble ist keine physische Einheit, sondern eine lose Gruppierung.

Building <-> Entity:
 Untertypbeziehung besteht.


Building <-> Circulation:
->: Ein Gebäude ist keine Darstellung von Fluchtwegen.
<-: Escape beschreibt Wege, keine physischen Gebäude.

Building <-> Exterior:
->: Ein Building ist ein physisches Objekt, kein Außenbereich.
<-: Exterior beschreibt Außenbereiche und nicht die physische Hülle eines Gebäudes.

Building <-> Interior:
->: Ein Building ist ein physisches Gebäude und nicht ein Raum innerhalb eines Gebäudes.
<-: Interior beschreibt Räume, nicht die physische Gesamtheit eines Gebäudes.

Building <-> Lift:
->: Lifts sind spezialisierte Innenräume zur vertikalen Fortbewegung, keine Gebäude.
<-: Ein Lift ist ein Innenraum, kein physisches Gebäude.

Building <-> :PublicRoad
->: Eine öffentliche Straße ist kein physisches Gebäude.
<-: PublicRoad beschreibt Bereiche für Fortbewegung, keine Gebäude.

Building <-> PureCirculation:
->: Ein Gebäude umfasst mehr als nur reine Erschließungsflächen.
<-: PureCirculation beschreibt reine Erschließungsflächen, nicht Gebäude.

Building <-> Room:
->: Ein Room ist ein Innenraum, während Building die physische Hülle darstellt.
<-: Rooms sind Räume und keine Gebäude als Ganzes.

Building <-> ServantSpace:
->: ServantSpace beschreibt funktionale Innenräume, keine Gebäude.
<-: ServantSpace ist ein spezialisierter Innenraum, kein Gebäude.

Building <-> ServedSpace:
->: ServedSpace beschreibt Innenräume für längeren Aufenthalt, nicht Gebäude.
<-: ServedSpace ist ein spezialisierter Innenraum, kein Gebäude.

Building <-> Space:
->: Space beschreibt Aufenthaltsbereiche, nicht physische Gebäude.
<-: Space ist ein allgemeiner Begriff für Bereiche, nicht für physische Gebäude.




Circulation  <-> Complex:
->: Circulation beschreibt Erschließungsflächen, keine physisch verbundenen Gebäude.
<-: Complex ist eine Sammlung physisch verbundener Gebäude, keine Erschließungsflächen.

Circulation  <-> Ensemble:
->: Circulation beschreibt Flächen zur Fortbewegung, keine lose Gruppierung von Gebäuden.
<-: Ensemble beschreibt Gruppierungen, keine Erschließungsflächen.

Circulation  <-> Entity:
->: Circulation beschreibt Flächen, während Entity gebaute Einheiten wie Gebäude beschreibt.
<-: Entity beschreibt physische Einheiten, keine Fortbewegungsflächen.

Circulation  <-> Escape:
->: Escape beschreibt Fluchtwege, während Circulation allgemeine Erschließungsflächen beschreibt.
<-: Escape beschreibt spezifische Fluchtwege, nicht allgemeine Erschließungsflächen.

Circulation  <-> PublicRoad:
Untertypbeziehung besteht.

Circulation  <-> PureCirculation:
Untertypbeziehung besteht.

Circulation  <-> ServedSpace:
Untertypbeziehung besteht.

Circulation  <-> Space:
Untertypbeziehung besteht.





Complex   <-> Ensemble:
->: Complex ist ein Zusammenschluss physisch verbundener Gebäude, während Ensemble lose Gruppierungen beschreibt, unabhängig von physischer Verbindung.
<-: Ein Ensemble ist keine physische Einheit, sondern eine Gruppierung von Gebäude(komplexen).

Complex   <-> Entity:
Untertypbeziehung besteht.

Complex   <-> Escape:
->: Complex beschreibt physische Verbindungen zwischen Gebäuden, während Escape spezifische Fluchtwege repräsentiert.
<-: Escape beschreibt Wege und ist keine physische Einheit wie ein Gebäudekomplex.

Complex   <-> Exterior:
->: Complex ist ein Zusammenschluss von Gebäuden, während Exterior Außenbereiche beschreibt.
<-:  Exterior sind Außenbereiche und nicht physische Gebäudeeinheiten.

Complex   <-> Interior:
->: Complex ist eine physische Einheit aus mehreren Gebäuden, während Interior geschlossene Innenräume beschreibt.
<-: Interior beschreibt Innenräume und keine Gebäudezusammenschlüsse.

Complex   <-> Lift:
->: Complex beschreibt physische Gebäudezusammenschlüsse, während Lift ein spezifischer Innenraum ist.
<-: Lift ist ein Innenraum, nicht ein Gebäudekomplex.

Complex   <-> PublicRoad:
->: Complex beschreibt physische Gebäudeverbindungen, während PublicRoad öffentliche Straßen für Fortbewegung repräsentiert.
<-: PublicRoad ist keine physische Einheit, sondern eine reine Fortbewegungsfläche.

Complex   <-> PureCirculation:
->: Complex beschreibt physische Verbindungen von Gebäuden, während PureCirculation ausschließlich der Fortbewegung dient.
<-: PureCirculation sind reine Erschließungsflächen, nicht Gebäudezusammenschlüsse.

Complex   <-> Room:
->: Complex beschreibt Zusammenschlüsse aus Gebäuden, während Room abgeschlossene Innenräume beschreibt.
<-: Room ist ein Innenraum, kein Gebäudeverbund.

Complex   <-> ServantSpace:
->: Complex beschreibt Gebäudezusammenschlüsse, während ServantSpace spezifische dienende Innenräume beschreibt.
<-: ServantSpace sind Innenräume, keine Gebäudekomplexe.

Complex   <-> ServedSpace:
->: Complex ist eine Verbindung aus Gebäuden, während ServedSpace spezifische Innenräume beschreibt.
<-: ServedSpace beschreibt bediente Innenräume, keine physische Verbindung aus Gebäuden.

Complex   <-> Space:
->: Complex beschreibt physische Verbindungen von Gebäuden, während Space allgemeine Aufenthaltsbereiche beschreibt.
<-: Space ist kein Gebäudeverbund, sondern ein abstrakter Bereich.




Ensemble <-> Entity:
Untertypbeziehung besteht.

Ensemble <-> Escape:
->: Ensemble beschreibt eine lose Gruppierung von Gebäuden oder Komplexen, während Escape spezifische Fluchtwege beschreibt.
<-: Escape ist ein Fluchtweg und keine physische Einheit wie ein Ensemble.

Ensemble <-> Exterior:
->: Ensemble ist eine Gruppierung von Gebäuden, keine Außenbereiche.
<-: Exterior beschreibt Außenbereiche, die keine physische Gruppierung von Gebäuden darstellen.

Ensemble <-> Interior:
->: Ensemble beschreibt eine Gruppierung, keine geschlossenen Innenräume.
<-: Interior beschreibt geschlossene Räume und keine Gruppierungen.

Ensemble <-> Lift:
->: Ensemble ist eine lose Gruppierung, kein spezifischer Innenraum.
<-: Lift ist ein Innenraum und keine lose Gruppierung.

Ensemble <-> PublicRoad:
->: Ensemble beschreibt Gebäudegruppen, keine Straßen.
<-: PublicRoad beschreibt öffentliche Straßen und keine lose Gebäudegruppen.

Ensemble <-> PureCirculation:
->: Ensemble beschreibt Gruppierungen, keine reinen Erschließungsflächen.
<-: PureCirculation beschreibt Fortbewegungsflächen, keine Gruppierungen.

Ensemble <-> Room:
->: Ensemble ist eine Gruppierung von Gebäuden, keine abgeschlossenen Innenräume.
<-: Room ist ein Innenraum und keine Gruppierung.

Ensemble <-> ServantSpace:
->: Ensemble beschreibt Gruppierungen, keine spezifischen dienenden Räume.
<-: ServantSpace beschreibt spezifische Innenräume, keine Gruppierungen.

Ensemble <-> ServedSpace:
->: Ensemble ist eine Gruppierung, keine spezifischen bedienten Innenräume.
<-: ServedSpace beschreibt Innenräume, keine Gruppierungen.

Ensemble <-> Space:
->: Ensemble beschreibt Gebäudegruppierungen, keine allgemeinen Aufenthaltsbereiche.
<-: Space beschreibt Aufenthaltsbereiche, keine Gruppierungen.





Entity  <-> Escape:
->: Entity beschreibt gebaute Einheiten wie Gebäude, Komplexe und Ensembles, während Escape Fluchtwege beschreibt.
<-: Escape beschreibt spezifische Fluchtwege und ist keine gebaute Einheit.

Entity  <-> Exterior:
->: Entity beschreibt physische Einheiten, während Exterior Außenbereiche beschreibt, die Teil eines Gebäudes oder Komplexes sein können.
<-: Exterior beschreibt Außenbereiche und keine umfassenden gebauten Einheiten.

Entity  <-> Interior:
->: Entity beschreibt übergeordnete Einheiten wie Gebäude oder Komplexe, während Interior spezifisch geschlossene Innenräume beschreibt.
<-:Interior beschreibt Räume, keine vollständigen Einheiten.

Entity  <-> Lift:
->: Entity beschreibt umfassende gebaute Einheiten, während Lift ein spezialisierter Innenraum ist.
<-: Lift ist ein spezifischer Innenraum, keine umfassende gebaute Einheit.

Entity  <-> PublicRoad:
->: Entity beschreibt physische Einheiten wie Gebäude und Komplexe, während PublicRoad öffentliche Straßen repräsentiert.
<-: PublicRoad beschreibt Straßen und keine physischen Einheiten wie Gebäude oder Komplexe.

Entity  <-> PureCirculation:
->: Entity beschreibt gebaute Einheiten, während PureCirculation ausschließlich reine Erschließungsflächen repräsentiert.
<-: PureCirculation beschreibt Erschließungsflächen und ist keine umfassende gebaute Einheit.

Entity  <-> Room:
->: Entity beschreibt umfassende Einheiten wie Gebäude oder Komplexe, während Room spezifisch einen geschlossenen Innenraum beschreibt.
<-: Room ist ein Innenraum, keine umfassende gebaute Einheit.



Entity  <-> ServantSpace:
->: Entity beschreibt umfassende Einheiten, während ServantSpace spezialisierte dienende Räume beschreibt.
<-: ServantSpace beschreibt spezifische Innenräume, keine umfassende gebaute Einheit.

Entity  <-> ServedSpace:
->: Entity beschreibt umfassende Einheiten wie Gebäude oder Komplexe, während ServedSpace bediente Räume beschreibt.
<-: ServedSpace beschreibt Innenräume, keine gebauten Einheiten.

Entity  <-> Space:
->: Entity beschreibt umfassende physische Einheiten, während Space allgemeine Aufenthaltsbereiche beschreibt.
<-: Space ist kein umfassendes physisches Konzept, sondern beschreibt Aufenthaltsbereiche.





Escape <-> Exterior:
->: Escape beschreibt spezifische Fluchtwege, während Exterior Außenbereiche darstellt.
<-: Exterior beschreibt Außenbereiche und ist kein Fluchtweg.

Escape <-> Interior:
->: Escape beschreibt Fluchtwege, während Interior spezifisch geschlossene Innenräume beschreibt.
<-: Interior beschreibt Innenräume, keine Wege.

Escape <-> Lift:
->: Escape beschreibt Fluchtwege, während Lift ein spezifischer Innenraum zur vertikalen Fortbewegung ist.
<-: Lift ist ein Innenraum, keine Darstellung eines Fluchtwegs.

Escape <-> PublicRoad:
->: Escape beschreibt Fluchtwege, während PublicRoad allgemeine Fortbewegungsflächen beschreibt.
<-: PublicRoad ist keine Darstellung von Fluchtwegen, sondern eine Straße.

Escape <-> PureCirculation:
->: Escape beschreibt spezifische Fluchtwege, während PureCirculation reine Erschließungsflächen beschreibt.
<-: PureCirculation beschreibt keine spezifischen Fluchtwege, sondern allgemeine Erschließungsflächen.

Escape <-> Room:
->: Escape beschreibt Fluchtwege, während Room abgeschlossene Innenräume beschreibt.
<-: Room beschreibt Räume, keine Fluchtwege.

Escape <-> ServantSpace:
->: Escape beschreibt Fluchtwege, während ServantSpace spezialisierte dienende Räume sind.
<-: ServantSpace beschreibt Innenräume, keine Wege.

Escape <-> ServedSpace:
->: Escape beschreibt Fluchtwege, während ServedSpace spezifische bediente Räume beschreibt.
<-: ServedSpace beschreibt Räume, keine Fluchtwege.

Escape <-> Space:
->: Escape beschreibt Fluchtwege, während Space allgemeine Aufenthaltsbereiche beschreibt.
<-: Space ist ein Bereich, keine Darstellung eines Wegs.




Exterior  <-> Interior:
->: Exterior beschreibt Außenbereiche, während Interior geschlossene Innenräume beschreibt.
<-: Interior beschreibt geschlossene Räume, während Exterior offene Außenbereiche beschreibt.

Exterior  <-> Lift:
->: Exterior beschreibt Außenbereiche, während Lift ein Innenraum ist.
<-: Lift ist ein Innenraum zur vertikalen Erschließung, kein Außenbereich.

Exterior  <-> PublicRoad:
->:  Exterior beschreibt Außenbereiche, die Teil von Gebäuden oder Komplexen sein können, während PublicRoad öffentliche Straßen beschreibt.
<-: PublicRoad beschreibt Straßen, die außerhalb von Gebäuden liegen, aber keine Teilmenge von Außenbereichen sind.

Exterior  <-> PureCirculation:
->: Exterior beschreibt Außenbereiche, während PureCirculation reine Erschließungsflächen beschreibt.
<-: PureCirculation beschreibt reine Erschließungsflächen, keine Außenbereiche.

Exterior  <-> Room:
->: Exterior beschreibt offene Außenbereiche, während Room geschlossene Innenräume beschreibt.
<-: Room ist ein Innenraum, kein Außenbereich.

Exterior  <-> ServantSpace:
->: Exterior beschreibt Außenbereiche, während ServantSpace spezialisierte Innenräume beschreibt.
<-: ServantSpace beschreibt Innenräume, keine Außenbereiche.

Exterior  <-> ServedSpace:
->: Exterior beschreibt Außenbereiche, während ServedSpace spezialisierte Innenräume für längeren Aufenthalt beschreibt.
<-: ServedSpace beschreibt Innenräume, keine Außenbereiche.

Exterior  <-> Space:
Untertypbeziehung besteht.




Interior <-> Lift:
->: Interior beschreibt geschlossene Innenräume im Allgemeinen, während Lift ein spezifischer Innenraum zur vertikalen Fortbewegung ist.
<-: Lift ist eine spezifische Form von Innenraum, aber mit speziellen Einschränkungen (z. B. keine Fluchtwege).

Interior <-> PublicRoad:
->: Interior beschreibt Innenräume, während PublicRoad öffentliche Straßen beschreibt.
<-: PublicRoad beschreibt Straßen, keine Innenräume.

Interior <-> PureCirculation:
->: Interior beschreibt geschlossene Räume, während PureCirculation reine Erschließungsflächen beschreibt.
<-: PureCirculation beschreibt keine geschlossenen Räume, sondern Erschließungsflächen.

Interior <-> Room:
Untertypbeziehung besteht.

Interior <-> ServantSpace:
Untertypbeziehung besteht.

Interior <-> ServedSpace:
Untertypbeziehung besteht.

Interior <-> Space:
Untertypbeziehung besteht.



Lift  <-> PublicRoad:
->: Lift ist ein Innenraum zur vertikalen Erschließung, während PublicRoad öffentliche Straßen beschreibt.
<-: PublicRoad beschreibt Straßen, keine Innenräume.

Lift  <-> PureCirculation:
Untertypbeziehung besteht.

Lift  <-> Room:
Untertypbeziehung besteht.

Lift  <-> ServantSpace:
->: Lift ist ein Raum zur vertikalen Erschließung, während ServantSpace allgemeine dienende Räume beschreibt.
<-: ServantSpace beschreibt spezifische dienende Räume, keine vertikalen Erschließungsräume.

Lift  <-> ServedSpace:
->: Lift ist ein Raum zur vertikalen Erschließung, während ServedSpace Räume für längeren Aufenthalt beschreibt.
<-: ServedSpace beschreibt bediente Innenräume, keine vertikalen Erschließungsräume.

Lift  <-> Space:
Untertypbeziehung besteht.




PublicRoad   <-> PureCirculation:
Untertypbeziehung besteht.

PublicRoad   <-> Room:
->: PublicRoad beschreibt öffentliche Straßen, während Room geschlossene Innenräume beschreibt.
<-:  Room ist ein Innenraum, keine Straße.

PublicRoad   <-> ServantSpace:
->: PublicRoad beschreibt Straßen, während ServantSpace spezifische Innenräume beschreibt.
<-: ServantSpace beschreibt dienende Räume, keine Straßen.

PublicRoad   <-> ServedSpace:
->: PublicRoad beschreibt Straßen, während ServedSpace spezifische bediente Räume beschreibt
<-: ServedSpace beschreibt bediente Innenräume, keine Straßen.

PublicRoad   <-> Space:
Untertypbeziehung besteht.




PureCirculation <-> Room:
->: PureCirculation beschreibt reine Erschließungsflächen, während Room geschlossene Innenräume beschreibt.
<-: Room ist ein Innenraum mit Schutzfunktion, während PureCirculation keine geschlossenen Räume umfasst.



PureCirculation <-> ServantSpace:
->: PureCirculation beschreibt reine Erschließungsflächen, während ServantSpace dienende Innenräume beschreibt.
<-: ServantSpace beschreibt spezifische Innenräume, die keine reine Erschließungsfunktion haben.



PureCirculation <-> ServedSpace:
->: PureCirculation beschreibt reine Erschließungsflächen, während ServedSpace Räume für längeren Aufenthalt beschreibt.
<-: ServedSpace beschreibt bediente Innenräume, keine reine Erschließungsfunktion.

PureCirculation <-> Space:
->:
<-:

PureCirculation <-> :
Untertypbeziehung besteht.



Room  <-> ServantSpace:
Untertypbeziehung besteht.

Room  <-> ServedSpace:
Untertypbeziehung besteht.

Room  <-> Space:
Untertypbeziehung besteht.



ServantSpace   <-> ServedSpace:
->: ServantSpace beschreibt dienende Räume, die primär funktionalen Aufgaben dienen, während ServedSpace Räume für längeren Aufenthalt beschreibt.
<-: ServedSpace beschreibt bediente Räume, die qualitativ andere Anforderungen erfüllen als dienende Räume.

ServantSpace   <-> Space:
Untertypbeziehung besteht.


ServedSpace    <-> Space:
Untertypbeziehung besteht.





 */
