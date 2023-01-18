package net.blog.web;

import lombok.RequiredArgsConstructor;
import net.blog.config.auth.dto.SessionUser;
import net.blog.domain.posts.PostsRepository;
import net.blog.service.posts.PostsService;
import net.blog.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        // 세션에서 user를 취득
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            // 세션에서 취득한 user가 null이 아닐경우 userName에 유저명을 설정
            model.addAttribute("loginUserName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
