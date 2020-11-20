package org.springframework.samples.petclinic.utility;

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

	@Test
	public void recognizeTriangleByCodeTestCC1() {
		TriType triType = new TriType();
		TriType.TryClass triClass;
		triClass = triType.classifyTriangle(1, 1, 1);
		Assertions.assertEquals(triClass, TriType.TryClass.EQUILATERAL);
	}

	@Test
	public void recognizeTriangleByCodeTestCC2() {
		TriType triType = new TriType();
		TriType.TryClass triClass;
		triClass = triType.classifyTriangle(-1, -1, -1);
		Assertions.assertEquals(triClass, TriType.TryClass.NOT_VALID);
	}

	@Test
	public void recognizeTriangleByCodeTestCC3() {
		TriType triType = new TriType();
		TriType.TryClass triClass;
		Assertions.assertEquals(triType.classifyTriangle(1, 2, 3), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(2, 3, 1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(3, 2, 1), TriType.TryClass.NOT_VALID);
	}

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
