package org.daubin.pistorms;

public enum SensorType {
    NONE(0),
    SWITCH(1),
    ANALOG(2),
    LIGHT_ACTIVE(3),
    LIGHT_INACTIVE(4),
    SOUND_DB(5),
    SOUND_DBA(6),
    LOWSPEED_9V(7),
    LOWSPEED(8),
    CUSTOM(9),
    COLORFULL(13),
    COLORRED(14),
    COLORGREEN(15),
    COLORBLUE(16),
    COLORNONE(17),
    EV3_SWITCH(18),
    EV3(19);
	
	private final byte typeId;

	private SensorType(int typeId) {
		this.typeId = (byte)typeId;
	}

	public byte getTypeId() {
		return typeId;
	}

}
