package com.curso.workshopmongo.resources;

import com.curso.workshopmongo.domain.Post;
import com.curso.workshopmongo.resources.util.URL;
import com.curso.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
        text = URL.decodeParam(text);

        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/contentsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByContent(@RequestParam(value = "content", defaultValue = "") String content){
        content = URL.decodeParam(content);

        List<Post> list = postService.findByContent(content);
        return ResponseEntity.ok().body(list);
    }

}
