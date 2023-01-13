package net.blog.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void load() {

        String title = "title";
        String content = "content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("cp.lsung@gmail.com")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntityRegister() {
        String title = "title";
        String content = "content";
        String author = "author";

        // 현재시간을 설정
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        // 입력된 시간을 출력
        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        // 생성된 시간보다 now가 미래일 경우
        assertThat(posts.getCreatedDate()).isAfter(now);
        // 갱신된 시간보다 now가 미래일 경우
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
