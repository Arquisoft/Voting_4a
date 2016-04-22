# Vote Counting & Publishing system


[![Build Status](https://travis-ci.org/Arquisoft/VoteCounting_4a.svg?branch=master)](https://travis-ci.org/Arquisoft/VoteCounting_4a)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/90db566fc1724bbaa9e774c0b6dda812)](https://www.codacy.com/app/jelabra/VoteCounting_4a)
[![codecov.io](https://codecov.io/github/Arquisoft/VoteCounting_4a/coverage.svg?branch=master)](https://codecov.io/github/Arquisoft/VoteCounting_4a?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5716a529fcd19a0039f1789d/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5716a529fcd19a0039f1789d)
[![ViewOnHeroku](https://img.shields.io/badge/View%20on-Heroku-ff69b4.svg)](http://vote-counting-4a.herokuapp.com)

[![Code Climate](https://codeclimate.com/github/Arquisoft/VoteCounting_4a/badges/gpa.svg)](https://codeclimate.com/github/Arquisoft/VoteCounting_4a)
[![Issue Count](https://codeclimate.com/github/Arquisoft/VoteCounting_4a/badges/issue_count.svg)](https://codeclimate.com/github/Arquisoft/VoteCounting_4a)

[![GitHub version](https://badge.fury.io/gh/Arquisoft%2FVoteCounting_4a.svg)](https://badge.fury.io/gh/Arquisoft%2FVoteCounting_4a)

##Chat

[![Chat on Gitter](https://badges.gitter.im/Arquisoft/VoteCounting_4a.svg)](https://gitter.im/Arquisoft/VoteCounting_4a)

##Web

[Project Site](http://arquisoft.github.io/VoteCounting_4a/)


VoteCounting es un proyecto realizado como práctica de entrega para la asignatura Arquitectura del Software de la Universidad de Oviedo.

El sistema es capaz de leer de identificar los datos de una votación de una base de datos y en función de ello instanciar el sistema de una forma u otra.

La aplicación tiene dos formas de despliegue, prod y test. En prod hará uso de una base de datos MySQL en el puerto 3306 llamada "test". En el primer despliegue creará las tablas en función del modelo. En "test" hará uso de una base de datos interna H2. La aplicación hace uso de JPA.

Mediante el uso de la tecnología Spring y JSF despliega en el puerto 8080. Como librería gráfica usa Primefaces. Desde ahí se puede acceder a las estadísticas que muestra la aplicación para los votos recogidos. Dichas estadísticas se recargan de forma periódica y automática para el usuario actualizando los datos al tiempo real.


# Authors

* Jose Labra
* Lucas García
* Victor Castaño
* Adrián Jiménez


 
 

