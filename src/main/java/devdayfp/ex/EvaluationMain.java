package devdayfp.ex;

import static devdayfp.ex.EvaluationMain.Expr.add;
import static devdayfp.ex.EvaluationMain.Expr.mul;
import static devdayfp.ex.EvaluationMain.Expr.term;
import static org.junit.Assert.assertEquals;

import lombok.EqualsAndHashCode;
import lombok.Value;

public class EvaluationMain {
	
	// Sum Type
	public static abstract class Expr {
		private Expr() {}
		
		public static Term term(int i) {
			return new Term(i);
		}
		public static Expr add(Expr left, Expr right) {
			return new Add(left, right);
		}
		public static Expr mul(Expr left, Expr right) {
			return new Multiply(left, right);
		}
		

		@Value @EqualsAndHashCode
		(callSuper=false)
		public static class Term extends Expr {
			private final int i;
		}
		// Product type
		@Value @EqualsAndHashCode(callSuper=false)
		public static class Add extends Expr {
			private final Expr left;
			private final Expr right;
		}
		@Value @EqualsAndHashCode(callSuper=false)
		public static class Multiply extends Expr {
			private final Expr left;
			private final Expr right;
		}
		

//		public	abstract Integer eval();
	}
	
//	private static Integer eval(Expr expr) {
//		return null;
//	}
//	private static String print(Expr expr) {
//		return null;
//	}
	
	public static void main(String[] args) {
		Expr expr = add(term(5), mul(term(5), term(6)));
//		assertEquals(35, eval(expr).intValue());
//		assertEquals(35, expr.eval().intValue());
	}
	
}
