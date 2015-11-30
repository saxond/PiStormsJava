package org.daubin.pistorms;

import org.daubin.mindstorms.motors.MotorPort;

enum PSMotorPort implements Constants {

    BAM1(MotorPort.BAM1, Bank.A, PS_Position_M1, PS_Speed_M1, PS_Status_M1, PS_SetPoint_M1, PS_CMDA_M1, A, a),
    BAM2(MotorPort.BAM2, Bank.A, PS_Position_M2, PS_Speed_M2, PS_Status_M2, PS_SetPoint_M2, PS_CMDA_M2, A, a),
    BBM1(MotorPort.BBM1, Bank.B, PS_Position_M1, PS_Speed_M1, PS_Status_M1, PS_SetPoint_M1, PS_CMDA_M1, B, b),
    BBM2(MotorPort.BBM2, Bank.B, PS_Position_M2, PS_Speed_M2, PS_Status_M2, PS_SetPoint_M2, PS_CMDA_M2, B, b);
    
    private final Bank bank;
    private final int positionAddress;
    private final int speedAddress;
    private final MotorPort motorPort;
    private final byte brakeAddress;
    private final byte floatAddress;
    private final int statusAddress;
    private final int setPointAddress;
    private final int cdmaAddress;

    private PSMotorPort(
            MotorPort motorPort, 
            Bank bank, 
            int positionAddress,
            int speedAddress,
            int statusAddress,
            int setPointAddress,
            int cdmaAddress,
            int brakeAddress, 
            int floatAddress) {
        this.bank = bank;
        this.positionAddress = positionAddress;
        this.speedAddress = speedAddress;
        this.statusAddress = statusAddress;
        this.setPointAddress = setPointAddress;
        this.cdmaAddress = cdmaAddress;
        this.motorPort = motorPort;
        this.brakeAddress = (byte) brakeAddress;
        this.floatAddress = (byte) floatAddress;
    }

    public Bank getBank() {
        return bank;
    }

    public MotorPort getMotorPort() {
        return motorPort;
    }

    public int getPositionAddress() {
        return positionAddress;
    }

    public byte getBrakeAddress() {
        return brakeAddress;
    }

    public byte getFloatAddress() {
        return floatAddress;
    }

    public int getSpeedAddress() {
        return speedAddress;
    }

    public int getStatusAddress() {
        return statusAddress;
    }

    public int getSetPointAddress() {
        return setPointAddress;
    }

    public int getCDMAAddress() {
        return cdmaAddress;
    }
    
}
