package de.seuhd.campuscoffee.domain.implementation;

import de.seuhd.campuscoffee.domain.model.objects.User;
import de.seuhd.campuscoffee.domain.ports.data.CrudDataService;
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
    private UserDataService userDataService;

    private CrudServiceImpl<User, Long> crudService;
    @BeforeEach
    void beforeEach() {
        // Create a concrete implementation for testing the abstract CrudServiceImpl
        crudService = new CrudServiceImpl<>(User.class) {
            @Override
            protected CrudDataService<User, Long> dataService() {
                return userDataService;
            }
        };
    }

    @Test
    void deleteSuccessfully() {
        // given
        User user = TestFixtures.getUserFixtures().getFirst();
        assertNotNull(user.getId());

        // when
        crudService.delete(user.getId());

        // then
        verify(userDataService).delete(user.getId());
    }

    @Test
    void clearSuccessfully() {
        //given

        //when
        crudService.clear();

        //then
        verify(userDataService).clear();
    }
}
