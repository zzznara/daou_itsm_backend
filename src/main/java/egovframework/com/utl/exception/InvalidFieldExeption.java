package egovframework.com.utl.exception;

public class InvalidFieldExeption extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public InvalidFieldExeption() {
		super("오브젝트에 존재하지 않는 필드입니다.");
	}

	public void printStackTrace(String key) {
		super.printStackTrace();
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}