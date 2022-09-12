package com.project.noticeboard.web.post;

import com.project.noticeboard.domain.post.Post;
import com.project.noticeboard.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class PostController {

    private final PostRepository postRepository;

    @PostConstruct
    public void init() {
        postRepository.save(new Post("titleA", "contentA", LocalDate.now()));
        postRepository.save(new Post("titleB", "contentB", LocalDate.now()));
    }

    @GetMapping
    public String boards(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "boards";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/add")
    public String addPost() {
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        Post savedPost = postRepository.save(post);
        redirectAttributes.addAttribute("postId", savedPost.getId());
        return "redirect:/boards";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @ModelAttribute Post post) {
        postRepository.update(postId, post);
        return "redirect:/boards/{postId}";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam(value = "checkbox-list", required = false) Long[] checkboxList) {
        if (checkboxList != null) {
            for (Long value : checkboxList)
                postRepository.delete(value);
        }
        return "redirect:/boards";
    }

}