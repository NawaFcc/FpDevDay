package devdayfp.ex;

import static devdayfp.ex.Valid.error;
import static devdayfp.ex.Valid.value;

import java.util.function.Function;

import lombok.EqualsAndHashCode;

public abstract class Valid<DATA> {
	
	private Valid() {}
	
	public static <D> Value<D> value(D data) {
		return new Value<D>(data);
	}
	public static <D> Error<D> error(String error) {
		return new Error<D>(error);
	}
	
	public abstract DATA get();
	public abstract DATA orElse(DATA elseValue);
	public abstract <T> Valid<T> map    (Function<DATA, T>        mapper);
	public abstract <T> Valid<T> flatMap(Function<DATA, Valid<T>> mapper);
	public abstract <F> Valid<DATA> validate(
			Function<DATA, F>    access, 
			Function<F, Boolean> lambda, 
			String               error);
	
	@lombok.Value
	@EqualsAndHashCode(callSuper=false)
	public static class Value<D> extends Valid<D> {
		private D data;
		public D get() {
			return data;
		}
		public D orElse(D elseValue) {
			if (data == null)
				return elseValue;
			return data;
		}
		public <T> Valid<T> map(Function<D, T> mapper) {
			if (data == null)
				return Valid.value(null);
			
			return Valid.value(mapper.apply(data));
		}
		public <T> Valid<T> flatMap(Function<D, Valid<T>> mapper) {
			if (data == null)
				return Valid.value(null);
			
			return mapper.apply(data);
		}
		public <F> Valid<D> validate(
				Function<D, F>       access, 
				Function<F, Boolean> lambda, 
				String               error) {
			if (data == null)
				return this;
			if (access.andThen(lambda).apply(data))
				return this;
			
			return error(error);
		}
		public String toString() {
			return "Result{ value: " + data + "}";
		}
	}
	
	@lombok.Value
	@EqualsAndHashCode(callSuper=false)
	public static class Error<D> extends Valid<D> {
		private String error;
		public D get() {
			return null;
		}
		public D orElse(D elseValue) {
			return null;
		}
		public <T> Valid<T> map(Function<D, T> mapper) {
			return Valid.error(error);
		}
		public <T> Valid<T> flatMap(Function<D, Valid<T>> mapper) {
			return Valid.error(error);
		}
		public <F> Valid<D> validate(
				Function<D, F>       access, 
				Function<F, Boolean> lambda, 
				String               error) {
			return this;
		}
		public String toString() {
			return "Result{ error: " + error + " }";
		}
	}
}
