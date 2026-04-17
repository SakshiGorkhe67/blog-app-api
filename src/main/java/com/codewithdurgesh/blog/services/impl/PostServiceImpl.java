package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.CategoryReporitory;
import com.codewithdurgesh.blog.repositories.PostRepository;
import com.codewithdurgesh.blog.repositories.UserRepository;
import com.codewithdurgesh.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryReporitory categoryReporitory;
    //***************************Create Post ********************************
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId)
        {
            User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

            Category category = this.categoryReporitory.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

            Post post = this.modelMapper.map(postDto, Post.class);


             post.setUser(user);
             post.setCategory(category);
             post.setAddDate(java.time.LocalDateTime.now());
             post.setImageName("default.png");

             Post savedPost = this.postRepository.save(post);

             return this.modelMapper.map(savedPost, PostDto.class);
        }

    //*************************** Update Post ********************************
    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepository.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    //*************************** Delete Post ********************************
    @Override
    public String deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        postRepository.delete(post);
        postRepository.flush();
        return "Post delete Successfully";
    }

    //*************************** Get All Post ********************************
    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=null;

        if(sortDir.equalsIgnoreCase("asc"))
        {
          sort=Sort.by(sortBy).ascending();
        }
        else
        {
        sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost=this.postRepository.findAll(p);
        List<Post> allPost=pagePost.getContent();


        List<PostDto> postDtos= allPost.stream()
                .map((post)->this.modelMapper.map(post,PostDto.class))
                .toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotleElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());


        return postResponse;
    }

    //*************************** Get Post By ID  ********************************
    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        return this.modelMapper.map(post,PostDto.class);
    }


    //*************************** Get Post By Category ********************************

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
      Category category=this.categoryReporitory.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
      List<Post>posts =this.postRepository.findByCategory(category);
      List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
      return postDtos;
    }

    // //*************************** Get  Post By User  ********************************
    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post>posts=this.postRepository.findByUser(user);
    List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=this.postRepository.findByTitleContaining(keyword);
        List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).toList();
        return postDtos;
    }
}












