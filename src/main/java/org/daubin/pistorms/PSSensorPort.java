package org.daubin.pistorms;

import org.daubin.mindstorms.sensors.SensorPort;

enum PSSensorPort implements Constants {
    BAS1(SensorPort.BAS1, Bank.A, PS_S1_Mode), 
    BAS2(SensorPort.BAS2, Bank.A, PS_S2_Mode), 
    BBS1(SensorPort.BBS1, Bank.B, PS_S1_Mode), 
    BBS2(SensorPort.BBS2, Bank.B, PS_S2_Mode);

    private final Bank bank;
    private final int mode;
    private final SensorPort sensorPort;

    private PSSensorPort(SensorPort sensorPort, Bank bank, int mode) {
        this.bank = bank;
        this.mode = mode;
        this.sensorPort = sensorPort;
    }

    Bank getBank() {
        return bank;
    }

    int getMode() {
        return mode;
    }

    public SensorPort getSensorPort() {
        return sensorPort;
    }
}
