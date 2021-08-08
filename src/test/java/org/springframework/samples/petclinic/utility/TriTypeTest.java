package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.*;
import com.github.mryf323.tractatus.experimental.extensions.ReportingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ExtendWith(ReportingExtension.class)
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
		predicate = (a && b) || (c && d);
//		we will write down the CUTPNFP and also UTPC TRs for them and you can see that the former does not subsumes the latter one.
//		CUTPNFP
//		TTFF, FTFF, TFFF, FFTT, FFTF, FFFT
//		now it would be adequate to just find a combination that is not included in the CUTPNFP TRs
//		FTFT would appear in the !a!c implicant in the fBar predicate that must be covered by the UTPC but this combination is not in the CUTPNFP TRs
//		So we can conclude that the CUTPNFP does not subsume UTPC
		return predicate;
	}

//	CC Tests (Clause Coverage)


	@ClauseDefinition(
		clause = 'a',
		def = "Side1 <= 0"
	)
	@ClauseDefinition(
		clause = 'b',
		def = "Side2 <= 0"
	)
	@ClauseDefinition(
		clause = 'c',
		def = "Side3 <= 0"
	)
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		}
	)
	@Test
	public void recognizeTriangleByCodeTestCC1() {
		TriType triType = new TriType();
		Assertions.assertEquals(triType.classifyTriangle(1, 1, 1), TriType.TryClass.EQUILATERAL);
	}

	@ClauseDefinition(
		clause = 'a',
		def = "Side1 <= 0"
	)
	@ClauseDefinition(
		clause = 'b',
		def = "Side2 <= 0"
	)
	@ClauseDefinition(
		clause = 'c',
		def = "Side3 <= 0"
	)
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = true),
		}
	)
	@Test
	public void recognizeTriangleByCodeTestCC2() {
		TriType triType = new TriType();
		Assertions.assertEquals(triType.classifyTriangle(-1, -1, -1), TriType.TryClass.NOT_VALID);
	}


	@ClauseDefinition(
		clause = 'a',
		def = "Side1+Side2 <= Side3"
	)
	@ClauseDefinition(
		clause = 'b',
		def = "Side2+Side3 <= Side1"
	)
	@ClauseDefinition(
		clause = 'c',
		def = "Side1+Side3 <= Side2"
	)
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		}
	)
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false),
		}
	)
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true),
		}
	)
	@Test
	public void recognizeTriangleByCodeTestCC3() {
		TriType triType = new TriType();
		Assertions.assertEquals(triType.classifyTriangle(1, 2, 3), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(2, 3, 1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(3, 2, 1), TriType.TryClass.NOT_VALID);
	}

////	redundant
//	@ClauseCoverage(
//		predicate = "a && b",
//		valuations = {
//			@Valuation(clause = 'a', valuation = true),
//			@Valuation(clause = 'b', valuation = false)
//		}
//	)
//	@ClauseCoverage(
//		predicate = "a && b",
//		valuations = {
//			@Valuation(clause = 'a', valuation = true),
//			@Valuation(clause = 'b', valuation = true)
//		}
//	)
//	@ClauseCoverage(
//		predicate = "a && b",
//		valuations = {
//			@Valuation(clause = 'a', valuation = false),
//			@Valuation(clause = 'b', valuation = false)
//		}
//	)
//	@ClauseCoverage(
//		predicate = "a && b",
//		valuations = {
//			@Valuation(clause = 'a', valuation = false),
//			@Valuation(clause = 'b', valuation = true)
//		}
//	)
//	@Test
//	public void recognizeTriangleByCodeTestCC4() {
//		TriType triType = new TriType();
//		Assertions.assertEquals(triType.classifyTriangle(2, 2, 3), TriType.TryClass.ISO_SCALENE);
//		Assertions.assertEquals(triType.classifyTriangle(3, 2, 2), TriType.TryClass.ISO_SCALENE);
//		Assertions.assertEquals(triType.classifyTriangle(2, 3, 2), TriType.TryClass.ISO_SCALENE);
//		Assertions.assertEquals(triType.classifyTriangle(2, 2, 4), TriType.TryClass.NOT_VALID);
//		Assertions.assertEquals(triType.classifyTriangle(4, 2, 2), TriType.TryClass.NOT_VALID);
//		Assertions.assertEquals(triType.classifyTriangle(2, 4, 2), TriType.TryClass.NOT_VALID);
//	}

//	CACC tests (Correlated Active Clause Coverage),
//		CUTPNFP (Corresponding Unique Points - Near False Point Coverage)

	@ClauseDefinition(
		clause = 'a',
		def = "Side1 <= 0"
	)
	@ClauseDefinition(
		clause = 'b',
		def = "Side2 <= 0"
	)
	@ClauseDefinition(
		clause = 'c',
		def = "Side3 <= 0"
	)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = true
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = true
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		},
		predicateValue = true
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false
	)
	@Test
	public void recognizeTriangleByCodeTestCACC1() {
		TriType triType = new TriType();
		Assertions.assertEquals(triType.classifyTriangle(-1, 1, 1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(1, -1, 1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(1, 1, -1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(1, 1, 1), TriType.TryClass.EQUILATERAL);
	}

	@ClauseDefinition(
		clause = 'a',
		def = "Side1+Side2 <= Side3"
	)
	@ClauseDefinition(
		clause = 'b',
		def = "Side2+Side3 <= Side1"
	)
	@ClauseDefinition(
		clause = 'c',
		def = "Side1+Side3 <= Side2"
	)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = true
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = true
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		},
		predicateValue = true
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false
	)
	@Test
	public void recognizeTriangleByCodeTestCACC2() {
		TriType triType = new TriType();
		Assertions.assertEquals(triType.classifyTriangle(1, 1, 3), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(3, 1, 1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(1, 3, 1), TriType.TryClass.NOT_VALID);
		Assertions.assertEquals(triType.classifyTriangle(4, 3, 5), TriType.TryClass.SCALENE);
	}

////	redundant
//	@CACC(
//		predicate = "a && b",
//		majorClause = 'a',
//		valuations = {
//			@Valuation(clause = 'a', valuation = true),
//			@Valuation(clause = 'b', valuation = true)
//		},
//		predicateValue = true
//	)
//	@CACC(
//		predicate = "a && b",
//		majorClause = 'a',
//		valuations = {
//			@Valuation(clause = 'a', valuation = false),
//			@Valuation(clause = 'b', valuation = true)
//		},
//		predicateValue = false
//	)
//	@CACC(
//		predicate = "a && b",
//		majorClause = 'b',
//		valuations = {
//			@Valuation(clause = 'a', valuation = true),
//			@Valuation(clause = 'b', valuation = true)
//		},
//		predicateValue = true
//	)
//	@CACC(
//		predicate = "a && b",
//		majorClause = 'b',
//		valuations = {
//			@Valuation(clause = 'a', valuation = true),
//			@Valuation(clause = 'b', valuation = false)
//		},
//		predicateValue = false
//	)
//	@Test
//	public void recognizeTriangleByCodeTestCACC3() {
//		TriType triType = new TriType();
//		Assertions.assertEquals(triType.classifyTriangle(2, 2, 1), TriType.TryClass.ISO_SCALENE);
//		Assertions.assertEquals(triType.classifyTriangle(2, 1, 2), TriType.TryClass.ISO_SCALENE);
//		Assertions.assertEquals(triType.classifyTriangle(2, 2, 5), TriType.TryClass.NOT_VALID);
//
//		Assertions.assertEquals(triType.classifyTriangle(2, 1, 2), TriType.TryClass.ISO_SCALENE);
//		Assertions.assertEquals(triType.classifyTriangle(1, 2, 2), TriType.TryClass.ISO_SCALENE);
//		Assertions.assertEquals(triType.classifyTriangle(2, 5, 2), TriType.TryClass.NOT_VALID);
//
//		Assertions.assertEquals(triType.classifyTriangle(1, 2, 2), TriType.TryClass.ISO_SCALENE);
////		Assertions.assertEquals(triType.classifyTriangle(1, 2, 2), TriType.TryClass.ISO_SCALENE);
////		not feasible for the last one because we cannot reach here without triOut == 3 which is supposed to be false
//		Assertions.assertEquals(triType.classifyTriangle(5, 2, 2), TriType.TryClass.NOT_VALID);
//	}

}
