package com.project.noticeboard.web.post;

import com.project.noticeboard.Repository.post.PostSearchCond;
import com.project.noticeboard.Repository.post.PostUpdateDto;
import com.project.noticeboard.domain.post.Post;
import com.project.noticeboard.service.post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class PostController {

    private final PostServiceImpl postService;

    @GetMapping
    public String boards(@ModelAttribute("postSearch") PostSearchCond postSearch, Model model) {
        List<Post> posts = postService.findPosts(postSearch);
        model.addAttribute("posts", posts);
        return "boards";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId).get();
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/add")
    public String addPost() {
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        Post savedPost = postService.save(post);
        redirectAttributes.addAttribute("postId", savedPost.getId());
        return "redirect:/boards";
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId).get();
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @ModelAttribute PostUpdateDto updateParam) {
        postService.update(postId, updateParam);
        return "redirect:/boards/{postId}";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam(value = "checkbox-list", required = false) Long[] checkboxList) {
        if (checkboxList != null) {
            for (Long value : checkboxList)
                postService.delete(value);
        }
        return "redirect:/boards";
    }

}