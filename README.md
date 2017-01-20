MarriagePlus
============

The continuation of the very first Marriage plugin (also had the most horrifying [source code](https://gist.github.com/dreanor/9d0d9eb8517c465a54ee) you could imagine) in Minecraft that was also created by ASC_Dreanor. 

## Whats the purpose of the Plugin
MarriagePlus allows you to Marry another Player in Minecraft either with a priester or just with your partner! You can gain benefits from the MarriagePlus, that only work for the Married Couple ex. Teleportation, NoPvP, health regeneration if close to partner. MarriagePlus integrates all Marriage's features but also has new exclusive features. It will also feature a complete new database structure


## What you can't do
* Marry multiple persons
* Set a gender (Minecraft is genderless, the player is a human being)

## Features
* Database types are MySQL and Standard files
* Permissions
* Economy
* Vault support
* Teleport to Married Player
* Show your love to your partner and play heart animations
* Regenerate health near your partner
* Get more xp if you are close to your partner while killing mobs!
* You and your partner automatically share food together if you are close to eachother (2 block radius)
* Set your home and teleport to it
* Become a Priester and marry people (People need to be in a 25 block radius)
* Enable/Disable PvP with the partner
* Share your money with the partner (Will be divided by 2 if you divorce)

## Config
```yaml
Permissions: false
Economy:
  Enable: false
  Divorce: 100.0
  Marry: 100.0
  Tp: 25.0
  HomeTp: 25.0
  SetHome: 100.0
HealthRegain:
  Enable: true
  Amount: 2
BonusXp:
  Enable: true
  Multiplier: 2
Announcement: true
```
**HealthRegain**

The amount of 1 is equal to half of a heart.

## Commands
* /marry - Shows command usage
* /marry list - Lists all Married Players
* /marry divorce <Playername> - Only usable as a Priest
* /marry <Playername> <Playername> - Only usable as a Priest
* /marry priest <playername> - Only operator sets a Priest
* /marry tp - Teleport to Married Player
* /marry love - Heart animation around you and the Partner
* /marry pvpon
* /marry pvpoff
* /marry home
* /marry sethome
