package org.daubin.pistorms;

public interface Constants {

	static final byte PS_A_ADDRESS = 0x34;
	static final byte PS_B_ADDRESS = 0x36;
    
    // Registers
    static final int PS_Command = 0x41;
    
    static final int PS_SetPoint_M1 =0x42;
    static final int PS_Speed_M1 = 0x46;
    static final int PS_Time_M1 = 0x47;
    static final int PS_CMDB_M1 = 0x48;
    static final int PS_CMDA_M1 = 0x49;
    
    static final int PS_SetPoint_M2 =0x4A;
    static final int PS_Speed_M2 = 0x4E;
    static final int PS_Time_M2 = 0x4F;
    static final int PS_CMDB_M2 = 0x50;
    static final int PS_CMDA_M2 = 0x51;
    
    static final int PS_Position_M1 = 0x52;
    static final int PS_Position_M2 = 0x56;
    
    static final int PS_Status_M1 = 0x5A;
    static final int PS_Status_M2 = 0x5B;
    
    static final int PS_Tasks_M1 = 0x5C;
    static final int PS_Tasks_M2 = 0x5D;
    
    static final int PS_P_Kp = 0x5E;
    static final int PS_P_Ki = 0x60;
    static final int PS_P_Kd = 0x62;
    static final int PS_S_Kp = 0x64;
    static final int PS_S_Ki = 0x66;
    static final int PS_S_Kd = 0x68;
    static final int PS_PassCount = 0x6A;
    static final int PS_PassTolerance = 0x6B;
    static final int PS_ChkSum = 0x6C;
    static final int PS_BattV = 0x6E;
    
    /**
     *   Sensor 1
     */
    static final int PS_S1_Mode = 0x6F;
    // EV3
    static final int PS_S1EV_Ready = 0x70;
    static final int PS_S1EV_SensorID = 0x71;
    static final int PS_S1EV_Mode = 0x81;
    static final int PS_S1EV_Length = 0x82;
    static final int PS_S1EV_Data = 0x83;
    /**
     *  LEGO Analog
     */
    static final int PS_S1AN_Read = 0x70;
    // LEGO Color
    static final int PS_S1C_Color = 0x70;
    static final int PS_S1C_R_cal = 0x71;
    static final int PS_S1C_G_cal = 0x72;
    static final int PS_S1C_B_cal = 0x73;
    static final int PS_S1C_N_cal = 0x74;
    static final int PS_S1C_R_raw = 0x75;
    static final int PS_S1C_G_raw = 0x76;
    static final int PS_S1C_B_raw = 0x77;
    static final int PS_S1C_N_raw = 0x78;
    
    static final int PS_EV_Ready = PS_S1EV_Ready - PS_S1_Mode;
    static final int PS_EV_SensorID = PS_S1EV_SensorID - PS_S1_Mode;
    static final int PS_EV_Mode = PS_S1EV_Mode - PS_S1_Mode;
    static final int PS_EV_Length = PS_S1EV_Length - PS_S1_Mode;
    static final int PS_EV_Data = PS_S1EV_Data - PS_S1_Mode;
    
    // Sensor 2
    static final int PS_S2_Mode = 0xA3;
    // EV3
    static final int PS_S2EV_Ready = 0xA4;
    static final int PS_S2EV_SensorID = 0xA5;
    static final int PS_S2EV_Mode = 0xB5;
    static final int PS_S2EV_Length = 0xB6;
    static final int PS_S2EV_Data = 0xB7;
    // LEGO Analog
    static final int PS_S2AN_Read = 0xA4;
    // LEGO Color
    static final int PS_S2C_Color = 0xA4;
    static final int PS_S2C_R_cal = 0xA5;
    static final int PS_S2C_G_cal = 0xA6;
    static final int PS_S2C_B_cal = 0xA7;
    static final int PS_S2C_N_cal = 0xA8;
    static final int PS_S2C_R_raw = 0xA9;
    static final int PS_S2C_G_raw = 0xAA;
    static final int PS_S2C_B_raw = 0xAB;
    static final int PS_S2C_N_raw = 0xAC;
    // LED 
    static final int PS_R = 0xD7;
    static final int PS_G = 0xD8;
    static final int PS_B = 0xD9;
    // Buttons
    static final int PS_KeyPress = 0xDA;
    static final int PS_Key1Count = 0xDB;
    static final int PS_Key2Count = 0xDC;
    
    static final int PS_CONTROL_SPEED  = 0x01;
    static final int PS_CONTROL_RAMP   = 0x02;
    static final int PS_CONTROL_RELATIVE =  0x04;
    static final int PS_CONTROL_TACHO  = 0x08;
    static final int PS_CONTROL_BRK  =   0x10;
    static final int PS_CONTROL_ON    =  0x20;
    static final int PS_CONTROL_TIME  =   0x40;
    static final int PS_CONTROL_GO   =     0x80;
    // Supported I2C commands
    static final byte R = 0x52;
    static final byte S = 0x53;
    static final byte a = 0x61;
    static final byte b = 0x62;
    static final byte c = 0x63;
    static final byte A = 0x41;
    static final byte B = 0x42;
    static final byte C = 0x43;
}
