package kg.geektech.postapp.data.remote;

import java.util.List;

import kg.geektech.postapp.data.models.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApi {

    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/posts")
    Call<Post> getPostsByGroup(
            @Query("group") String group
    );

    @POST("/posts")
    Call<Post> createPost(
            @Body Post post
    );

    @PUT("/posts/{id}")
    Call<Post> updatePost(
            @Path("id") int id,
            @Body Post post
    );

    @DELETE("/posts/{id}")
    Call<Post> deletePost(
            @Path("id") int id
    );
}
