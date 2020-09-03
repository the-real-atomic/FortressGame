package net.cflip.fortress.util;

import java.nio.ByteBuffer;

public interface GenericBufferWriter {
	<T> void writeToBuffer(T[] data, ByteBuffer buffer, int index);
}
