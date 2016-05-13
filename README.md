# Voting System

Voting System

[![Build Status](https://travis-ci.org/Arquisoft/Voting_4a.svg?branch=master)](https://travis-ci.org/Arquisoft/Voting_4a)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/f0ab4c8359fd4548ab373ae93ab8706a)](https://www.codacy.com/app/jelabra/Voting_4a)
[![codecov.io](https://codecov.io/github/Arquisoft/Voting_4a/coverage.svg?branch=master)](https://codecov.io/github/Arquisoft/Voting_4a?branch=master)

[![Dependency Status](https://www.versioneye.com/user/projects/57288894a0ca35004baf7c7d/badge.svg?style=flat)](https://www.versioneye.com/user/projects/57288894a0ca35004baf7c7d)
[![GitHub issues](https://img.shields.io/github/issues/Arquisoft/Voting_4a.svg)](https://github.com/Arquisoft/Voting_4a/issues)
[![GitHub forks](https://img.shields.io/github/forks/Arquisoft/Voting_4a.svg)](https://github.com/Arquisoft/Voting_4a/network)

[Project Site](http://arquisoft.github.io/Voting_4a/)

Estamos en Heroku

[![ViewOnHeroku](https://img.shields.io/badge/View%20on-Heroku-ff69b4.svg)](http://votecounting4a.herokuapp.com/)  VoteCounting
[![ViewOnHeroku](https://img.shields.io/badge/View%20on-Heroku-ff69b4.svg)](http://votingsystem4a.herokuapp.com/)  VotingSystem
[![ViewOnHeroku](https://img.shields.io/badge/View%20on-Heroku-ff69b4.svg)](http://voters4a.herokuapp.com/)  Voters

##Chat

[![Gitter](https://badges.gitter.im/Arquisoft/Voting_4a.svg)](https://gitter.im/Arquisoft/Voting_4a?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

##VoteCounting

[VoteCounting Project Site](http://arquisoft.github.io/VoteCounting_4a/)


VoteCounting es un proyecto realizado como práctica de entrega para la asignatura Arquitectura del Software de la Universidad de Oviedo.

El sistema es capaz de leer de identificar los datos de una votación de una base de datos y en función de ello instanciar el sistema de una forma u otra.

La aplicación tiene dos formas de despliegue, prod y test. En prod hará uso de una base de datos MySQL en el puerto 3306 llamada "test". En el primer despliegue creará las tablas en función del modelo. En "test" hará uso de una base de datos interna H2. La aplicación hace uso de JPA.

Mediante el uso de la tecnología Spring y JSF despliega en el puerto 8080. Como librería gráfica usa Primefaces. Desde ahí se puede acceder a las estadísticas que muestra la aplicación para los votos recogidos. Dichas estadísticas se recargan de forma periódica y automática para el usuario actualizando los datos al tiempo real.


##Voters

Sistema de acceso al usuario por medio de un servicio REST aportando sus datos de acceso (correo electrónico y contraseña). Acceso tanto por método POST como GET a cambiar contraseña y email


# Authors

* Lucas García
* Victor Castaño
* Adrián Jiménez
* Ricardo Suárez Rodríguez
* Iván Modroño Álvarez
