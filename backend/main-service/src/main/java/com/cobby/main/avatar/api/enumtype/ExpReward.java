package com.cobby.main.avatar.api.enumtype;

public enum ExpReward {
	COMMIT(30), ATTENDANCE(5);

	private final int value;

	ExpReward(int value) { this.value = value; }

	public int getValue() { return value; }
}
