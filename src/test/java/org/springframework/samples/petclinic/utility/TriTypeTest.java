package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.ClauseCoverage;
import com.github.mryf323.tractatus.Valuation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TriTypeTest {

	private static final Logger log = LoggerFactory.getLogger(TriTypeTest.class);

	@Test
	public void sampleTest() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.EQUILATERAL, triClass);
	}


	/**
	 * todo
	 * explain your answer here
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @return
	 */
	private static boolean questionTwo(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean predicate = false;
//		predicate = a predicate with any number of clauses
		return predicate;
	}

//	CC Tests (Clause Coverage)


	@ClauseCoverage(
		predicate = "a || b || c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		}
	)
	@Test
	public void recognizeTriangleByCodeTestCC1() {
		TriType triType = new TriType();
		TriType.TryClass triClass;
		triClass = triType.classifyTriangle(1, 1, 1);
		Assertions.assertEquals(triClass, TriType.TryClass.EQUILATERAL);
	}

	@ClauseCoverage(
		predicate = "a || b || c",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = true),
		}
	)
	@Test
	public void recognizeTriangleByCodeTestCC2() {
		TriType triType = new TriType();
		TriType.TryClass triClass;
		triClass = triType.classifyTriangle(-1, -1, -1);
		Assertions.assertEquals(triClass, TriType.TryClass.NOT_VALID);
	}

	@ClauseCoverage(
		predicate = "a || b || c",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		}
	)
	@ClauseCoverage(
		predicate = "a || b || c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false),
		}
	)
	@ClauseCoverage(
		predicate = "a || b || c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true),
		}
	)
	@Test
	public void recognizeTriangleByCodeTestCC3() {
		TriType triType = new TriType();
		TriType.TryClass triClass;
		Assertions.assertEquals(triType.classifyTriangle(1, 2, 3), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(2, 3, 1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(3, 2, 1), TriType.TryClass.NOT_VALID);
	}

	@ClauseCoverage(
		predicate = "a && b",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false)
		}
	)
	@ClauseCoverage(
		predicate = "a && b",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = true)
		}
	)
	@ClauseCoverage(
		predicate = "a && b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false)
		}
	)
	@ClauseCoverage(
		predicate = "a && b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true)
		}
	)
	@Test
	public void recognizeTriangleByCodeTestCC4() {
		TriType triType = new TriType();
		TriType.TryClass triClass;
		Assertions.assertEquals(triType.classifyTriangle(2, 2, 3), TriType.TryClass.ISO_SCALENE);
		Assertions.assertEquals(triType.classifyTriangle(3, 2, 2), TriType.TryClass.ISO_SCALENE);
		Assertions.assertEquals(triType.classifyTriangle(2, 3, 2), TriType.TryClass.ISO_SCALENE);
		Assertions.assertEquals(triType.classifyTriangle(2, 2, 4), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(4, 2, 2), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(2, 4, 2), TriType.TryClass.NOT_VALID);
	}

}
