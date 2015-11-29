# PiStormsJava

PiStorms is a shield (or a HAT) designed for the Raspberry Pi to control NXT and EV3 Motors and operate NXT and EV3 Sensors. PiStorms works with Raspberry Pi model A+, model B+, and 2 model B.

This library is a Java API that supports the PiStorms shield.

## Usage

Install the PiStorms software from [here](http://www.mindsensors.com/content/72-pistorms-libraries-and-sample-programs).

[Main.java](src/main/java/org/daubin/pistorms/Main.java) is a simple example
that reads a few sensors and prints their values.  Before running this program, exit any python applications which are communicating with the PiStorms shield. 