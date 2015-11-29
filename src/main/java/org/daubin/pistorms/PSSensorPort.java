package org.daubin.pistorms;

public enum PSSensorPort {
	BAS1(Bank.A, PiStormsCom.PS_S1_Mode),
    BAS2(Bank.A, PiStormsCom.PS_S2_Mode),
    BBS1(Bank.B, PiStormsCom.PS_S1_Mode),
    BBS2(Bank.B, PiStormsCom.PS_S2_Mode);

	private final Bank bank;
	private final int mode;

	private PSSensorPort(Bank bank, int mode) {
		this.bank = bank;
		this.mode = mode;
	}

	Bank getBank() {
		return bank;
	}
	
	int getMode() {
		return mode;
	}
}
