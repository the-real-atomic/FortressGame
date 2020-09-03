package net.cflip.fortress.util;

import java.nio.ByteBuffer;

public class FloatBufferWriter implements GenericBufferWriter {
	@Override
	public <T> void writeToBuffer(T[] data, ByteBuffer buffer, int index) {
		if (data instanceof Float[]) {
			for (T value : data) buffer.putFloat(index, (Float) value);
		} else {
			throw new IllegalArgumentException("Data type must be a float array");
		}
	}
}
