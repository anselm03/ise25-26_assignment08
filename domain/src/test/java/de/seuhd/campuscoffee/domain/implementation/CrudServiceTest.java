package de.seuhd.campuscoffee.domain.implementation;

import de.seuhd.campuscoffee.domain.model.objects.User;
import de.seuhd.campuscoffee.domain.model.objects.Review;
import de.seuhd.campuscoffee.domain.ports.data.CrudDataService;
import de.seuhd.campuscoffee.domain.ports.data.ReviewDataService;
import de.seuhd.campuscoffee.domain.ports.data.UserDataService;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CrudServiceTest {

    @Mock
    private ReviewDataService reviewDataService;


    private CrudServiceImpl<Review, Long> crudService;

    @BeforeEach
    void beforeEach() {
        // Create a concrete implementation for testing the abstract CrudServiceImpl
        crudService = new CrudServiceImpl<>(Review.class) {
            @Override
            protected CrudDataService<Review, Long> dataService() {
                return reviewDataService;
            }
        };
    }

    @Test
    void deleteSuccessfully() {
        // given
        Review review = TestFixtures.getReviewFixtures().getFirst();
        assertNotNull(review.getId());

        // when
        crudService.delete(review.getId());

        // then
        verify(reviewDataService).delete(review.getId());
    }

    @Test
    void clearSuccessfully() {
        //given

        //when
        crudService.clear();

        //then
        verify(reviewDataService).clear();
    }
}
