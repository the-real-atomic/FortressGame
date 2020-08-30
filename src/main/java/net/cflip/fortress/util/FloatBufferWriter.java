package net.cflip.fortress.util;

import java.nio.ByteBuffer;

public class FloatBufferWriter implements GenericBufferWriter {
	@Override
	public <T> void writeToBuffer(T data, ByteBuffer buffer) {
		if (data instanceof Float) {
			buffer.putFloat((Float) data);
		} else {
			throw new IllegalArgumentException("Data type must be a float");
		}
	}

	@Override
	public <T> void writeToBuffer(T[] data, ByteBuffer buffer) {
		if (data instanceof Float[]) {
			for (T value : data)
				buffer.putFloat((Float) value);
		} else {
			throw new IllegalArgumentException("Data type must be a float array");
		}
	}
}
