package uld.sdk.tools;

/**
 * 引用类型类
 * @author Administrator
 *
 * @param <T>
 */
public final class RefObject<T> {
	public T argvalue;

	public RefObject(T refarg) {
		argvalue = refarg;
	}
}