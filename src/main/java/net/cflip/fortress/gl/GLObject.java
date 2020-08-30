package net.cflip.fortress.gl;

public abstract class GLObject {
	private static final int INVALID_ID = -1;
	protected int id;

	public GLObject(int id) {
		this.id = id;
	}

	protected void checkValidity() {
		if (id <= 0) throw new IllegalStateException("OpenGL object has invalid ID");
	}

	protected void invalidate() {
		id = INVALID_ID;
	}

	public abstract void bind();
	public abstract void unbind();
	public abstract void delete();
}
