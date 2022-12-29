package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.CreatePostRequest;
import sem3.its.ReReddit.domain.CreatePostResponse;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class CreatePostUseCaseImplTest {
    @Mock
    private PostRepository postRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private CreatePostUseCaseImpl createPostUseCase;

    @Test
    void createPost_ShouldReturnIdOfCreatedPost(){
        UserEntity author = UserEntity.builder().id(3L).build();
        PostEntity postEntity = PostEntity.builder().author(author).build();

        when(userRepositoryMock.save(author))
                .thenReturn(author);
        when(userRepositoryMock.findById(3L))
                .thenReturn(Optional.of(author));
        when(postRepositoryMock.save(postEntity))
                .thenReturn(postEntity);

        userRepositoryMock.save(author);

        CreatePostResponse actual = createPostUseCase.createPost(CreatePostRequest.builder().authorId(3).build());

        CreatePostResponse expected = CreatePostResponse.builder().id(actual.getId()).build();

        assertEquals(expected.getId(), actual.getId());

        verify(postRepositoryMock).save(postEntity);
    }
}
