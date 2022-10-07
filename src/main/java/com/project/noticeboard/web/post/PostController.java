package com.project.noticeboard.web.post;

import com.project.noticeboard.domain.auth.PrincipalDetails;
import com.project.noticeboard.domain.comment.Comment;
import com.project.noticeboard.domain.member.Member;
import com.project.noticeboard.domain.post.PostSearchCond;
import com.project.noticeboard.domain.post.PostUpdateDto;
import com.project.noticeboard.domain.post.Post;
import com.project.noticeboard.service.comment.CommentService;
import com.project.noticeboard.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

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

        List<Comment> comments = commentService.findComments(postId);
        model.addAttribute("comments", comments);
        return "post";
    }

    @GetMapping("/add")
    public String addPost() {
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        var member = principalDetails.getMember();
        post.setMember(member);
        Post savedPost = postService.save(post);
        redirectAttributes.addAttribute("postId", savedPost.getId());
        return "redirect:/boards";
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Post post = postService.findById(postId).get();
        Member member = principalDetails.getMember();

        if (!Objects.equals(member.getEmail(), post.getMember().getEmail())) {
            return "redirect:/boards/{postId}";
        }

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

    @PostMapping("/{postId}/comment/add")
    public String addComment(@PathVariable Long postId, @ModelAttribute Comment comment, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Member member = principalDetails.getMember();
        Optional<Post> post = postService.findById(postId);
        comment.setPost(post.get());
        comment.setMember(member);
        commentService.save(comment);
        return "redirect:/boards/{postId}";
    }

}