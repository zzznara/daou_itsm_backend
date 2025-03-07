package egovframework.com.utl.exception;


public class ConvertFailedException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public ConvertFailedException() {
		super("객체화에 실패하였습니다.");
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}