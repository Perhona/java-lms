package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.ImageShapeTest.NORMAL_IMAGE_SHAPE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {
	public static final CoverImage NORMAL_COVER_IMAGE = new CoverImage(0L, 1024L * 1024L, "jpg", NORMAL_IMAGE_SHAPE);

	@Test
	@DisplayName("생성_이미지 크기 1MB 이하_throw IllegalArgumentException")
	void 이미지크기_1MB_이하() {
		assertThatThrownBy(() -> new CoverImage(0L, 1050L * 1050L, "jpg", NORMAL_IMAGE_SHAPE))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("커버 이미지 크기는 1MB 이하여야 합니다.");
	}
}